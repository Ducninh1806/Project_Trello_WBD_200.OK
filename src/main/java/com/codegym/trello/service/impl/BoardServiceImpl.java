package com.codegym.trello.service.impl;

import com.codegym.trello.model.Board;
import com.codegym.trello.repository.BoardRepository;
import com.codegym.trello.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Iterable<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public Board save(Board board) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
