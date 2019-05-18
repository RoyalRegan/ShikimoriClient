package com.example.shikimoriclient.BackEnd.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

//TODO:починить
public interface Videos {
    @GET(/*"{anime}/video_online/{number}"*/)
    Call<String> getVideosByEpisodeNumber(@Url String url/*@Path("anime")String anime, @Path("number") int number*/);

    @GET(/*"{anime}/video_online/{number}/{id}"*/)
    Call<String> getPlayerUrlByVideoId(@Url String url/*@Path("anime")String anime, @Path("number") int number, @Path("id") int id*/);
}