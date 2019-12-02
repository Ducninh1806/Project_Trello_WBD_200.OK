package com.source.trello.service;

import com.source.trello.model.Board;
import com.source.trello.model.Card;
import com.source.trello.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void remove(Long id);

    Optional<User> findByUserName(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String username);

    User findByEmail (String email);

    List<User> findAllByBoardSetContaining(Board board);

    List<User> findAllByUsernameContaining(String name);

    List<User> findAllByCardSetContaining(Card card);

}
