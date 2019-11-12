package com.source.trello.service;

import com.source.trello.model.Board;

import java.util.Optional;

public interface BoardService {

    Iterable<Board>findAll();

    Optional<Board> findById(Long id);

    void save (Board board);

    void remove (Long id);



}
