package com.source.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "list")
public class ListCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long listId;

    private String listName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardId")
    private Board boardSet;

    @JsonIgnore
    @OneToMany(targetEntity = Card.class,fetch = FetchType.EAGER, mappedBy = "listSet", cascade = CascadeType.ALL)
    private Set<Card> cardSet;

    public ListCard() {
    }

    public ListCard(String listName) {
        this.listName = listName;
    }

    public ListCard(String listName, Board boardSet, Set<Card> cardSet) {
        this.listName = listName;
        this.boardSet = boardSet;
        this.cardSet = cardSet;
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

    public Board getBoardSet() {
        return boardSet;
    }

    public void setBoardSet(Board boardSet) {
        this.boardSet = boardSet;
    }

    public Set<Card> getCardSet() {
        return cardSet;
    }

    public void setCardSet(Set<Card> cardSet) {
        this.cardSet = cardSet;
    }
}