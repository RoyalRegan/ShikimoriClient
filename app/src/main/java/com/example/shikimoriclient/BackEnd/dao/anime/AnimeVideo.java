package com.example.shikimoriclient.BackEnd.dao.anime;

public class AnimeVideo {
    private String id;
    private String type;
    private String host;
    private String author = "";

    public AnimeVideo() {
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getHost() {
        return host;
    }

    public String getAuthor() {
        return author;
    }
}
