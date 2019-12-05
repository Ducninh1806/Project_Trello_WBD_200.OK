package com.source.trello.service.impl;

import com.source.trello.model.Notification;
import com.source.trello.repository.NotiRepository;
import com.source.trello.service.NotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotiServiceImpl implements NotiService {
    @Autowired
    private NotiRepository notiRepository;

    @Override
    public Iterable<Notification> findAll() {
        return notiRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notiRepository.findById(id);
    }

    @Override
    public void save(Notification cardNotification) {
        notiRepository.save(cardNotification);
    }

    @Override
    public void remove(Long id) {
        notiRepository.deleteById(id);
    }

    @Override
    public List<Notification> findAllByUserCardNoti_UserId(Long userId) {
        return notiRepository.findAllByUserCardNoti_UserId(userId);
    }

    @Override
    public List<Notification> findAllByCardNoti_CardId(Long cardId) {
        return notiRepository.findAllByCardNoti_CardId(cardId);
    }

    @Override
    public List<Notification> findAllByCardNoti_CardIdAndUserCardNoti_UserId(Long cardId, Long userId) {
        return notiRepository.findAllByCardNoti_CardIdAndUserCardNoti_UserId(cardId, userId);
    }


}
