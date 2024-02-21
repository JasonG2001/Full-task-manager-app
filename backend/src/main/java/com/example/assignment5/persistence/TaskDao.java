package com.example.assignment5.persistence;


import com.example.assignment5.domain.TaskDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TaskDomain> getTasks() {
        return jdbcTemplate.query("SELECT * FROM Tasks;", (rs, _rowNum) ->
                new TaskDomain(
                        rs.getInt("taskID"),
                        rs.getString("taskName"),
                        rs.getInt("priority"),
                        rs.getBoolean("completed")));
    }

    public Optional<TaskDomain> getTaskByID(int taskID) {
        return jdbcTemplate.query("SELECT * FROM Tasks WHERE taskID = ?;",
                (rs, _rowNum) -> new TaskDomain(
                    rs.getInt("taskID"),
                    rs.getString("taskName"),
                    rs.getInt("priority"),
                    rs.getBoolean("completed")),
                taskID)
                .stream()
                .findFirst();
    }

    public List<TaskDomain> getCompletedTasks() {
        return jdbcTemplate.query("SELECT * FROM Tasks WHERE completed = true;",
                (rs, _rowNum) -> new TaskDomain(
                        rs.getInt("taskID"),
                        rs.getString("taskName"),
                        rs.getInt("priority"),
                        rs.getBoolean("completed")));
    }

    public List<TaskDomain> getInProgressTasks() {
        return jdbcTemplate.query("SELECT * FROM Tasks WHERE completed = false;",
                (rs, _rowNum) -> new TaskDomain(
                        rs.getInt("taskID"),
                        rs.getString("taskName"),
                        rs.getInt("priority"),
                        rs.getBoolean("completed")));
    }

    public TaskDomain addTask(TaskDomain taskDomain) {
        if (jdbcTemplate.update("INSERT INTO Tasks (taskName, priority, completed) VALUES (? , ?, ?);",
                taskDomain.getTaskName(),
                taskDomain.getPriority(),
                taskDomain.isCompleted()) == 1) {
            return taskDomain;
        }
        return null;
    }

    public TaskDomain updateTask(int taskID, TaskDomain taskDomain) {
        if (jdbcTemplate.update("UPDATE Tasks SET taskName = ?, priority = ?, completed = ? WHERE taskID = ?;",
                taskDomain.getTaskName(),
                taskDomain.getPriority(),
                taskDomain.isCompleted(),
                taskID) == 1) {
            return taskDomain;
        }
        return null;
    }

    public void deleteTask(int taskID) {
        jdbcTemplate.update("DELETE FROM Tasks WHERE taskID = ?;",
                taskID);
    }
}
