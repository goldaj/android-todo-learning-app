package com.example.myapplication.domain;

public final class Task {
    private final long id;
    private final String title;
    private final TaskState state;

    public Task(long id, String title, TaskState state) {
        this.id = id;
        this.title = title;
        this.state = state;
    }

    // Kept for compatibility with historical examples.
    public Task(String title, TaskState state) { this(-1, title, state); }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public TaskState getState() { return state; }
}
