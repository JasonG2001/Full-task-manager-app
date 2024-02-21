package com.example.assignment5.controller;
import com.example.assignment5.domain.TaskDomain;
import com.example.assignment5.dto.TaskDto;
import com.example.assignment5.service.TaskService;
import com.example.assignment5.transformer.TaskTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/tasks")
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/completed")
    public List<TaskDto> getCompletedTasks() {
        return taskService
                .getCompletedTasks()
                .stream()
                .map(TaskTransformer::transformTaskDomainToTaskDto)
                .toList();
    }

    @GetMapping("/incomplete")
    public List<TaskDto> getInProgressTasks() {
        return taskService
                .getInProgressTasks()
                .stream()
                .map(TaskTransformer::transformTaskDomainToTaskDto)
                .toList();
    }

    @GetMapping("/priority/ascending")
    public List<TaskDto> getAllTasksInPriorityAscOrder() {
        return taskService
                .getTasksInPriorityAscOrder()
                .stream()
                .map(TaskTransformer::transformTaskDomainToTaskDto)
                .toList();
    }

    @GetMapping("/priority/descending")
    public List<TaskDto> getAllTasksInPriorityDescOrder() {
        return taskService
                .getTasksInPriorityDescOrder()
                .stream()
                .map(TaskTransformer::transformTaskDomainToTaskDto)
                .toList();
    }

    @GetMapping("")
    public List<TaskDto> getAllTasks() {
        return taskService
                .getAllTasks()
                .stream()
                .map(TaskTransformer::transformTaskDomainToTaskDto)
                .toList();
    }

    @GetMapping("/{id}")
    public TaskDto getTaskByID(@PathVariable final int id) {
        Optional<TaskDomain> optionalTask = taskService.getTaskByID(id);
        return optionalTask.map(taskDomain -> TaskTransformer.transformTaskDomainToTaskDto(taskDomain)).orElse(null);
    }

    @PostMapping
    public TaskDto addNewTask(@RequestBody final TaskDto taskDto) {
        TaskDomain taskDomain = TaskTransformer.transformTaskDtoToTaskDomain(taskDto);
        return TaskTransformer.transformTaskDomainToTaskDto(taskService.addTask(taskDomain));
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable final int id, @RequestBody final TaskDto taskDto) {
        return TaskTransformer.transformTaskDomainToTaskDto(
                taskService.updateTask(id, TaskTransformer.transformTaskDtoToTaskDomain(taskDto)));

    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable final int id) {
        taskService.deleteTask(id);
    }
}
