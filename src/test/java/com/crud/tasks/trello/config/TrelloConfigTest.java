package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;


    @Test
    public void getTrelloApiEndpoint() {
        assertEquals("https://api.trello.com/1", trelloConfig.getTrelloApiEndpoint());

    }

    @Test
    public void getTrelloAppKey() {
        assertEquals("0627f8d543aaa3f1bfdf0bd71c06720e", trelloConfig.getTrelloAppKey());
    }

    @Test
    public void getTrelloToken() {
        assertEquals("3cb95ebe22b57c4eb6032030bf17c1cbe43de65ac65ee297d5ca398143a431c6", trelloConfig.getTrelloToken());
    }

    @Test
    public void getTrelloUsername() {
        assertEquals("szymon.pp.2016@interia.pl", trelloConfig.getTrelloUsername());
    }
}