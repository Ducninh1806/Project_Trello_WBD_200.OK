package com.source.trello.service.impl;

import com.source.trello.model.ListCard;
import com.source.trello.repository.ListRepository;
import com.source.trello.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListRepository listRepository;


    @Override
    public Iterable<ListCard> findALl() {
        return listRepository.findAll();
    }

    @Override
    public Optional<ListCard> findById(Long id) {
        return listRepository.findById(id);
    }

    @Override
    public void save(ListCard listCard) {
        listRepository.save(listCard);
    }

    @Override
    public void remove(Long id) {
        listRepository.deleteById(id);
    }
}
