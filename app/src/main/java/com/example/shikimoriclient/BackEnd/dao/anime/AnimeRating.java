package com.example.shikimoriclient.BackEnd.dao.anime;

public enum AnimeRating {
    NONE("none"),
    G("g"),
    PG("pg"),
    PG_13("pg_13"),
    R("r"),
    R_PLUS("r_plus"),
    RX("rx");

    private String rating;

    AnimeRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return rating;
    }

}
