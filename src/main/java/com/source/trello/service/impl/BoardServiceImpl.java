package com.source.trello.service.impl;

import com.source.trello.model.Board;
import com.source.trello.model.User;
import com.source.trello.repository.BoardRepository;
import com.source.trello.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void remove(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public List<Board> findAllByUserSetIsContaining(User user) {
        return boardRepository.findAllByUserSetContaining(user);
    }

    @Override
    public List<Board> findAllByUserSetContainingOrderByTime(User user) {
        return boardRepository.findAllByUserSetContainingOrderByTimeDesc(user);
    }


}
