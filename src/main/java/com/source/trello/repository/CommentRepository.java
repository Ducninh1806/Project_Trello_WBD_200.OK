package com.source.trello.repository;

import com.source.trello.model.Card;
import com.source.trello.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    List<Comment> findAllByCardComment(Card card);

    List<Comment> findAllByCardCommentOrderByTimeDesc(Card card);
}
