package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloConfigTest {

    @Mock
    private TrelloConfig trelloConfig;


    @Test
    public void getTrelloApiEndpoint() {

        when(trelloConfig.getTrelloUsername()).thenReturn("aaaaa");
        String username= trelloConfig.getTrelloUsername();

    assertEquals("aaaaa", username);
    }

    @Test
    public void getTrelloAppKey() {
    }

    @Test
    public void getTrelloToken() {
    }

    @Test
    public void getTrelloUsername() {
    }
}