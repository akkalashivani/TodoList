package com.example.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewTasks;
    private DatabaseHelper databaseHelper;
    private ArrayList<Task> taskList;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        listViewTasks = findViewById(R.id.listViewTasks);
        Button buttonAddTask = findViewById(R.id.buttonAddTask);

        buttonAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });

        loadTasks();
        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
            Task selectedTask = taskList.get(position);
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            intent.putExtra("task_id", selectedTask.getId());
            startActivity(intent);
        });
    }

    private void loadTasks() {
        taskList = databaseHelper.getAllTasks();
        ArrayList<String> taskTitles = new ArrayList<>();
        for (Task task : taskList) {
            taskTitles.add(task.getTitle() + (task.isCompleted() ? " (Completed)" : ""));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskTitles);
        listViewTasks.setAdapter(adapter);
    }

}
