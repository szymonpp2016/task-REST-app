package com.crud.tasks.controller;

import static org.junit.Assert.*;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    @RunWith(SpringRunner.class)
    @WebMvcTest(TaskController.class)
    public class TaskControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private DbService service;

        @MockBean
        private TaskMapper taskMapper;

        @Test
        public void test1GetTask() throws Exception {
            //given
            TaskDto taskDto = new TaskDto(1L, "test", "test_content");
            Task task = new Task(1L,"test", "test_content");

            //when & then
            Optional<Task> optionalTask = Optional.of(task);
            when(service.getTask(1L)).thenReturn(optionalTask);
            when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
            mockMvc.perform(get("/v1/task/getTask").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.title", is("test")))
                    .andExpect(jsonPath("$.content", is("test_content")));
        }



        @Test
        public void test2GetTaskbyId() throws Exception {
            //given
            TaskDto taskDto = new TaskDto(1L, "test", "test_content");
            Task task = new Task(1L,"test", "test_content");

            //when & then
            when(service.getTaskById(1L)).thenReturn(task);
             when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
            mockMvc.perform(get("/v1/task/getTasksById").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.title", is("test")))
                    .andExpect(jsonPath("$.content", is("test_content")));
        }

        @Test
        public void test3GetTasks() throws Exception {
            // Given
            List<TaskDto> taskDtoList = new ArrayList<>();
            taskDtoList.add(new TaskDto(1L, "test title", "test content"));

            when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtoList);

            // When & Then
            mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[0].title", is("test title")))
                    .andExpect(jsonPath("$[0].content", is("test content")));
        }


        @Test
        public void test4UpdateTask() throws Exception {
            //given
            Task task = new Task(1L,"test", "something to do");
            TaskDto taskDto = new TaskDto(1L,"test", "something to do");

            when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
            when(service.saveTask(task)).thenReturn(task);
            when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
            Gson gson = new Gson();
            String jsonContent = gson.toJson(task);
            // when & then
            mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContent)
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.title", is("test")))
                    .andExpect(jsonPath("$.content", is("something to do")));
        }

        @Test
        public void test5CreateTask() throws Exception {
            //given
            Task task = new Task(1L,"test", "something to do");
            TaskDto taskDto = new TaskDto(1L,"test", "something to do");


            when(service.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
            when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);

            Gson gson = new Gson();
            String jsonContent = gson.toJson(task);

            // when & then
            mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContent)
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isOk());

            verify(service,times(1)).saveTask(ArgumentMatchers.any(Task.class));
        }

        @Test
        public void test6DeleteTask() throws Exception {
            //given
            Task task = new Task(1L,"test", "test_content");
            TaskDto taskDto = new TaskDto(1L, "test", "test_content");
            when(service.getTaskById(task.getId())).thenReturn(task);
            doNothing().when(service).deleteTask(1L);
            //when & then
            mockMvc.perform(delete("/v1/task/deleteTask").param("taskId","1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        @Test
        public void test6BetterDeleteTask() throws Exception {

            mockMvc.perform(delete("/v1/task/deleteTask").param("taskId","1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());


            verify(service).deleteTask(1L);
        }




    }