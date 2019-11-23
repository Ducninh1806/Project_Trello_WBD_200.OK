package com.source.trello.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;
    private String title;
    private String description;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_card",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSetCard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ListCardId")
    private ListCard listSet;

    private String color;

    public Card() {
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

    public Card(String title, String description, Set<User> userSetCard, ListCard listSet) {
        this.title = title;
        this.description = description;
        this.userSetCard = userSetCard;
        this.listSet = listSet;
    }

    public Card(String title, String description, Set<User> userSetCard, ListCard listSet, String color) {
        this.title = title;
        this.description = description;
        this.userSetCard = userSetCard;
        this.listSet = listSet;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Set<User> getUserSetCard() {
        return userSetCard;
    }

    public void setUserSetCard(Set<User> userSetCard) {
        this.userSetCard = userSetCard;
    }
}