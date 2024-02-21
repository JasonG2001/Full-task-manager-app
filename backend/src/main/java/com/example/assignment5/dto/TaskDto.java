package com.example.assignment5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TaskDto {
    private int taskID;

    private String taskName;
    private int priority;
    private boolean completed;

    public TaskDto(int taskID, String taskName, int priority, boolean completed) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.priority = priority;
        this.completed = completed;
    }
}
