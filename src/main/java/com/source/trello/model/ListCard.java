package com.source.trello.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "list")
public class ListCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long listId;
    private String listName;

    public ListCard() {
    }

    public ListCard(String listName) {
        this.listName = listName;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

}