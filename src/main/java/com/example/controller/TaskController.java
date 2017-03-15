package com.example.controller;

import com.example.controller.request.AddTaskRequest;
import com.example.controller.response.AddTaskResponse;
import com.example.database.domain.Task;
import com.example.service.TaskService;
import com.example.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/api/getTasks", method = RequestMethod.GET)
    public List<Task> getTasks() {
        return taskService.getAll();
    }

    @RequestMapping(value = "/api/addTask", method = RequestMethod.POST)
    public AddTaskResponse addIncome(HttpServletResponse httpResponse,
                                     @RequestBody(required = false) AddTaskRequest request) {
        AddTaskResponse response = ValidationUtils.addTaskRequestValid(request);
        if (!response.getSuccess()) {
            return response;
        }

        taskService.save(request.getTask());
        return response;
    }
}