package com.source.trello.service;

import com.source.trello.model.Card;
import com.source.trello.model.User;

import java.util.List;
import java.util.Optional;

public interface CardService {

    Iterable<Card> findAll();

    Optional<Card> findById(Long id );

    void save (Card card);

    void remove(Long id);

    List<Card> findAllByListSet_ListId(Long listId);

    List<Card> findAllByTitleContainingOrDescriptionContaining(String title, String description);

    List<Card> findAllByTitleContainingOrDescriptionContainingAndListSet_ListId(String title, String description, Long id);

    List<Card> findAllByUserSetCardContaining(User[] user);

    List<Card> findAllByColorsContaining(String[] colors);
}
