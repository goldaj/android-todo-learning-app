package com.example.myapplication.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public final class TaskRules {
    private TaskRules() {}

    public static String normalizeTitle(String input) {
        if (input == null) throw new IllegalArgumentException("Le titre est obligatoire");
        String title = input.trim().replaceAll("\\s+", " ");
        if (title.isEmpty() || title.length() > 140) {
            throw new IllegalArgumentException("Le titre doit contenir entre 1 et 140 caractères");
        }
        return title;
    }

    public static List<Task> select(List<Task> tasks, String query, TaskState only) {
        String text = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        List<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (only != null && task.getState() != only) continue;
            if (!task.getTitle().toLowerCase(Locale.ROOT).contains(text)) continue;
            results.add(task);
        }
        results.sort(Comparator
                .comparingInt((Task t) -> t.getState() == TaskState.TODO ? 0 : 1)
                .thenComparing(Comparator.comparingLong(Task::getId).reversed()));
        return results;
    }
}
