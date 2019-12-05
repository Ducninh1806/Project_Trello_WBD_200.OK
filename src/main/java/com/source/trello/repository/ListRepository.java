package com.source.trello.repository;

import com.source.trello.model.ListCard;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository extends PagingAndSortingRepository<ListCard, Long> {

    List<ListCard> findAllByBoardSet_BoardIdOrderByOrderNumber(Long boardId);

    List<ListCard> findAllByOrderByOrderNumberAsc();
}
