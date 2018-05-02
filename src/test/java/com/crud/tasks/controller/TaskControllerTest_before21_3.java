package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest_before21_3 {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService service;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private TaskController mockTaskController;

    @Test
    public void getTasks() {

        //Given
        List<TaskDto> taskDto = new ArrayList<>();

        taskDto.add(new TaskDto(1L, "test list", "aaaaaa"));
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDto);

        //When
        List<TaskDto> mapedtaskDto= taskController.getTasks();

        //Then
        assertNotNull(mapedtaskDto);
        assertEquals  (1, mapedtaskDto.size());
    }


   @Test
    public void getTasksById() {

        TaskDto taskDto= new TaskDto(1L, "test list", "aaaaaa");
        when(taskMapper.mapToTaskDto(service.getTaskById(1L))).thenReturn(taskDto);

        //When
        TaskDto mapedtaskDto= taskController.getTasksById(1L);

        //Then
        assertNotNull(mapedtaskDto);

    }

 /*   @Test
    public void getTask() throws TaskNotFoundException {
        Long taskId =1L;
        TaskDto taskDto= new TaskDto(1L, "test list", "aaaaaa");
        when(taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new))).thenReturn(taskDto);

        //When
        TaskDto mapedtaskDto= taskController.getTasksById(1L);

        //Then
        assertNotNull(mapedtaskDto);
    } */

    @Test
    public void deleteTask() {
        //Given

        Long taskId =1L;

        //When
        mockTaskController.deleteTask(taskId);

        //Then
        verify(mockTaskController,times(1)).deleteTask(taskId);
    }




    @Test
    public void updateTask() {


        TaskDto taskDto = new TaskDto(1L, "test list", "aaaaaa");
        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(taskDto);

        //When
        TaskDto mapedtaskDto = taskController.updateTask(taskDto);

        //Then
        assertNotNull(mapedtaskDto);
        assertEquals  (taskDto.getId(), mapedtaskDto.getId());
    }
    @Test
    public void createTask() {

        Long taskId =1L;

        //When
        Task task= new Task(1L, "test list", "aaaaaa");
        TaskDto taskDto = new TaskDto(1L, "test list", "aaaaaa");
        when(service.saveTask(taskMapper.mapToTask(taskDto))).thenReturn(task);

        taskController.createTask(taskDto);

        //Then
        verify(taskMapper,times(1)).mapToTask(taskDto);
    }

    @Test
    public void helloWorld() {
        //When
        String answerOfHelloWorld = taskController.helloWorld();
        //Then
        assertEquals("HelloWorld", answerOfHelloWorld);
    }
}