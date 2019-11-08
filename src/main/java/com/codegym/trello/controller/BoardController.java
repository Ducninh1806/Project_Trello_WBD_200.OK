package com.codegym.trello.controller;

import com.codegym.trello.model.Board;
import com.codegym.trello.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<List<Board>>findAllBoard(){
        List<Board> boards =(List<Board>) boardService.findAll();
        if (boards.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getIdBoard(@PathVariable Long id){
        Optional<Board> board = boardService.findById(id);
        if (board.isPresent()){
            return new ResponseEntity<>(board.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board){
       Board currentBoard= boardService.save(board);
        return new ResponseEntity<>(currentBoard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board board){
        Optional<Board> currentBoard = boardService.findById(id);
        if (!currentBoard.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentBoard.get().setBoardName(board.getBoardName());
        boardService.save(currentBoard.get());
        return new ResponseEntity<>(currentBoard.get(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable Long id){
        Optional<Board> board =  boardService.findById(id);
        if (board.isPresent()){
             boardService.remove(id);
            return new ResponseEntity<>(board.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
