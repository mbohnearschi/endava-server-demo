package com.example.controller;

import com.example.controller.request.AddTaskRequest;
import com.example.controller.response.AddTaskResponse;
import com.example.database.domain.Task;
import com.example.database.service.TaskService;
import com.example.utils.ValidationUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.verifyZeroInteractions;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidationUtils.class)
public class TaskControllerTest {

    private TaskService service;
    private TaskController controller;

    @Before
    public void setUp() throws Exception {
        service = mock(TaskService.class);
        controller = new TaskController(service);
    }

    @Test
    public void testGetTasks() throws Exception {
        List<Task> mockTasks = new ArrayList<>();
        when(service.getAll()).thenReturn(mockTasks);

        List<Task> tasks = controller.getTasks();

        verify(service).getAll();
        assertEquals(mockTasks, tasks);
    }

    @Test
    public void testAddTask() throws Exception {
        PowerMockito.mockStatic(ValidationUtils.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        AddTaskRequest mockRequest = mock(AddTaskRequest.class);
        Task mockTask = mock(Task.class);
        AddTaskResponse mockValidationResponse = mock(AddTaskResponse.class);

        when(mockRequest.getTask()).thenReturn(mockTask);
        when(mockValidationResponse.getSuccess()).thenReturn(true);
        doNothing().when(service).save(mockTask);
        PowerMockito.when(ValidationUtils.addTaskRequestValid(mockRequest)).thenReturn(mockValidationResponse);

        AddTaskResponse response = controller.addTask(mockResponse, mockRequest);

        verify(mockRequest).getTask();
        verify(mockValidationResponse).getSuccess();
        verify(service).save(mockTask);
        assertEquals(mockValidationResponse, response);
        verifyStatic();
    }

    @Test
    public void testAddTaskFailure() throws Exception {
        PowerMockito.mockStatic(ValidationUtils.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        AddTaskRequest mockRequest = mock(AddTaskRequest.class);
        Task mockTask = mock(Task.class);
        AddTaskResponse mockValidationResponse = mock(AddTaskResponse.class);

        when(mockRequest.getTask()).thenReturn(mockTask);
        when(mockValidationResponse.getSuccess()).thenReturn(false);
        when(mockValidationResponse.getFailReason()).thenReturn("Missing Something");
        doNothing().when(service).save(mockTask);
        PowerMockito.when(ValidationUtils.addTaskRequestValid(mockRequest)).thenReturn(mockValidationResponse);

        AddTaskResponse response = controller.addTask(mockResponse, mockRequest);

        verify(mockValidationResponse).getSuccess();
        verifyZeroInteractions(mockRequest);
        verifyZeroInteractions(service);
        assertEquals(mockValidationResponse, response);
        verifyStatic();
    }
}