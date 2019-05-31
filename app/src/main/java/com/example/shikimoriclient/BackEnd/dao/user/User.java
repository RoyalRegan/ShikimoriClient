package com.example.shikimoriclient.BackEnd.dao.user;

import com.google.gson.annotations.SerializedName;

public class User {
    private int id;
    private String nickname;
    @SerializedName("avatar")
    private String avatarUrl;

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
