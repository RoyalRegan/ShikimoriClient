package com.example.shikimoriclient.BackEnd.dao;

import java.io.Serializable;

public class StatusRate implements Serializable {
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
