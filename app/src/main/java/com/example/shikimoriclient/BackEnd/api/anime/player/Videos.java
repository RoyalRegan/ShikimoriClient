package com.example.shikimoriclient.BackEnd.api.anime.player;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

@Deprecated
public interface Videos {
    @GET()
    Call<String> getVideosByEpisodeNumber(@Url String url);

    @GET()
    Call<String> getPlayerUrlByVideoId(@Url String url);
}