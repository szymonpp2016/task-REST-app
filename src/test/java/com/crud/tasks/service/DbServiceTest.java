package com.crud.tasks.service;


import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        Task taskTest1 = new Task(1L, "test title1", "test content1");
        Task taskTest2 = new Task(2L, "test title2", "test content2");
        List<Task> tasksStub = new ArrayList<>();
        tasksStub.add(taskTest1);
        tasksStub.add(taskTest2);

        when(taskRepository.findAll()).thenReturn(tasksStub);

        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();

        //Then
        assertEquals(2, fetchedTaskList.size());
    }

    @Test
    public void testGetAllTasksWithEmptyList() {
        //Given
        when(taskRepository.findAll()).thenReturn(new ArrayList<>());

        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();

        //Then
        assertEquals(0, fetchedTaskList.size());
    }

    @Test
    public void testGetTaskById() {
    /*    //Given
        Task taskStub = new Task(1L,"test title", "test content");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskStub));

        //When
        Task fetchedTaskById = dbService.getTaskById(1L);

        //Then
        assertEquals(taskStub.getId(), fetchedTaskById.getId()); */
    }

    @Test
    public void testSaveTask() {
        //Given
        Task taskStub = new Task(1l, " test title", "test content");
        when(taskRepository.save(taskStub)).thenReturn(taskStub);

        //When
        Task testTask = dbService.saveTask(taskStub);

        //Then
        assertEquals(taskStub.getId(), testTask.getId());
        assertEquals(taskStub.getTitle(), testTask.getTitle());
        assertEquals(taskStub.getContent(), testTask.getContent());
    }

    @Test
    public void testGetTask() {
        //Given
        Task taskStub = new Task(1L, "test title", "test content");

        when(dbService.getTask(1L)).thenReturn(Optional.ofNullable(taskStub));

        //When
        Optional<Task> fetchedTaskById = dbService.getTask(1L);

        //Then
        assertTrue(fetchedTaskById.isPresent());
        assertEquals(taskStub.getId(), fetchedTaskById.get().getId());
    }
}
