package com.example.shikimoriclient.BackEnd.dao.anime;

import com.google.gson.annotations.SerializedName;

public class Studio {
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
