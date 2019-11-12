package com.source.trello.controller;

import com.source.trello.model.ListCard;
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

    @GetMapping
    public ResponseEntity<List<ListCard>>getAllList(){
        List<ListCard> listCards =(List<ListCard>) listService.findALl();
        if (listCards.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listCards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListCard>getIdList(@PathVariable Long id){
        Optional<ListCard> listCard = listService.findById(id);
        if (listCard.isPresent()){
            return new ResponseEntity<>(listCard.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<ListCard> createList(@RequestBody ListCard listCard){
        ListCard currentList = listCard;
        listService.save(currentList);
        return new ResponseEntity<>(currentList, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListCard> updateList(@PathVariable Long id, @RequestBody ListCard listCard){
        Optional<ListCard> currentList = listService.findById(id);
        if (currentList.isPresent()){
            currentList.get().setListName(listCard.getListName());
            listService.save(currentList.get());
            return new ResponseEntity<>(currentList.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ListCard> deleteList(@PathVariable Long id){
        Optional<ListCard> currentList = listService.findById(id);
        if (currentList.isPresent()){
            listService.remove(id);
            return new ResponseEntity<>(currentList.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
