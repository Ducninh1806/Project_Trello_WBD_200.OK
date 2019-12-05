package com.source.trello.repository;

import com.source.trello.model.Notification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotiRepository extends PagingAndSortingRepository<Notification, Long> {

    List<Notification> findAllByUserCardNoti_UserId(Long userId);

    List<Notification> findAllByCardNoti_CardId(Long cardId);

    List<Notification> findAllByCardNoti_CardIdAndUserCardNoti_UserId(Long cardId, Long userId);
}
