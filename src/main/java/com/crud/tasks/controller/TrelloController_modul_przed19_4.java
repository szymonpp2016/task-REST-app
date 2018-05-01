package com.crud.tasks.controller;


import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
//@RestController
//@RequestMapping("/v1/trello")
public class TrelloController_modul_przed19_4 {

   // @Autowired
    private TrelloClient trelloClient;



 //   @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }
     /*   trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
            System.out.println("This board contains lists: ");
            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));


        });
    } */

  //  @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }







   /*     trelloBoards.stream()  //wersja z modulu 18.2
                .filter(board -> !board.getId().isEmpty())
                .filter(board-> !board.getName().isEmpty())
                .filter(board-> board.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() +
                        " " + trelloBoardDto.getName()));

        trelloBoards.forEach(trelloBoardDto  //wersja z modulu 18.1
        -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));
        */





}

