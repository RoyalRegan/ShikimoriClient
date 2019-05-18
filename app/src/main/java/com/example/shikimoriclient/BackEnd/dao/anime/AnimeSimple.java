package com.example.shikimoriclient.BackEnd.dao.anime;

import com.example.shikimoriclient.BackEnd.dao.Item;
import com.google.gson.annotations.SerializedName;

public class AnimeSimple extends Item {
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
