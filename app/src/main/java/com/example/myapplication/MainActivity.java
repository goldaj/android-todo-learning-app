package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.TaskContract;
import com.example.myapplication.db.TaskDbHelper;
import com.example.myapplication.domain.Task;
import com.example.myapplication.domain.TaskRules;
import com.example.myapplication.domain.TaskState;

import java.util.ArrayList;
import java.util.List;

/** Tâches SQLite : opérations toujours identifiées par _ID, jamais par le titre. */
public class MainActivity extends AppCompatActivity {
    private TaskDbHelper helper;
    private ListView listView;
    private TaskArrayAdapter adapter;
    private EditText search;
    private TextView summary;
    private TaskState filter = null;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        helper = new TaskDbHelper(this);
        listView = findViewById(R.id.list_todo);
        listView.setEmptyView(findViewById(R.id.task_empty));
        search = findViewById(R.id.task_search);
        summary = findViewById(R.id.task_summary);
        if (state != null) {
            String stored = state.getString("filter", "");
            if ("TODO".equals(stored)) filter = TaskState.TODO;
            if ("DONE".equals(stored)) filter = TaskState.DONE;
        }
        findViewById(R.id.filter_all).setOnClickListener(v -> setFilter(null));
        findViewById(R.id.filter_todo).setOnClickListener(v -> setFilter(TaskState.TODO));
        findViewById(R.id.filter_done).setOnClickListener(v -> setFilter(TaskState.DONE));
        search.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { updateUI(); }
            @Override public void afterTextChanged(Editable s) {}
        });
        updateUI();
    }

    private void setFilter(TaskState next) {
        filter = next;
        updateUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        state.putString("filter", filter == null ? "" : filter.name());
        super.onSaveInstanceState(state);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_task) {
            showEditor(-1L, "");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showEditor(long id, String initial) {
        final EditText input = new EditText(this);
        input.setSingleLine(false);
        input.setMaxLines(3);
        input.setText(initial);
        input.setSelection(initial.length());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(id < 0 ? R.string.add_task : R.string.edit_task)
                .setView(input)
                .setPositiveButton(R.string.save, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        dialog.setOnShowListener(unused -> dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String title;
            try { title = TaskRules.normalizeTitle(input.getText().toString()); }
            catch (IllegalArgumentException error) {
                input.setError(error.getMessage()); return;
            }
            SQLiteDatabase db = helper.getWritableDatabase();
            try {
                ContentValues values = new ContentValues();
                values.put(TaskContract.TaskEntry.COL_TASK_TITLE, title);
                if (id < 0) {
                    values.put(TaskContract.TaskEntry.COL_TASK_STATE, TaskState.TODO.name());
                    db.insertOrThrow(TaskContract.TaskEntry.TABLE, null, values);
                } else {
                    int changed = db.update(TaskContract.TaskEntry.TABLE, values,
                            TaskContract.TaskEntry._ID + "=?", new String[]{String.valueOf(id)});
                    if (changed == 0) {
                        Toast.makeText(this, R.string.task_missing, Toast.LENGTH_SHORT).show();
                    }
                }
            } finally { db.close(); }
            dialog.dismiss();
            updateUI();
        }));
        dialog.show();
    }

    private long idFromButton(View button) {
        Object value = ((View) button.getParent()).getTag();
        if (!(value instanceof Long)) throw new IllegalStateException("Task ID missing");
        return (Long) value;
    }

    private void setState(long id, TaskState next) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(TaskContract.TaskEntry.COL_TASK_STATE, next.name());
            db.update(TaskContract.TaskEntry.TABLE, values,
                    TaskContract.TaskEntry._ID + "=?", new String[]{String.valueOf(id)});
        } finally { db.close(); }
        updateUI();
    }

    public void doneTask(View button) { setState(idFromButton(button), TaskState.DONE); }
    public void undoTask(View button) { setState(idFromButton(button), TaskState.TODO); }

    public void editTask(View button) {
        long id = idFromButton(button);
        SQLiteDatabase db = helper.getReadableDatabase();
        String title = null;
        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
                new String[]{TaskContract.TaskEntry.COL_TASK_TITLE},
                TaskContract.TaskEntry._ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);
        try {
            if (cursor.moveToFirst()) title = cursor.getString(0);
        } finally {
            cursor.close();
            db.close();
        }
        if (title != null) showEditor(id, title);
        else Toast.makeText(this, R.string.task_missing, Toast.LENGTH_SHORT).show();
    }

    public void deleteTask(View button) {
        long id = idFromButton(button);
        new AlertDialog.Builder(this)
                .setTitle(R.string.confirm_delete)
                .setPositiveButton(R.string.delete, (dialog, which) -> {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    try {
                        db.delete(TaskContract.TaskEntry.TABLE,
                                TaskContract.TaskEntry._ID + "=?", new String[]{String.valueOf(id)});
                    } finally { db.close(); }
                    updateUI();
                })
                .setNegativeButton(android.R.string.cancel, null).show();
    }

    private void updateUI() {
        List<Task> all = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE,
                        TaskContract.TaskEntry.COL_TASK_STATE},
                null, null, null, null, null);
        try {
            int countId = cursor.getColumnIndexOrThrow(TaskContract.TaskEntry._ID);
            int countTitle = cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COL_TASK_TITLE);
            int countState = cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COL_TASK_STATE);
            while (cursor.moveToNext()) {
                TaskState status;
                try { status = TaskState.valueOf(cursor.getString(countState)); }
                catch (IllegalArgumentException e) { status = TaskState.TODO; }
                all.add(new Task(cursor.getLong(countId), cursor.getString(countTitle), status));
            }
        } finally {
            cursor.close();
            db.close();
        }
        String text = search.getText() == null ? "" : search.getText().toString();
        List<Task> visible = TaskRules.select(all, text, filter);
        long remaining = 0;
        for (Task t : all) if (t.getState() == TaskState.TODO) remaining++;
        summary.setText(getString(R.string.task_summary_format, remaining, all.size() - remaining, visible.size()));
        findViewById(R.id.filter_all).setActivated(filter == null);
        findViewById(R.id.filter_todo).setActivated(filter == TaskState.TODO);
        findViewById(R.id.filter_done).setActivated(filter == TaskState.DONE);
        if (adapter == null) {
            adapter = new TaskArrayAdapter(this, visible);
            listView.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(visible);
            adapter.notifyDataSetChanged();
        }
    }
}
