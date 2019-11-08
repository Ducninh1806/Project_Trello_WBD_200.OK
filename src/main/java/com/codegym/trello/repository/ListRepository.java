package com.codegym.trello.repository;

import com.codegym.trello.model.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends PagingAndSortingRepository<List, Long> {
}
