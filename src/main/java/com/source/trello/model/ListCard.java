package com.source.trello.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "list")
public class ListCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long listId;
    private String listName;

    @OneToMany(targetEntity = Card.class)
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    public ListCard() {
    }

    public ListCard(String listName, Board board) {
        this.listName = listName;
        this.board = board;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public java.util.List<Card> getCards() {
        return cards;
    }

    public void setCards(java.util.List<Card> cards) {
        this.cards = cards;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}