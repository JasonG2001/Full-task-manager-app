package com.example.assignment5.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TaskDomain {

    private int taskID;
    private String taskName;
    private int priority;
    private boolean completed;

    public TaskDomain(int taskID, String taskName, int priority, boolean completed) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.completed = completed;
        this.priority = priority;
    }
}
