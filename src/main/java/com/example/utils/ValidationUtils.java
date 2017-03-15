package com.example.utils;

import com.example.controller.request.AddTaskRequest;
import com.example.controller.response.AddTaskResponse;
import com.example.database.domain.Task;

public class ValidationUtils {

    public static AddTaskResponse addTaskRequestValid(AddTaskRequest request) {
        AddTaskResponse response = new AddTaskResponse();

        if (null == request) {
            response.setSuccess(false);
            response.setFailReason("Missing Request");
            return response;
        }

        if (null == request.getTask()){
            response.setSuccess(false);
            response.setFailReason("Missing Task Object");
            return response;
        }

        Task task = request.getTask();
        if (isEmpty(task.getName())) {
            response.setSuccess(false);
            response.setFailReason("Missing Task Name");
            return response;
        }

        if (isEmpty(task.getDescription())) {
            response.setSuccess(false);
            response.setFailReason("Missing Task Description");
            return response;
        }

        if (isEmpty(task.getAssignee())) {
            response.setSuccess(false);
            response.setFailReason("Missing Task Assignee");
            return response;
        }

        if (null == task.getDate()) {
            response.setSuccess(false);
            response.setFailReason("Missing Task Date");
            return response;
        }

        response.setSuccess(true);
        return response;
    }

    public static boolean isEmpty(String string) {
        return null == string || string.isEmpty();
    }
}
