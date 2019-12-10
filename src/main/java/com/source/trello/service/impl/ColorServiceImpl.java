package com.source.trello.service.impl;

import com.source.trello.model.Card;
import com.source.trello.model.Color;
import com.source.trello.repository.ColorRepository;
import com.source.trello.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Iterable<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Optional<Color> findById(Long id) {
        return colorRepository.findById(id);
    }

    @Override
    public void save(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void remove(Long id) {
        colorRepository.deleteById(id);
    }

    @Override
    public List<Color> findAllByCardColorSetContaining(Card card) {
        return colorRepository.findAllByCardColorSetContaining(card);
    }

    @Override
    public List<Color> findAllByColorTypeContaining(String colorType) {
        return colorRepository.findAllByColorTypeContaining(colorType);
    }


}
