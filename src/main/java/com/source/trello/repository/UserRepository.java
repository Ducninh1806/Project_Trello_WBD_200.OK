package com.source.trello.repository;

import com.source.trello.model.Board;
import com.source.trello.model.Card;
import com.source.trello.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    User findByEmail (String email);

    List<User> findAllByBoardSetContaining(Board board);

    List<User> findAllByUsernameContaining(String name);

    List<User> findAllByCardSetContaining(Card card);

    List<User> findAllByUsernameContainingAndBoardSetContaining(String name, Board board);
}
