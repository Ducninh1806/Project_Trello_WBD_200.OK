package com.source.trello.service;

import com.source.trello.model.Card;
import com.source.trello.model.Color;
import com.source.trello.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CardService {

    Iterable<Card> findAll();

    Optional<Card> findById(Long id );

    void save (Card card);

    void remove(Long id);

    List<Card> findAllByListSet_ListId(Long listId);

    List<Card> findAllByTitleContainingOrDescriptionContaining(String title, String description);

    List<Card> findAllByTitleContainingOrDescriptionContainingAndListSet_ListId(String title, String description, Long id);

    List<Card> findAllByUserSetCardContainingAndListSet_ListId(User user, Long id);

    List<Card> findAllByColorsContaining(String[] colors);

    List<Card> findAllByColorSetContainsAndListSet_ListId(Color colors, Long id);

}
