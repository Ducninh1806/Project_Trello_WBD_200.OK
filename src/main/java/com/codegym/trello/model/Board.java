package com.codegym.trello.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long boardId;

    private String boardName;
    private String time;

//
//    @OneToMany(targetEntity = List.class)
//    private List<List> lists;

    public Board() {
    }

    public Board(String boardName, String time) {
        this.boardName = boardName;
        this.time = time;
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
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

//    public List<List> getLists() {
//        return lists;
//    }
//
//    public void setLists(List<List> lists) {
//        this.lists = lists;
//    }
}