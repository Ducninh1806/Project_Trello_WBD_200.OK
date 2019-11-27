package com.source.trello.service.impl;

import com.source.trello.model.Card;
import com.source.trello.model.Comment;
import com.source.trello.repository.CommentRepository;
import com.source.trello.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAllByCardComment(Card card) {
        return commentRepository.findAllByCardComment(card);
    }

    @Override
    public List<Comment> findAllByCardCommentOrderByTimeDesc(Card card) {
        return commentRepository.findAllByCardCommentOrderByTimeDesc(card);
    }

}
