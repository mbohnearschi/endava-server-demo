package com.example.utils;

import com.example.controller.request.AddTaskRequest;
import com.example.controller.response.AddTaskResponse;
import com.example.database.domain.Task;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidationUtilsTest {

    private static final String EMPTY_STRING = "";

    @Test
    public void testAddTaskRequestValid() throws Exception {
        AddTaskRequest mockRequest = mock(AddTaskRequest.class);
        Task mockTask = getPopulatedTask();

        when(mockRequest.getTask()).thenReturn(mockTask);

        AddTaskResponse response = ValidationUtils.addTaskRequestValid(mockRequest);

        assertTrue(response.getSuccess());
        assertEquals(null, response.getFailReason());
    }

    @Test
    public void testAddTaskRequestNull() throws Exception {
        AddTaskResponse response = ValidationUtils.addTaskRequestValid(null);

        assertFalse(response.getSuccess());
        assertTrue(response.getFailReason().equalsIgnoreCase("Missing Request"));
    }

    @Test
    public void testAddTaskRequestTaskMissing() throws Exception {
        AddTaskRequest mockRequest = mock(AddTaskRequest.class);
        when(mockRequest.getTask()).thenReturn(null);

        AddTaskResponse response = ValidationUtils.addTaskRequestValid(mockRequest);

        assertFalse(response.getSuccess());
        assertTrue(response.getFailReason().equalsIgnoreCase("Missing Task Object"));
    }

    @Test
    public void testAddTaskRequestTaskNameMissing() throws Exception {
        AddTaskRequest mockRequest = mock(AddTaskRequest.class);
        Task mockTask = getPopulatedTask();
        mockTask.setName(EMPTY_STRING);

        when(mockRequest.getTask()).thenReturn(mockTask);

        AddTaskResponse response = ValidationUtils.addTaskRequestValid(mockRequest);

        assertFalse(response.getSuccess());
        assertTrue(response.getFailReason().equalsIgnoreCase("Missing Task Name"));
    }

    @Test
    public void testAddTaskRequestTaskDescriptionMissing() throws Exception {
        AddTaskRequest mockRequest = mock(AddTaskRequest.class);
        Task mockTask = getPopulatedTask();
        mockTask.setDescription(EMPTY_STRING);

        when(mockRequest.getTask()).thenReturn(mockTask);

        AddTaskResponse response = ValidationUtils.addTaskRequestValid(mockRequest);

        assertFalse(response.getSuccess());
        assertTrue(response.getFailReason().equalsIgnoreCase("Missing Task Description"));
    }

    @Test
    public void testAddTaskRequestTaskAssigneeMissing() throws Exception {
        AddTaskRequest mockRequest = mock(AddTaskRequest.class);
        Task mockTask = getPopulatedTask();
        mockTask.setAssignee(EMPTY_STRING);

        when(mockRequest.getTask()).thenReturn(mockTask);

        AddTaskResponse response = ValidationUtils.addTaskRequestValid(mockRequest);

        assertFalse(response.getSuccess());
        assertTrue(response.getFailReason().equalsIgnoreCase("Missing Task Assignee"));
    }

    @Test
    public void testAddTaskRequestTaskDateMissing() throws Exception {
        AddTaskRequest mockRequest = mock(AddTaskRequest.class);
        Task mockTask = getPopulatedTask();
        mockTask.setDate(null);

        when(mockRequest.getTask()).thenReturn(mockTask);

        AddTaskResponse response = ValidationUtils.addTaskRequestValid(mockRequest);

        assertFalse(response.getSuccess());
        assertTrue(response.getFailReason().equalsIgnoreCase("Missing Task Date"));
    }

    @Test
    public void testIsEmpty() throws Exception {
        String testString = "";

        Boolean isEmpty = ValidationUtils.isEmpty(testString);

        assertTrue(isEmpty);
    }

    @Test
    public void testIsNotEmpty() throws Exception {
        String testString = "test";

        Boolean isEmpty = ValidationUtils.isEmpty(testString);

        assertFalse(isEmpty);
    }

    private Task getPopulatedTask() {
        Task task = new Task();

        task.setName("test name");
        task.setDescription("test description");
        task.setAssignee("test assignee");
        task.setDate(1000L);

        return task;
    }
}