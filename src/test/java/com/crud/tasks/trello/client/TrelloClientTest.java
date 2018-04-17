package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    //czy to w  testach jest ok?
    private URI uriCrads = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");
    private URI uriBoards = new URI("http://test.com/members/szymon.pp.2016@interia.pl/boards?key=test&token=test&fields=name,id&lists=all");
    private static int testCounter = 0;

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    public TrelloClientTest() throws URISyntaxException {
    }

    @Before
    public void init() {
        testCounter++;
        System.out.println("Preparing to execute test 18.4 #" + testCounter);
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUsername()).thenReturn("szymon.pp.2016@interia.pl"); // bład w kodilli - bo nie ma Mocka na to??
        // when(uri).thenReturn( new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id")); //zMockowanie sie nie udało - w sumie do obgadania
    }
    @After
    public void after() {
        System.out.println("Test Case: end");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println(("Starting Trello(18.4) test   \n"));
    }

    @AfterClass
    public static void aftereClass() {
        System.out.println("\n End Trello (18.4)  test");
    }

    @Test
    public void shouldFetchTrelloBoards() {

        // Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());
        when(restTemplate.getForObject(uriBoards, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        // When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        // Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(

        //Given
                "1",
                "Test task",
                "http://test.com",
                new TrelloBadgesDto()
        ); //dodałem `obsługę` Badgest,  nie chiałem zakomentowac nim nie dasz znac że 18.3 ok.
        when(restTemplate.postForObject(uriCrads, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //When
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() {

        //Given
        when(restTemplate.postForObject(uriCrads, null, CreatedTrelloCard.class)).thenReturn(null);

        //When
        List<TrelloBoardDto> trelloBoardDto = trelloClient.getTrelloBoards();

        //Then
        assertEquals(0, trelloBoardDto.size());
    }
}