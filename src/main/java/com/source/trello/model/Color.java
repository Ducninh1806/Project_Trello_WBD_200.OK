package com.source.trello.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long colorId;

    private String colorType;

    @ManyToMany(targetEntity = Card.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "color_card",
            joinColumns = @JoinColumn(name = "color_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    Set<Card> cardColorSet;

    public Color() {
    }

    public Color(String colorType) {
        this.colorType = colorType;
    }

    public Color(String colorType, Set<Card> cardColorSet) {
        this.colorType = colorType;
        this.cardColorSet = cardColorSet;
    }

    public Set<Card> getCardColorSet() {
        return cardColorSet;
    }

    public void setCardColorSet(Set<Card> cardColorSet) {
        this.cardColorSet = cardColorSet;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }
}
