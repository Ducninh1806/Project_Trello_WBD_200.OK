package com.source.trello.controller;

import com.source.trello.model.Notification;
import com.source.trello.service.NotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    
    @Autowired
    private NotiService notiService;
    
    @GetMapping()
    public ResponseEntity<List<Notification>> findAll(){
        List<Notification> cardNotifications= (List<Notification>) notiService.findAll();
        if(cardNotifications.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cardNotifications, HttpStatus.OK);
    }
    
    @GetMapping("/{id}") 
    public ResponseEntity<Notification> findByIdCardNotification(@PathVariable Long id) {
        Optional<Notification> cardNotification = notiService.findById(id);
        if (cardNotification.isPresent()) {
            return new ResponseEntity<>(cardNotification.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Notification> createCardNotification(@RequestBody Notification cardNotification) {
        notiService.save(cardNotification);
        return new ResponseEntity<>(cardNotification, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateCardNotification(@PathVariable Long id, @RequestBody Notification cardNotification) {
        Optional<Notification> currentCardNotification = notiService.findById(id);
        if (currentCardNotification.isPresent()) {
            currentCardNotification.get().setId(cardNotification.getId());
            currentCardNotification.get().setType(cardNotification.getType());
            currentCardNotification.get().setUserCardNoti(cardNotification.getUserCardNoti());
            currentCardNotification.get().setCardNoti(cardNotification.getCardNoti());
            notiService.save(currentCardNotification.get());
            return new ResponseEntity<>(currentCardNotification.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notification> deleteCard(@PathVariable Long id) {
        Optional<Notification> cardNotification = notiService.findById(id);
        if (cardNotification.isPresent()) {
            notiService.remove(id);
            return new ResponseEntity<>(cardNotification.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //----------------------------find all by user----------------------------------
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Notification>> findAllByUser(@PathVariable Long id){
        List<Notification> cardNotifications= notiService.findAllByUserCardNoti_UserId(id);
        if(cardNotifications.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cardNotifications, HttpStatus.OK);
    }

    // -----------------------find all by card --------------------------------------
    @GetMapping("/card/{id}")
    public ResponseEntity<List<Notification>> findAllByCard(@PathVariable Long id){
        List<Notification> cardNotifications= notiService.findAllByCardNoti_CardId(id);
        if(cardNotifications.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cardNotifications, HttpStatus.OK);
    }

    // ----------------------find all by card and user ---------------------------------
    @GetMapping("/card/user")
    public ResponseEntity<List<Notification>> findAllByCardAndUSer(@RequestParam Long cardId, @RequestParam Long userId){
        List<Notification> cardNotifications = notiService.findAllByCardNoti_CardIdAndUserCardNoti_UserId(cardId, userId);
        if(cardNotifications.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cardNotifications, HttpStatus.OK);
    }

}
