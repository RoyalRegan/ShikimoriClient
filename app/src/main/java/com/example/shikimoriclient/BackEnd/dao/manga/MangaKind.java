package com.example.shikimoriclient.BackEnd.dao.manga;

import android.support.annotation.NonNull;

import java.io.Serializable;

public enum MangaKind implements Serializable {
    MANGA("manga"),
    MANHWA("manhwa"),
    MANHUA("manhua"),
    ONE_SHOT("one_shot"),
    DOUJIN("doujin");

    private String kind;

    MangaKind(String kind) {
        this.kind = kind;
    }

    @NonNull
    @Override
    public String toString() {
        return kind;
    }
}
