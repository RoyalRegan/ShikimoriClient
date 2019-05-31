package com.example.shikimoriclient.BackEnd.dao.anime;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum AnimeRating implements Serializable {
    @SerializedName("none")
    NONE("None"),
    @SerializedName("g")
    G("G"),
    @SerializedName("pg")
    PG("PG"),
    @SerializedName("pg_13")
    PG_13("PG-13"),
    @SerializedName("r")
    R("R"),
    @SerializedName("r_plus")
    R_PLUS("R+"),
    @SerializedName("rx")
    RX("RX");

    private String rating;

    AnimeRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return rating;
    }
}
