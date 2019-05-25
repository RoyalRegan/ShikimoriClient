package com.example.shikimoriclient.BackEnd.dao.anime;

import java.io.Serializable;

public class Screenshot implements Serializable {

    private String original;
    private String preview;

    public String getOriginal() {
        return original;
    }

    public String getPreview() {
        return preview;
    }
}
