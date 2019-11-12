package com.source.trello.service;

import com.source.trello.model.Role;
import com.source.trello.model.RoleName;

import java.util.Optional;

public interface RoleService {

    Iterable<Role> findAll();

    Optional<Role> findById(Long id);

    Role save(Role role);

    void remove(Long id);

    Optional<Role> findByName(RoleName roleName);

}
