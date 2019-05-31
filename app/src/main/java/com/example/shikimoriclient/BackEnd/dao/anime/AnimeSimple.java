package com.example.shikimoriclient.BackEnd.dao.anime;

import com.example.shikimoriclient.BackEnd.dao.ItemSimple;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnimeSimple extends ItemSimple implements Serializable {
    private AnimeKind kind;
    private int episodes;
    @SerializedName("episodes_aired")
    private int apisodesAired;

    public AnimeKind getKind() {
        return kind;
    }

    public int getEpisodes() {
        return episodes;
    }

    public int getApisodesAired() {
        return apisodesAired;
    }
}
