package com.source.trello.controller;

import com.source.trello.model.Card;
import com.source.trello.model.ListCard;
import com.source.trello.service.CardService;
import com.source.trello.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lists")
public class ListController {

    @Autowired
    private ListService listService;

    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<List<ListCard>> getAllList() {
        List<ListCard> listCards = (List<ListCard>) listService.findALl();
        if (listCards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listCards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListCard> getIdList(@PathVariable Long id) {
        Optional<ListCard> listCard = listService.findById(id);
        if (listCard.isPresent()) {
            return new ResponseEntity<>(listCard.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<ListCard> createList(@RequestBody ListCard listCard) {
        ListCard currentList = listCard;
        listService.save(currentList);
        return new ResponseEntity<>(currentList, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListCard> updateList(@PathVariable Long id, @RequestBody ListCard listCard) {
        Optional<ListCard> currentList = listService.findById(id);
        if (currentList.isPresent()) {
            currentList.get().setListId(listCard.getListId());
            currentList.get().setListName(listCard.getListName());
            currentList.get().setBoardSet(listCard.getBoardSet());
//            currentList.get().setCardSet(listCard.getCardSet());
            listService.save(currentList.get());
            return new ResponseEntity<>(currentList.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Long id) {
        Optional<ListCard> currentList = listService.findById(id);
        if (currentList.isPresent()) {
            listService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //-----------------------find all by board----------------------
    @GetMapping("/board/{id}")
    public ResponseEntity<List<ListCard>> getAllListByBoard(@PathVariable Long id) {
        List<ListCard> listCards = listService.findAllByBoardSet_BoardId(id);
        if (listCards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listCards, HttpStatus.OK);
    }

    @PutMapping("/card")
    public ResponseEntity<ListCard> updateListAndCard(@RequestParam Long previousId, Long currentId) {

        Optional<ListCard> listCard = listService.findById(currentId);
        Optional<ListCard> listCard2 = listService.findById(previousId);
        List<Card> cards = cardService.findAllByListSet_ListId(previousId);
        List<Card> cards1 = cardService.findAllByListSet_ListId(currentId);

        ListCard listCardMid = new ListCard();
        listCardMid.setListName(listCard.get().getListName());
        listCardMid.setBoardSet(listCard.get().getBoardSet());

        listCard.get().setListName(listCard2.get().getListName());
        listCard.get().setBoardSet(listCard2.get().getBoardSet());
        listService.save(listCard.get());

        listCard2.get().setListName(listCardMid.getListName());
        listCard2.get().setBoardSet(listCardMid.getBoardSet());
        listService.save(listCard2.get());


        for (Card value : cards) {
            value.setListSet(listCard.get());
            cardService.save(value);
        }

        for (Card value : cards1) {
            value.setListSet(listCard2.get());
            cardService.save(value);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
