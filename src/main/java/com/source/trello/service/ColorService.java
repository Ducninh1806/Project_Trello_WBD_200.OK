package com.source.trello.service;

import com.source.trello.model.Board;
import com.source.trello.model.Card;
import com.source.trello.model.Color;
import com.source.trello.model.User;

import java.util.List;
import java.util.Optional;

public interface ColorService {
    Iterable<Color>findAll();

    Optional<Color> findById(Long id);

    void save (Color color);

    void remove (Long id);

    List<Color> findAllByCardColorSetContaining(Card card);

    List<Color> findAllByColorTypeContaining(String colorType);
}
