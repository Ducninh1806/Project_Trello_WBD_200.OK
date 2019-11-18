package com.source.trello.repository;

import com.source.trello.model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findAllByConfirmationToken(String confirmationToken);
}
