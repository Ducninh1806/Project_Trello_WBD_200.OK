package com.source.trello.repository;

import com.source.trello.model.Card;
import com.source.trello.model.Color;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends PagingAndSortingRepository<Color, Long> {
    List<Color> findAllByCardColorSetContaining(Card card);

    List<Color> findAllByColorTypeContaining(String colorType);

}
