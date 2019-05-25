package com.example.shikimoriclient.BackEnd.dao.anime;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum AnimeKind implements Serializable {
    @SerializedName("tv")
    TV("TV Сериал"),
    @SerializedName("movie")
    MOVIE("Фильм"),
    @SerializedName("ova")
    OVA("OVA"),
    @SerializedName("ona")
    ONA("ONA"),
    @SerializedName("special")
    SPECIAL("Спешл"),
    @SerializedName("music")
    MUSIC("Клип"),
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

    @NonNull
    @Override
    public String toString() {
        return kind;
    }
}
