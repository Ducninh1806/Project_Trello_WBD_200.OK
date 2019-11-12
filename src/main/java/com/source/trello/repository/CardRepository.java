package com.source.trello.repository;

import com.source.trello.model.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {

    List<Card> findAllByListSet_ListId(Long listId);
}
