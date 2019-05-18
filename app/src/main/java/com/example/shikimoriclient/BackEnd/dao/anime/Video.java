package com.example.shikimoriclient.BackEnd.dao.anime;

import com.google.gson.annotations.SerializedName;

public class Video {
    private int id;
    private String url;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("player_url")
    private String playerUrl;
    private String name;
    private String kind;
    private String hosting;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPlayerUrl() {
        return playerUrl;
    }

    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public String getHosting() {
        return hosting;
    }
}
