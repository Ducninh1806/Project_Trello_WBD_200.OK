package com.source.trello.controller;

import com.source.trello.model.Card;
import com.source.trello.model.Comment;
import com.source.trello.service.CardService;
import com.source.trello.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CardService cardService;

    @GetMapping
    public ResponseEntity<List<Comment>> findAllComment() {
        List<Comment> comments = (List<Comment>) commentService.findAll();
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getIdComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        comment.setTime(new Date());
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> currentComment = commentService.findById(id);
        if (!currentComment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentComment.get().setId(comment.getId());
        currentComment.get().setCommentLine(comment.getCommentLine());
        currentComment.get().setCardComment(comment.getCardComment());
        currentComment.get().setUserComment(comment.getUserComment());
        currentComment.get().setTime(new Date());

        commentService.save(currentComment.get());
        return new ResponseEntity<>(currentComment.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        Optional<Comment> Comment = commentService.findById(id);
        if (Comment.isPresent()) {
            commentService.remove(id);
            return new ResponseEntity<>(Comment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //-------------------------find comment by card-------------------------------
    @GetMapping("/card/{id}")
    public ResponseEntity<List<Comment>> findAllByCard(@PathVariable Long id) {
        Optional<Card> card = cardService.findById(id);
        if (card.isPresent()) {
            List<Comment> comments = commentService.findAllByCardCommentOrderByTimeDesc(card.get());
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
