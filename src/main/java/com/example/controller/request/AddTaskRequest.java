package com.example.controller.request;

import com.example.database.domain.Task;

public class AddTaskRequest {

    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}