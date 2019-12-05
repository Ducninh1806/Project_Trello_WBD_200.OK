package com.source.trello.model;

import javax.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    @ManyToOne()
    @JoinColumn(name = "card_id")
    private Card cardNoti;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User userCardNoti;

    public Notification() {
    }

    public Notification(String type, Card cardNoti, User userCardNoti) {
        this.type = type;
        this.cardNoti = cardNoti;
        this.userCardNoti = userCardNoti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Card getCardNoti() {
        return cardNoti;
    }

    public void setCardNoti(Card cardNoti) {
        this.cardNoti = cardNoti;
    }

    public User getUserCardNoti() {
        return userCardNoti;
    }

    public void setUserCardNoti(User userCardNoti) {
        this.userCardNoti = userCardNoti;
    }
}
