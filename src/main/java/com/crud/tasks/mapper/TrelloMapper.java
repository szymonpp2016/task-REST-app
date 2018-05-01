package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Component
public class TrelloMapper {
    public List<TrelloBoard> mapToBoards(List<TrelloBoardDto>  trelloBoardDtoList) {
             return trelloBoardDtoList.stream()
                .map(trelloBoardDto -> new TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(),
                                mapToList(trelloBoardDto.getLists())))
                .collect(toList());
    }

    public List<TrelloBoardDto> mapToBoardsDto(List<TrelloBoard>  trelloBoardList) {
           return trelloBoardList.stream()
                .map(trelloBoard ->  new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(),
                                mapToListDto(trelloBoard.getLists())))
                .collect(toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDtoList) {
            return trelloListDtoList.stream()
                .map(listDto -> new TrelloList(listDto.getId(), listDto.getName(), listDto.isClosed()))
                .collect(toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists) {
            return trelloLists.stream()
                .map(list ->  new TrelloListDto(list.getId(), list.getName(), list.isClosed()))
                .collect(toList());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
          return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(),
                trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
          return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(),
                trelloCardDto.getListId());
    }
}

