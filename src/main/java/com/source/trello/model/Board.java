package com.source.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;

    private String boardName;
    private String time;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_board",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSet;

    @JsonIgnore
    @OneToMany(targetEntity = ListCard.class,fetch = FetchType.EAGER, mappedBy = "boardSet", cascade = CascadeType.ALL)
    private Set<ListCard> listSet;

    public Board() {
    }

    public Board(String boardName, String time) {
        this.boardName = boardName;
        this.time = time;
    }

    public Board(String boardName, String time, Set<User> userSet) {
        this.boardName = boardName;
        this.time = time;
        this.userSet = userSet;
    }

    public Board(String boardName, String time, Set<User> userSet, Set<ListCard> listSet) {
        this.boardName = boardName;
        this.time = time;
        this.userSet = userSet;
        this.listSet = listSet;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<ListCard> getListSet() {
        return listSet;
    }

    public void setListSet(Set<ListCard> listSet) {
        this.listSet = listSet;
    }
}