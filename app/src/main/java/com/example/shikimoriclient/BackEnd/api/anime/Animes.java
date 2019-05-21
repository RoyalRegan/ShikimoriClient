package com.example.shikimoriclient.BackEnd.api.anime;

import com.example.shikimoriclient.BackEnd.dao.anime.Anime;
import com.example.shikimoriclient.BackEnd.dao.anime.AnimeSimple;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Animes {

    @GET("animes")
    Call<List<AnimeSimple>> getList(@QueryMap Map<String, String> params);

    @GET("animes/{id}")
    Call<Anime> getAnime(@Path("id") int id);

    @GET("animes/{id}/similar")
    Call<List<AnimeSimple>> getSimilar(@Path("id") int id);

    @GET("animes/{id}/related")
    Call<List<AnimeSimple>> getRelated(@Path("id") int id);

}
