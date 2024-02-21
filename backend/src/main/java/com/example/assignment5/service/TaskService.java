package com.example.assignment5.service;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import com.example.assignment5.domain.TaskDomain;
import com.example.assignment5.persistence.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<TaskDomain> getAllTasks() {
        return taskDao.getTasks();
    }

    public Optional<TaskDomain> getTaskByID(int taskID) {
        return taskDao.getTaskByID(taskID);
    }
    public List<TaskDomain> getCompletedTasks() {
        return taskDao.getCompletedTasks();
    }

    public List<TaskDomain> getInProgressTasks() {
        return taskDao.getInProgressTasks();
    }

    public TaskDomain addTask(TaskDomain taskDomain) {
        return taskDao.addTask(taskDomain);
    }

    public TaskDomain updateTask(int taskID, TaskDomain newTaskDomain) {
        return taskDao.updateTask(taskID, newTaskDomain);
    }

    public void deleteTask(int taskID) {
        taskDao.deleteTask(taskID);
    }

    public List<TaskDomain> getTasksInPriorityAscOrder() {
        List<TaskDomain> allTasks = getAllTasks();
        return allTasks
                .stream()
                .sorted(new Comparator<TaskDomain>() {
                    @Override
                    public int compare(TaskDomain o1, TaskDomain o2) {
                        if (o2.getPriority() > o1.getPriority()) {
                            return -1;
                        } else if(o2.getPriority() < o1.getPriority()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                })
                .toList();
    }

    public List<TaskDomain> getTasksInPriorityDescOrder() {
        List<TaskDomain> allTasks = getAllTasks();
        return allTasks
                .stream()
                .sorted(new Comparator<TaskDomain>() {
                    @Override
                    public int compare(TaskDomain o1, TaskDomain o2) {
                        if (o2.getPriority() > o1.getPriority()) {
                            return 1;
                        } else if(o2.getPriority() < o1.getPriority()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                })
                .toList();
    }
}
