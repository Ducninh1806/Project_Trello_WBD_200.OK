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
        listService.save(listCard);
        listCard.setOrderNumber(listCard.getListId());
        listService.save(listCard);
        return new ResponseEntity<>(listCard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListCard> updateList(@PathVariable Long id, @RequestBody ListCard listCard) {
        Optional<ListCard> currentList = listService.findById(id);
        if (currentList.isPresent()) {
            currentList.get().setListId(listCard.getListId());
            currentList.get().setListName(listCard.getListName());
            currentList.get().setOrderNumber(listCard.getOrderNumber());
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

    //---------------------------swap listId bewteen two cardSet----------------------------------
    @PutMapping("/cardSwap")
    public ResponseEntity<ListCard> updateListAndCard(@RequestParam Long previousId, Long currentId) {
        List<Card> cards1 = cardService.findAllByListSet_ListId(previousId);
        List<Card> cards2 = cardService.findAllByListSet_ListId(currentId);
        Optional<ListCard> listCard1 = listService.findById(previousId);
        Optional<ListCard> listCard2 = listService.findById(currentId);

        for (Card card : cards1) {
            card.setListSet(listCard2.get());
            cardService.save(card);
        }

        for (Card card : cards2) {
            card.setListSet(listCard1.get());
            cardService.save(card);
        }
        return new ResponseEntity<ListCard>(listCard1.get(), HttpStatus.OK);
    }

 //-------------------------------change detail of list---------------------------------------------
 @PutMapping("/listSwap")
 public ResponseEntity<ListCard> swapListDetail(@RequestParam Long previousId, Long currentId) {
     Optional<ListCard> listCard1 = listService.findById(previousId);
     Optional<ListCard> listCard2 = listService.findById(currentId);

     ListCard listCard = new ListCard();

     listCard.setListName(listCard1.get().getListName());

     listCard1.get().setListName(listCard2.get().getListName());
     listCard1.get().setBoardSet(listCard2.get().getBoardSet());

     listService.save(listCard1.get());

     listCard2.get().setListName(listCard.getListName());

     listService.save(listCard2.get());

     return new ResponseEntity<>(listCard1.get(), HttpStatus.OK);
 }


}
