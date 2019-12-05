package com.source.trello.service.impl;

import com.source.trello.model.Card;
import com.source.trello.model.User;
import com.source.trello.repository.CardRepository;
import com.source.trello.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Iterable<Card> findAll() {
        return cardRepository.findAllByOrderByOrderNumberAsc();
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

    @Override
    public List<Card> findAllByListSet_ListId(Long listId) {
        return cardRepository.findAllByListSet_ListIdOrderByOrderNumberAsc(listId);
    }

    @Override
    public List<Card> findAllByTitleContainingOrDescriptionContaining(String title, String description) {
        return cardRepository.findAllByTitleContainingOrDescriptionContaining(title, description);
    }

    @Override
    public List<Card> findAllByTitleContainingOrDescriptionContainingAndListSet_ListId(String title, String description, Long id) {
        return cardRepository.findAllByTitleContainingOrDescriptionContainingAndListSet_ListId(title, description, id);
    }

    @Override
    public List<Card> findAllByUserSetCardContaining(User user) {
        return cardRepository.findAllByUserSetCardContaining(user);
    }

    @Override
    public List<Card> findAllByColorsContaining(String[] colors) {
        return cardRepository.findAllByColors(colors);
    }
}
