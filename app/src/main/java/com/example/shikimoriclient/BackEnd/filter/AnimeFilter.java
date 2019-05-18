package com.example.shikimoriclient.BackEnd.filter;

import java.util.HashMap;

public class AnimeFilter {
    private HashMap<String, String> params;

    public AnimeFilter() {
        params = new HashMap<>();
        setParam("limit", "20");
    }

    public AnimeFilter(HashMap<String, String> params) {
        this.params = params;
        setParam("limit", "20");
    }

    public AnimeFilter setParam(String param, String value) {
        params.put(param, value);
        return this;
    }

    public AnimeFilter setCombineParam(String param, String value, boolean subtract) {
        if (params.get(param) != null) {
            if (subtract) {
                params.put(param, params.get(param) + ",!" + value);
            } else {
                params.put(param, params.get(param) + "," + value);
            }
        } else {
            if (subtract) {
                params.put(param, "!" + value);
            } else {
                params.put(param, value);
            }
        }
        return this;
    }

    public AnimeFilter delParam(String param) {
        params.remove(param);
        return this;
    }

    public HashMap<String, String> getParams() {
        return params;
    }
}
