package com.source.trello.service;

import com.source.trello.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotiService {

    Iterable<Notification> findAll();

    Optional<Notification> findById(Long id );

    void save (Notification cardNotification);

    void remove(Long id);

    List<Notification> findAllByUserCardNoti_UserId(Long userId);

    List<Notification> findAllByCardNoti_CardId(Long cardId);

    List<Notification> findAllByCardNoti_CardIdAndUserCardNoti_UserId(Long cardId, Long userId);

}
