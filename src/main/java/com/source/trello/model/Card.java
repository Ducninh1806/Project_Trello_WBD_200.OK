package com.source.trello.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;
    private String title;
    private String description;
    private String[] colors;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ListCardId")
    private ListCard listSet;

    public Card() {
    }

    public Card(String title, String description, String[] colors, ListCard listSet) {
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.listSet = listSet;
    }

    public Card(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Card(String title, String description, ListCard listSet) {
        this.title = title;
        this.description = description;
        this.listSet = listSet;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
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

    public ListCard getListSet() {
        return listSet;
    }

    public void setListSet(ListCard listSet) {
        this.listSet = listSet;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }
}