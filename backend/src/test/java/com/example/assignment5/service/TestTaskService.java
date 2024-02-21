package com.example.assignment5.service;

import com.example.assignment5.domain.TaskDomain;
import com.example.assignment5.persistence.TaskDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;
public class TestTaskService {
    private TaskService taskService;
    private List<TaskDomain> tasks;
    private TaskDomain firstTask;
    private List<TaskDomain> completedTasks;
    private TaskDomain addedTask;
    private TaskDomain updatedTask;
    private List<TaskDomain> incompletedTasks;

    @BeforeEach
    public void setUp() {
        TaskDao taskDao = mock(TaskDao.class);
        tasks = Arrays.asList(
                new TaskDomain(1, "Do the shopping", 4, false),
                new TaskDomain(2, "Do laundry", 1, false),
                new TaskDomain(3, "Do homework", 7, false),
                new TaskDomain(4, "Post letter", 6, true)
        );
        when(taskDao.getTasks()).thenReturn(tasks);

        firstTask = new TaskDomain(1, "Do the shopping", 4, false);
        when(taskDao.getTaskByID(1)).thenReturn(Optional.of(firstTask));

        completedTasks = Arrays.asList(new TaskDomain(4, "Post letter", 6, true));
        when(taskDao.getCompletedTasks()).thenReturn(completedTasks);

        addedTask = new TaskDomain(5, "Repair lights", 9, false);
        when(taskDao.addTask(addedTask)).thenReturn(addedTask);

        updatedTask = new TaskDomain(3, "Change task 3", 3, true);
        when(taskDao.updateTask(3, updatedTask)).thenReturn(updatedTask);

        incompletedTasks = Arrays.asList(
                new TaskDomain(5, "incompleted task1", 2, false),
                new TaskDomain(6, "incompleted task2", 3, false)
        );
        when(taskDao.getInProgressTasks()).thenReturn(incompletedTasks);

        taskService = new TaskService(taskDao);
    }

    @Test
    public void testGetAllTasks() {
        assertEquals(tasks, taskService.getAllTasks());
        assertEquals(4, taskService.getAllTasks().size());
    }

    @Test
    public void testGetTaskByID() {
        assertEquals(Optional.of(firstTask),
                taskService.getTaskByID(1));
    }

    @Test
    public void testGetCompletedTasks() {
        assertEquals(completedTasks, taskService.getCompletedTasks());
    }

    @Test
    public void testAddTask() {
        assertEquals(addedTask, taskService.addTask(addedTask));
    }

    @Test
    public void testUpdateTask() {
        assertNotEquals(updatedTask, taskService.updateTask(2, updatedTask));
        assertEquals(updatedTask, taskService.updateTask(3, updatedTask));
    }

    @Test
    public void getIncompletedTasks() {
        assertEquals(2, taskService.getInProgressTasks().size());
    }
}
