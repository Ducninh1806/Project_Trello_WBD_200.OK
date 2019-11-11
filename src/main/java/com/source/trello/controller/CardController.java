package com.source.trello.controller;

import com.source.trello.model.Card;
import com.source.trello.service.CardService;
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

    @GetMapping
    public ResponseEntity<List<Card>> findAllCard(){
        List<Card> cards =(List<Card>) cardService.findAll();
        if (cards.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findByIdCard(@PathVariable Long id){
        Optional<Card> card = cardService.findById(id);
        if (card.isPresent()){
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card){
         cardService.save(card);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card card){
        Optional<Card> currentCard = cardService.findById(id);
        if (currentCard.isPresent()){
            currentCard.get().setTitle(card.getTitle());
            currentCard.get().setDescription(card.getDescription());
            cardService.save(currentCard.get());
            return new ResponseEntity<>(currentCard.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Card> deleteCard(@PathVariable Long id){
        Optional<Card> card = cardService.findById(id);
        if (card.isPresent()){
            cardService.remove(id);
            return new ResponseEntity<>(card.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
