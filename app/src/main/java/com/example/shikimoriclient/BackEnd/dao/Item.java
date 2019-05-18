package com.example.shikimoriclient.BackEnd.dao;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public abstract class Item {
    private int id;
    private String name;
    private String russian;
    private Image image;
    private String url;
    private ItemStatus status;
    @SerializedName("aired_on")
    private Date airedOn;
    @SerializedName("released_on")
    private Date releasedOn;

    protected Item() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRussian() {
        return russian;
    }

    public Image getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public Date getAiredOn() {
        return airedOn;
    }

    public Date getReleasedOn() {
        return releasedOn;
    }
}
