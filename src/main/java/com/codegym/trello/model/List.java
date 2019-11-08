package com.codegym.trello.model;

import javax.persistence.*;

@Entity
@Table(name = "list")
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long listId;
    private String listName;

    @OneToMany(targetEntity = Card.class)
    private java.util.List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    public List() {
    }

    public List(String listName) {
        this.listName = listName;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
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