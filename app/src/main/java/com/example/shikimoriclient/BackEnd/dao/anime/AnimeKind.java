package com.example.shikimoriclient.BackEnd.dao.anime;

import com.google.gson.annotations.SerializedName;

public enum AnimeKind {
    @SerializedName("tv")
    TV("tv"),
    @SerializedName("movie")
    MOVIE("movie"),
    @SerializedName("ova")
    OVA("ova"),
    @SerializedName("ona")
    ONA("ona"),
    @SerializedName("special")
    SPECIAL("special"),
    @SerializedName("music")
    MUSIC("music"),
    @SerializedName("tv_13")
    TV_13("tv_13"),
    @SerializedName("tv_24")
    TV_24("tv_24"),
    @SerializedName("tv_48")
    TV_48("tv_48");

    private String kind;

    AnimeKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return kind;
    }
}
