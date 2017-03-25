package com.example.database.service;

import com.example.database.domain.Task;
import com.example.database.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskRepository repository;
    private TaskService service;

    @Before
    public void setUp() throws Exception {
        repository = mock(TaskRepository.class);
        service = new TaskService(repository);
    }

    @Test
    public void testSave() throws Exception {
        Task task = new Task();
        when(repository.save(task)).thenReturn(task);

        service.save(task);

        verify(repository, times(1)).save(task);
    }

    @Test
    public void testDelete() throws Exception {
        Task task = new Task();
        doNothing().when(repository).delete(task);

        service.delete(task);

        verify(repository, times(1)).delete(task);
    }

    @Test
    public void testDeleteAll() throws Exception {
        doNothing().when(repository).deleteAll();

        service.deleteAll();

        verify(repository, times(1)).deleteAll();
    }

    @Test
    public void testGetAll() throws Exception {
        List<Task> mockTasks = new ArrayList<>();
        when(repository.findAll()).thenReturn(mockTasks);

        List<Task> tasks = service.getAll();

        verify(repository, times(1)).findAll();
        assertEquals(mockTasks, tasks);
    }
}