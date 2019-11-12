package com.source.trello.repository;

import com.source.trello.model.Role;
import com.source.trello.model.RoleName;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends PagingAndSortingRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
