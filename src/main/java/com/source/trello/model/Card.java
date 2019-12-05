package com.source.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String[] colors;

    private Long orderNumber;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_card",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSetCard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ListCardId")
    private ListCard listSet;

    @JsonIgnore
    @OneToMany(targetEntity = Comment.class,fetch = FetchType.EAGER, mappedBy = "cardComment")
    private Set<Comment> commentSet;

    @JsonIgnore
    @ManyToMany(mappedBy = "cardColorSet", fetch = FetchType.EAGER)
    private Set<Color> colorSet;

    @JsonIgnore
    @OneToMany(targetEntity = FileUpload.class, fetch = FetchType.EAGER, mappedBy = "card")
    private Set<FileUpload> fileUploadSet;

    @JsonIgnore
    @OneToMany(targetEntity = Notification.class, fetch = FetchType.EAGER, mappedBy = "cardNoti")
    private Set<Notification> cardNotifications;

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

    public Card(String title, String description, String[] colors, Set<User> userSetCard, ListCard listSet) {
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.userSetCard = userSetCard;
        this.listSet = listSet;
    }

    public Card(String title, String description, String[] colors, Set<User> userSetCard, ListCard listSet, Set<Color> colorSet) {
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.userSetCard = userSetCard;
        this.listSet = listSet;
        this.colorSet = colorSet;
    }


    public Card(String title, String description, String[] colors, Set<User> userSetCard, ListCard listSet, Set<Comment> commentSet, Set<Color> colorSet) {
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.userSetCard = userSetCard;
        this.listSet = listSet;
        this.commentSet = commentSet;
        this.colorSet = colorSet;
    }

    public Card(String title, String description, String[] colors, Set<User> userSetCard, ListCard listSet, Set<Comment> commentSet, Set<Color> colorSet, Set<FileUpload> fileUploadSet) {
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.userSetCard = userSetCard;
        this.listSet = listSet;
        this.commentSet = commentSet;
        this.colorSet = colorSet;
        this.fileUploadSet = fileUploadSet;
    }

    public Card(String title, String description, String[] colors, Long orderNumber, Set<User> userSetCard, ListCard listSet, Set<Comment> commentSet, Set<Color> colorSet, Set<FileUpload> fileUploadSet) {
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.orderNumber = orderNumber;
        this.userSetCard = userSetCard;
        this.listSet = listSet;
        this.commentSet = commentSet;
        this.colorSet = colorSet;
        this.fileUploadSet = fileUploadSet;
    }

    public Card(String title, String description, String[] colors, Long orderNumber, Set<User> userSetCard, ListCard listSet, Set<Comment> commentSet, Set<Color> colorSet, Set<FileUpload> fileUploadSet, Set<Notification> cardNotifications) {
        this.title = title;
        this.description = description;
        this.colors = colors;
        this.orderNumber = orderNumber;
        this.userSetCard = userSetCard;
        this.listSet = listSet;
        this.commentSet = commentSet;
        this.colorSet = colorSet;
        this.fileUploadSet = fileUploadSet;
        this.cardNotifications = cardNotifications;
    }

    public Set<Notification> getCardNotifications() {
        return cardNotifications;
    }

    public void setCardNotifications(Set<Notification> cardNotifications) {
        this.cardNotifications = cardNotifications;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Set<FileUpload> getFileUploadSet() {
        return fileUploadSet;
    }

    public void setFileUploadSet(Set<FileUpload> fileUploadSet) {
        this.fileUploadSet = fileUploadSet;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public Set<Color> getColorSet() {
        return colorSet;
    }

    public void setColorSet(Set<Color> colorSet) {
        this.colorSet = colorSet;
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

    public Set<User> getUserSetCard() {
        return userSetCard;
    }

    public void setUserSetCard(Set<User> userSetCard) {
        this.userSetCard = userSetCard;
    }
}