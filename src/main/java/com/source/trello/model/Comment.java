package com.source.trello.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String commentLine;

    @ManyToOne
    @JoinColumn(name = "cardId")
    private Card cardComment;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userComment;

    public Comment(){}

    public Date time;

    public Comment(String commentLine, Card cardComment, User userComment) {
        this.commentLine = commentLine;
        this.cardComment = cardComment;
        this.userComment = userComment;
    }

    public Card getCardComment() {
        return cardComment;
    }

    public Comment(String commentLine, Card cardComment, User userComment, Date time) {
        this.commentLine = commentLine;
        this.cardComment = cardComment;
        this.userComment = userComment;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setCardComment(Card cardComment) {
        this.cardComment = cardComment;
    }

    public User getUserComment() {
        return userComment;
    }

    public void setUserComment(User userComment) {
        this.userComment = userComment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentLine() {
        return commentLine;
    }

    public void setCommentLine(String commentLine) {
        this.commentLine = commentLine;
    }

}
