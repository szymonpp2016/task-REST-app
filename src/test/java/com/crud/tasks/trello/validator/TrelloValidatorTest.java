package com.crud.tasks.trello.validator;

import org.apache.log4j.Logger;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    private static Logger logger;


    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloBoard firstBoardStub = new TrelloBoard("0", "test", new ArrayList<>());
        TrelloBoard secondBoardStub = new TrelloBoard("1", "board name (shall pass the validator)", new ArrayList<>());
        List<TrelloBoard> trelloBoardsStub = new ArrayList<>();
        trelloBoardsStub.add(firstBoardStub);
        trelloBoardsStub.add(secondBoardStub);

        //When
        List<TrelloBoard> validatedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoardsStub);

        List<TrelloBoard> validatedEmptyTrelloBoards = trelloValidator.validateTrelloBoards(new ArrayList<>());

        //Then
        assertEquals(1, validatedTrelloBoards.size());
        assertEquals("board name (shall pass the validator)", validatedTrelloBoards.get(0).getName());
        assertEquals(0, validatedEmptyTrelloBoards.size());
    }


    @Test
    public void testValidateGoodCard() {

        //Given
        TrelloCard secondTestCard = new TrelloCard("real Card", " (Should  pass the validator)", "abb","A");

        //When
        logger = mock(Logger.class);
        reset(logger);
        trelloValidator.validateCard(secondTestCard);

        //Then
        verify(logger, times(1)).info("Seems my application is used in proper way.");
    }

    @Test
    public void testValidateTestCard() {

        //Given
        TrelloCard firstTestCard = spy(new TrelloCard("test", "test (Should`nt pass the validator)", "aaa","A"));

        //When
        logger = mock(Logger.class);
        reset(logger);
        trelloValidator.validateCard(firstTestCard);

        //Then
        verify(logger, times(1)).info("Someone is testing my application!");
    }
}