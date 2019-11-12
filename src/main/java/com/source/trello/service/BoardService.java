package com.source.trello.service;

import com.source.trello.model.Board;
import com.source.trello.model.User;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    Iterable<Board>findAll();

    Optional<Board> findById(Long id);

    void save (Board board);

    void remove (Long id);

    List<Board> findAllByUserSetIsContaining(User user);

    List<Board> findAllByUserSetContainingOrderByTime(User user);

}
