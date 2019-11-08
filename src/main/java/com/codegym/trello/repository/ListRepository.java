package com.codegym.trello.repository;

import com.codegym.trello.model.ListCard;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends PagingAndSortingRepository<ListCard, Long> {
}
