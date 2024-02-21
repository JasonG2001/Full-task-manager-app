package com.example.assignment5.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestTaskDaoIT {
    private final TaskDao taskDao;

    @Autowired
    public TestTaskDaoIT(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Test
    public void testGetAllTasks() {
        assertEquals(0, taskDao.getTasks().size());
    }
}
