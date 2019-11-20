package com.source.trello.service;

import com.source.trello.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    Iterable<Card> findAll();

    Optional<Card> findById(Long id );

    void save (Card card);

    void remove(Long id);

    List<Card> findAllByListSet_ListId(Long listId);

    List<Card> findAllByTitleOrDescription(String title, String description);

}
