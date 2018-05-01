package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    private static int testCounter = 0;

    @InjectMocks
    private TrelloMapper trelloMapper;


    @Before
    public void before() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
    }

    @After
    public void after() {
        System.out.println("Test Case: end");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Test Suite: begin");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Test Suite: end");
    }

    @Test
    public void test1MapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("exampleCard", "Card", "1", "11");
        TrelloCard trelloCard = new TrelloCard("exampleCard", "Card", "1", "11");

        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
         assertEquals(trelloCard.getName(), mappedCard.getName());
    }

    @Test
    public void test2MapToCardDto() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("exampleCard", "Card", "1", "11");
        TrelloCard trelloCard = new TrelloCard("exampleCard", "Card", "1", "11");

        //When
        TrelloCardDto dtoCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCardDto.getName(), dtoCard.getName());
       }


    @Test
    public void test3MapListDtoToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1","pierwsza",false));
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1","pierwsza",false));

        //When
        List<TrelloList> mappedTrelloList = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(mappedTrelloList.get(0).getName(), trelloList.get(0).getName()  );
        assertEquals(mappedTrelloList.get(0).getId(), trelloList.get(0).getId()  );
    }

    @Test
    public void test4MapListToListDto() {

        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1","pierwsza",false));
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1","pierwsza",false));

        //When
        List<TrelloListDto> mappedTrelloListDto = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(mappedTrelloListDto.get(0).getName(), trelloListDto.get(0).getName()  );
        assertEquals(mappedTrelloListDto.get(0).getId(), trelloListDto.get(0).getId()  );
    }

    @Test
    public void test5MapBoardToBoardDto() {

        //Given
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1","pierwsza",false));

        trelloBoard.add(new TrelloBoard("1","pierwsza",trelloList));

        //When
        List<TrelloBoardDto> mappedTrelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoard);

        //Then
        assertEquals(mappedTrelloBoardDto.get(0).getName(), trelloBoard.get(0).getName()  );
        assertEquals(mappedTrelloBoardDto.get(0).getId(), trelloBoard.get(0).getId()  );

        assertEquals(mappedTrelloBoardDto.get(0).getLists().get(0).getName(), trelloList.get(0).getName()  );
    }

    @Test
    public void test6MapBoardDtoToBoard() {

        //Given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1","pierwsza",false));

        trelloBoardDto.add(new TrelloBoardDto("1","pierwsza",trelloListDto));

        //When
        List<TrelloBoard> mappedTrelloBoard = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        assertEquals(mappedTrelloBoard.get(0).getName(), trelloBoardDto.get(0).getName()  );
        assertEquals(mappedTrelloBoard.get(0).getId(), trelloBoardDto.get(0).getId()  );

        assertEquals(mappedTrelloBoard.get(0).getLists().get(0).getName(), trelloListDto.get(0).getName()  );
    }
}
