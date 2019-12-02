package com.source.trello.service;

import com.source.trello.model.Card;
import com.source.trello.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Iterable<Comment> findAll();

    Optional<Comment> findById(Long id);

    void save (Comment comment);

    void remove (Long id);

    List<Comment> findAllByCardComment(Card card);

    List<Comment> findAllByCardCommentOrderByTimeDesc(Card card);

}
