package com.example.myapplication;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myapplication.domain.Task;
import com.example.myapplication.domain.TaskState;
import java.util.List;

public final class TaskArrayAdapter extends ArrayAdapter<Task> {
    public TaskArrayAdapter(@NonNull Context context,@NonNull List<Task> objects) {
        super(context,0,objects);
    }
    @NonNull
    @Override
    public View getView(int position,@Nullable View convertView,@NonNull ViewGroup parent) {
        View row=convertView!=null?convertView:
                LayoutInflater.from(getContext()).inflate(R.layout.item_todo,parent,false);
        Task task=getItem(position);
        if(task==null)return row;
        // The row tag survives recycling, unlike the original title-based SQL lookup.
        row.setTag(Long.valueOf(task.getId()));
        TextView title=row.findViewById(R.id.task_title);
        title.setText(task.getTitle());
        Button done=row.findViewById(R.id.task_done);
        Button undo=row.findViewById(R.id.task_undo);
        boolean completed=task.getState()==TaskState.DONE;
        done.setVisibility(completed?View.GONE:View.VISIBLE);
        undo.setVisibility(completed?View.VISIBLE:View.GONE);
        if(completed)title.setPaintFlags(title.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
        else title.setPaintFlags(title.getPaintFlags()&~Paint.STRIKE_THRU_TEXT_FLAG);
        return row;
    }
}
