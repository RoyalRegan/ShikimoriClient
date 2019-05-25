package com.example.shikimoriclient.BackEnd.dao.anime;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Studio implements Serializable {
    private int id;
    private String name;
    @SerializedName("filtered_name")
    private String filteredName;
    private boolean real;
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFilteredName() {
        return filteredName;
    }

    public boolean isReal() {
        return real;
    }

    public String getImage() {
        return image;
    }
}
