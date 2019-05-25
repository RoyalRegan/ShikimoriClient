package com.example.shikimoriclient.BackEnd.dao;

import java.io.Serializable;

public class Genre implements Serializable {
    private int id;
    private String name;
    private String russian;
    private String kind;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRussian() {
        return russian;
    }

    public String getKind() {
        return kind;
    }
}
