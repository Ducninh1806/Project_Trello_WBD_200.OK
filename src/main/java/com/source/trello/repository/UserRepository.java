package com.source.trello.repository;

import com.source.trello.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUserName(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String username);
}
