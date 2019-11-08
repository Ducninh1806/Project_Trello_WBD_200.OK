package com.codegym.trello.service;

import com.codegym.trello.model.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void remove(Long id);
}
