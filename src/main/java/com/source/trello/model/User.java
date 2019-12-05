package com.source.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 60)
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "userSet", fetch = FetchType.EAGER)
    private Set<Board> boardSet;

    @JsonIgnore
    @ManyToMany(mappedBy = "userSetCard", fetch = FetchType.EAGER)
    private Set<Card> cardSet;

    @JsonIgnore
    @OneToMany(targetEntity = Comment.class,fetch = FetchType.EAGER, mappedBy = "userComment")
    private Set<Comment> commentSet;

    private String emailId;

    @JsonIgnore
    @OneToMany(targetEntity = Notification.class, fetch = FetchType.EAGER, mappedBy = "userCardNoti")
    private Set<Notification> cardNotificationSet;

    private Long[] cardNoti;

    private String avatarLink;

    public User(String username, String email, String password, Set<Board> boardSet) {
        this.username = username;
        this.email = email;

        this.password = password;
        this.boardSet = boardSet;
    }

    public User(@NotBlank @Size(min = 2, max = 50) String userName, @NotBlank @Size(max = 60) String email, @NotBlank @Size(min = 3) String password) {
        this.username = userName;
        this.email = email;
        this.password = password;
    }

    public User(@NotBlank @Size(min = 2, max = 50) String username, @NotBlank @Size(max = 60) String email, @NotBlank @Size(min = 3) String password, Set<Role> roles, Set<Board> boardSet) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.boardSet = boardSet;
    }

    public User(@NotBlank @Size(min = 2, max = 50) String username, @NotBlank @Size(max = 60) String email, @NotBlank @Size(min = 3) String password, Set<Role> roles, Set<Board> boardSet, Set<Card> cardSet, String emailId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.boardSet = boardSet;
        this.cardSet = cardSet;
        this.emailId = emailId;
    }

    public User(@NotBlank @Size(min = 2, max = 50) String username, @NotBlank @Size(max = 60) String email, @NotBlank @Size(min = 3) String password, Set<Role> roles, Set<Board> boardSet, Set<Card> cardSet, String emailId, Long[] cardNoti) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.boardSet = boardSet;
        this.cardSet = cardSet;
        this.emailId = emailId;
        this.cardNoti = cardNoti;
    }

    public User(@NotBlank @Size(min = 2, max = 50) String username, @NotBlank @Size(max = 60) String email, @NotBlank @Size(min = 3) String password, Set<Role> roles, Set<Board> boardSet, Set<Card> cardSet, Set<Comment> commentSet, String emailId, Long[] cardNoti) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.boardSet = boardSet;
        this.cardSet = cardSet;
        this.commentSet = commentSet;
        this.emailId = emailId;
        this.cardNoti = cardNoti;
    }

    public User(@NotBlank @Size(min = 2, max = 50) String username, @NotBlank @Size(max = 60) String email, @NotBlank @Size(min = 3) String password, Set<Role> roles, Set<Board> boardSet, Set<Card> cardSet, Set<Comment> commentSet, String emailId, Long[] cardNoti, String avatarLink) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.boardSet = boardSet;
        this.cardSet = cardSet;
        this.commentSet = commentSet;
        this.emailId = emailId;
        this.cardNoti = cardNoti;
        this.avatarLink = avatarLink;
    }

    public User(@NotBlank @Size(min = 2, max = 50) String username, @NotBlank @Size(max = 60) String email, @NotBlank @Size(min = 3) String password, Set<Role> roles, Set<Board> boardSet, Set<Card> cardSet, Set<Comment> commentSet, String emailId, Set<Notification> cardNotificationSet, Long[] cardNoti, String avatarLink) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.boardSet = boardSet;
        this.cardSet = cardSet;
        this.commentSet = commentSet;
        this.emailId = emailId;
        this.cardNotificationSet = cardNotificationSet;
        this.cardNoti = cardNoti;
        this.avatarLink = avatarLink;
    }

    public User() {
    }

    public Set<Notification> getCardNotificationSet() {
        return cardNotificationSet;
    }

    public void setCardNotificationSet(Set<Notification> cardNotificationSet) {
        this.cardNotificationSet = cardNotificationSet;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public Long[] getCardNoti() {
        return cardNoti;
    }

    public void setCardNoti(Long[] cardNoti) {
        this.cardNoti = cardNoti;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Board> getBoardSet() {
        return boardSet;
    }

    public void setBoardSet(Set<Board> boardSet) {
        this.boardSet = boardSet;
    }

    public Set<Card> getCardSet() {
        return cardSet;
    }

    public void setCardSet(Set<Card> cardSet) {
        this.cardSet = cardSet;
    }
}
