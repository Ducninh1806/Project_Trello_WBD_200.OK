package com.source.trello.repository;

import com.source.trello.model.Card;
import com.source.trello.model.Color;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ColorRepository extends PagingAndSortingRepository<Color, Long> {
    List<Color> findAllByCardColorSetContaining(Card card);
}
