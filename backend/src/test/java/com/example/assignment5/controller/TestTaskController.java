package com.example.assignment5.controller;

import com.example.assignment5.domain.TaskDomain;
import com.example.assignment5.dto.TaskDto;
import com.example.assignment5.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestTaskController {
    private TaskController taskController;
    private List<TaskDto> taskDtos;

    @BeforeEach
    public void setUp() {
        TaskService taskService = mock(TaskService.class);
        taskDtos = Arrays.asList(
                new TaskDto(1, "Do the shopping", 4, false),
                new TaskDto(2, "Do laundry", 1, false),
                new TaskDto(3, "Do homework", 7, false),
                new TaskDto(4, "Post letter", 6, true)
        );
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(
                new TaskDomain(1, "Do the shopping", 4, false),
                new TaskDomain(2, "Do laundry", 1, false),
                new TaskDomain(3, "Do homework", 7, false),
                new TaskDomain(4, "Post letter", 6, true)
        ));
        when(taskService.getTaskByID(1)).thenReturn(Optional.of(
                new TaskDomain(1, "Do the shopping", 4, false)));

        when(taskService.getCompletedTasks()).thenReturn(Arrays.asList(
                new TaskDomain(4, "Post letter", 6, true)
        ));

        when(taskService.getInProgressTasks()).thenReturn(Arrays.asList(
                new TaskDomain(5, "incompleted task1", 2, false),
                new TaskDomain(6, "incompleted task2", 3, false)
        ));

        TaskDomain addedTask = new TaskDomain(5, "Fix lights", 9, false);
        when(taskService.addTask(addedTask)).thenReturn(addedTask);

        TaskDomain updatedTask = new TaskDomain(3, "Complete task 3", 5, true);
        when(taskService.updateTask(3, updatedTask)).thenReturn(updatedTask);

        taskController = new TaskController(taskService);
    }

    @Test
    public void testGetAllTasks() {
        assertEquals(taskDtos, taskController.getAllTasks());
        assertEquals(4, taskController.getAllTasks().size());
    }

    @Test
    public void testGetTaskByID() {
        assertEquals(new TaskDto(1, "Do the shopping", 4, false),
                taskController.getTaskByID(1));
    }

    @Test
    public void testGetCompletedTasks() {
        assertEquals(Arrays.asList(new TaskDto(4, "Post letter", 6, true)),
                taskController.getCompletedTasks());
        assertEquals(1, taskController.getCompletedTasks().size());
        assertEquals("Post letter", taskController.getCompletedTasks().getFirst().getTaskName());
    }

    @Test
    public void testGetInProgressTasks() {
        assertEquals(2, taskController.getInProgressTasks().size());
        assertTrue(taskController.getInProgressTasks().get(0) instanceof TaskDto);
        assertFalse(taskController.getInProgressTasks().get(0).isCompleted());
        assertFalse(taskController.getInProgressTasks().get(1).isCompleted());
    }

    @Test
    public void testAddTask() {
        TaskDto newTask = new TaskDto(5, "Fix lights", 9, false);
        assertEquals(newTask, taskController.addNewTask(newTask));
    }

    @Test
    public void testUpdateTask() {
        TaskDto updatedTask = new TaskDto(3, "Complete task 3", 5, true);
        assertEquals(updatedTask, taskController.updateTask(3, updatedTask));
        assertEquals(5, taskController.updateTask(3, updatedTask).getPriority());
    }
}
