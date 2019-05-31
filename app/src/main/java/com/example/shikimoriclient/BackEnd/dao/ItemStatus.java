package com.example.shikimoriclient.BackEnd.dao;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum ItemStatus implements Serializable {
    @SerializedName("anons")
    ANONS("Анонсировано"),
    @SerializedName("ongoing")
    ONGOING("Сейчас выходит"),
    @SerializedName("released")
    RELEASED("Вышло");

    private String status;

    ItemStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
