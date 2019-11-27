package com.source.trello.controller;

import com.source.trello.model.Board;
import com.source.trello.model.User;
import com.source.trello.service.BoardService;
import com.source.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Board>> findAllBoard() {
        List<Board> boards = (List<Board>) boardService.findAll();
        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getIdBoard(@PathVariable Long id) {
        Optional<Board> board = boardService.findById(id);
        if (board.isPresent()) {
            return new ResponseEntity<>(board.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        board.setTime(new Date());
        boardService.save(board);
        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board board) {
        Optional<Board> currentBoard = boardService.findById(id);
        if (!currentBoard.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentBoard.get().setBoardId(board.getBoardId());
        currentBoard.get().setBoardName(board.getBoardName());
        currentBoard.get().setUserSet(board.getUserSet());
        currentBoard.get().setListSet(board.getListSet());
        currentBoard.get().setTime(new Date());

        boardService.save(currentBoard.get());
        return new ResponseEntity<>(currentBoard.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable Long id) {
        Optional<Board> board = boardService.findById(id);
        if (board.isPresent()) {
            boardService.remove(id);
            return new ResponseEntity<>(board.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //----------------------find all board by user------------------------

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Board>> findAllBoardByUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        List<Board> boards = (List<Board>) boardService.findAllByUserSetIsContaining(user.get());
        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    //-----------------find all order by time ---------------------------

    @GetMapping("/user/{id}/time")
    public ResponseEntity<List<Board>> findAllBoardByUserOrderTime(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        List<Board> boards = (List<Board>) boardService.findAllByUserSetContainingOrderByTime(user.get());
        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }
}
