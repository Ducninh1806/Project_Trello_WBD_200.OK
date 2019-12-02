package com.source.trello.model;

import javax.persistence.*;

@Entity
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "cardId")
    private Card card;

    private String type;

    private String fileName;

    public FileUpload() {
    }

    public FileUpload(String url, Card card) {
        this.url = url;
        this.card = card;
    }

    public FileUpload(String url, Card card,String type) {
        this.url = url;
        this.card = card;
        this.type = type;
    }

    public FileUpload(String url, Card card, String type, String fileName) {
        this.url = url;
        this.card = card;
        this.type = type;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
