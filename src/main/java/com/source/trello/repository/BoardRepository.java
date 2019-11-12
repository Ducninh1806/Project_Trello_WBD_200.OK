package com.source.trello.repository;

import com.source.trello.model.Board;
import com.source.trello.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {

    List<Board> findAllByUserSetContaining(User user);

    List<Board> findAllByUserSetContainingOrderByTimeDesc(User user);
}
