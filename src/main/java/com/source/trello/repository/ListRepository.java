package com.source.trello.repository;

import com.source.trello.model.ListCard;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends PagingAndSortingRepository<ListCard, Long> {
}
