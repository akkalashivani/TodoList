package com.example.todolistapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTaskTitle;
    private EditText editTextTaskDescription;
    private DatabaseHelper databaseHelper;
    private Integer taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTaskTitle = findViewById(R.id.editTextTaskTitle);
        editTextTaskDescription = findViewById(R.id.editTextTaskDescription);
        Button buttonSaveTask = findViewById(R.id.buttonSaveTask);
        databaseHelper = new DatabaseHelper(this);

        taskId = getIntent().getIntExtra("task_id", -1);
        if (taskId != -1) {
            loadTaskData();
        }

        buttonSaveTask.setOnClickListener(v -> saveTask());
    }

    private void loadTaskData() {
        for (Task task : databaseHelper.getAllTasks()) {
            if (task.getId() == taskId) {
                editTextTaskTitle.setText(task.getTitle());
                editTextTaskDescription.setText(task.getDescription());
                break;
            }
        }
    }

    private void saveTask() {
        String title = editTextTaskTitle.getText().toString().trim();
        String description = editTextTaskDescription.getText().toString().trim();

        if (taskId == -1) {
            databaseHelper.addTask(title, description);
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        } else {
            databaseHelper.updateTask(taskId, title, description, false);
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
