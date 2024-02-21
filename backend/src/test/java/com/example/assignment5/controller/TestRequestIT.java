package com.example.assignment5.controller;

import com.example.assignment5.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class TestRequestIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void testAllTasks() throws Exception {
        mockMvc.perform(get("/api/v1/tasks")).andExpect(status().isOk());
    }

    @Test
    public void testGetTaskByID() throws Exception {
        mockMvc.perform(get("/api/v1/tasks/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetCompletedTasks() throws Exception {
        mockMvc.perform(get("/api/v1/tasks/completed")).andExpect(status().isOk());
    }

    @Test
    public void testGetInProgressTasks() throws Exception {
        mockMvc.perform(get("/api/v1/tasks/incomplete")).andExpect(status().isOk());
    }
}
