package com.example.shikimoriclient.BackEnd.api.manga;

import com.example.shikimoriclient.BackEnd.dao.manga.MangaSimple;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Mangas {

    @GET("mangas")
    Call<List<MangaSimple>> getList(@QueryMap Map<String, String> params);
}
