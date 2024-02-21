package com.example.assignment5.transformer;
import com.example.assignment5.dto.TaskDto;
import com.example.assignment5.domain.TaskDomain;

public class TaskTransformer {

    public static TaskDto transformTaskDomainToTaskDto(TaskDomain taskDomain) {
        return new TaskDto(taskDomain.getTaskID(), taskDomain.getTaskName(), taskDomain.getPriority(), taskDomain.isCompleted());
    }

    public static TaskDomain transformTaskDtoToTaskDomain(TaskDto taskDto) {
        return new TaskDomain(taskDto.getTaskID(), taskDto.getTaskName(), taskDto.getPriority(), taskDto.isCompleted());
    }
}
