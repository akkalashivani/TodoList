package com.example.todolistapp;

public class Task {
    private final int id;
    private final String title;
    private final String description;
    private final boolean isCompleted;

    public Task(int id, String title, String description, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return isCompleted; }

}
