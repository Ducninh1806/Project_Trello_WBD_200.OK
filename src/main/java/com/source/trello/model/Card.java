package com.source.trello.model;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.Set;
>>>>>>> d724a50408ae8d86251d734e5771b2bbad9d8d22

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;
    private String title;
    private String description;
    private String[] colors;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_card",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSetCard;

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

    public Card(String title, String description, Set<User> userSetCard, ListCard listSet) {
        this.title = title;
        this.description = description;
        this.userSetCard = userSetCard;
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

<<<<<<< HEAD
    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
=======
    public Set<User> getUserSetCard() {
        return userSetCard;
    }

    public void setUserSetCard(Set<User> userSetCard) {
        this.userSetCard = userSetCard;
>>>>>>> d724a50408ae8d86251d734e5771b2bbad9d8d22
    }
}