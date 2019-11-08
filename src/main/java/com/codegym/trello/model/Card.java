package com.codegym.trello.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cardId;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "listID")
    private List list;

    public Card() {
    }

    public Card(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}