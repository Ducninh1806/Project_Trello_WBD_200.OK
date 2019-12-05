package com.source.trello.controller;

import com.source.trello.model.Card;
import com.source.trello.model.User;
import com.source.trello.service.CardService;
import com.source.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Card>> findAllCard() {
        List<Card> cards = (List<Card>) cardService.findAll();
        if (cards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findByIdCard(@PathVariable Long id) {
        Optional<Card> card = cardService.findById(id);
        if (card.isPresent()) {
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        cardService.save(card);
        card.setOrderNumber(card.getCardId());
        cardService.save(card);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card card) {
        Optional<Card> currentCard = cardService.findById(id);
        if (currentCard.isPresent()) {

            currentCard.get().setCardId(card.getCardId());
            currentCard.get().setTitle(card.getTitle());
            currentCard.get().setDescription(card.getDescription());
            currentCard.get().setListSet(card.getListSet());
            currentCard.get().setUserSetCard(card.getUserSetCard());
            currentCard.get().setColors(card.getColors());
            currentCard.get().setColorSet(card.getColorSet());
            currentCard.get().setOrderNumber(card.getOrderNumber());

            cardService.save(currentCard.get());
            return new ResponseEntity<>(currentCard.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Card> deleteCard(@PathVariable Long id) {
        Optional<Card> card = cardService.findById(id);
        if (card.isPresent()) {
            cardService.remove(id);
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //-----------------------find all by list----------------------
    @GetMapping("/list/{id}")
    public ResponseEntity<List<Card>> findAllCardByList(@PathVariable Long id) {
        List<Card> cards = cardService.findAllByListSet_ListId(id);
        if (cards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PutMapping("/updateColor/{id}")
    public ResponseEntity<?> updateColor(@RequestBody Card card, @PathVariable Long id) {
        Optional<Card> card1 = cardService.findById(id);
        if (!card1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        card1.get().setColors(card.getColors());
        cardService.save(card1.get());
        return new ResponseEntity<>(card1, HttpStatus.OK);
    }

    //---------------------search card by title or description by List id-------------------------
    @GetMapping("/card/{id}")
    public ResponseEntity<List<Card>> findAllCardBySearch(@RequestParam String searchWord, @PathVariable Long id) {
        List<Card> cards = cardService.findAllByTitleContainingOrDescriptionContainingAndListSet_ListId(searchWord, searchWord, id);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    //-------------------------------search card by users-------------------------------------------
    @PostMapping("/user")
    public ResponseEntity<List<Card>> findAllCardByUser(@RequestBody User user) {
        List<Card> cardList = cardService.findAllByUserSetCardContaining(user);
        return new ResponseEntity<>(cardList, HttpStatus.OK);
    }

    //-------------------------------search card by colors------------------------------------------
    @PostMapping("/color")
    public ResponseEntity<List<Card>> findAllCardByColor(@RequestBody String[] colors) {
        List<Card> cardList = cardService.findAllByColorsContaining(colors);
        return new ResponseEntity<>(cardList, HttpStatus.OK);
    }

}
