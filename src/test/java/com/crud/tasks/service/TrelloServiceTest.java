package com.crud.tasks.service;



import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtoStub = new ArrayList<>();
        trelloListDtoStub.add(new TrelloListDto("1", "test list name", false));

        List<TrelloBoardDto> trelloBoardDtoStub = new ArrayList<>();
        trelloBoardDtoStub.add(new TrelloBoardDto("1", "test board name", trelloListDtoStub));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoStub);

        //When
        List<TrelloBoardDto> fetchedTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoardDto.size());
        assertEquals("test board name", fetchedTrelloBoardDto.get(0).getName());
    }

    @Test
    public void testFetchEmptyTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoStub = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoStub);

        //When
        List<TrelloBoardDto> fetchedEmptyTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(0, fetchedEmptyTrelloBoardDto.size());
    }

    @Test
    public void testCreateTrelloCard() {
        TrelloDto trelloDto = new TrelloDto("1", "1");
        TrelloAttachmentsDto trelloAttachmentsByTypeDto = new TrelloAttachmentsDto(trelloDto);
        TrelloBadgesDto badgeDtoStub = new TrelloBadgesDto(1, trelloAttachmentsByTypeDto);
        CreatedTrelloCardDto createdTrelloCardDtoStub = new CreatedTrelloCardDto("1", "test name", "test.com/test", badgeDtoStub);
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test description", "test pos", "1");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDtoStub);
        //when
        CreatedTrelloCardDto testCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertEquals("1", testCreatedTrelloCardDto.getId());
        assertEquals("test name", testCreatedTrelloCardDto.getName());
        assertEquals("test.com/test", testCreatedTrelloCardDto.getShortUrl());
        assertEquals(badgeDtoStub, testCreatedTrelloCardDto.getBadges());
        assertEquals("1", testCreatedTrelloCardDto.getBadges().getAttachmentsByType().getTrello().getCard());
        assertEquals("1", testCreatedTrelloCardDto.getBadges().getAttachmentsByType().getTrello().getBoard());
        assertEquals(1, testCreatedTrelloCardDto.getBadges().getVotes());
    }
}