package com.example.shikimoriclient.BackEnd.api;

import com.example.shikimoriclient.BackEnd.dao.ranobe.RanobeSimple;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Ranobe {

    @GET("ranobe")
    Call<List<RanobeSimple>> getList(@QueryMap Map<String, String> params);
}
