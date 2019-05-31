package com.example.shikimoriclient.BackEnd.dao;

import java.io.Serializable;

public class Image implements Serializable {
    private String original;
    private String preview;
    private String x96;
    private String x48;

    public String getOriginal() {
        return original;
    }

    public String getPreview() {
        return preview;
    }

    public String getX96() {
        return x96;
    }

    public String getX48() {
        return x48;
    }
}
