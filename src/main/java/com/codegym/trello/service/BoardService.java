package com.codegym.trello.service;

import com.codegym.trello.model.Board;

import java.util.Optional;

public interface BoardService {

    Iterable<Board>findAll();

    Optional<Board> findById(Long id);

    Board save (Board board);

    void remove (Long id);



}
