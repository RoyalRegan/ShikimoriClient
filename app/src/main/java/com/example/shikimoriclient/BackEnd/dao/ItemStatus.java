package com.example.shikimoriclient.BackEnd.dao;

enum ItemStatus {
    ANONS("anons"),
    ONGOING("ongoing"),
    RELEASED("released");

    private String status;

    ItemStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
