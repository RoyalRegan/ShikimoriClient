package com.example.shikimoriclient.BackEnd.api.ranobe;

import com.example.shikimoriclient.BackEnd.dao.manga.Manga;
import com.example.shikimoriclient.BackEnd.dao.ranobe.RanobeSimple;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Ranobes {

    @GET("ranobe")
    Call<List<RanobeSimple>> getList(@QueryMap Map<String, String> params, @Header("Authorization") String authorization);

    @GET("ranobe/{id}")
    Call<com.example.shikimoriclient.BackEnd.dao.ranobe.Ranobe> getRanobe(@Path("id") int id);
}
