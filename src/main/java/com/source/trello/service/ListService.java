package com.source.trello.service;

import com.source.trello.model.ListCard;

import java.util.Optional;

public interface ListService {

    Iterable<ListCard> findALl();

    Optional<ListCard> findById(Long id);

    void save (ListCard listCard);

    void remove (Long id);



}
