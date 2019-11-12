package com.source.trello.service.impl;

import com.source.trello.model.Card;
import com.source.trello.repository.CardRepository;
import com.source.trello.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Iterable<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void remove(Long id) {
        cardRepository.deleteById(id);
    }
}
