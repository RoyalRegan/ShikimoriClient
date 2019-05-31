package com.example.shikimoriclient.BackEnd.api;

import com.example.shikimoriclient.BackEnd.api.anime.Animes;
import com.example.shikimoriclient.BackEnd.api.manga.Mangas;
import com.example.shikimoriclient.BackEnd.api.ranobe.Ranobes;
import com.example.shikimoriclient.BackEnd.api.user.Users;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String baseApiURL = "https://shikimori.one";
    public static final String baseURL = "https://shikimori.one";
    private static Retrofit retrofit;

    public static Retrofit initalize() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseApiURL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static Animes getAnimes() {
        return retrofit.create(Animes.class);
    }

    public static Mangas getMangas() {
        return retrofit.create(Mangas.class);
    }

    public static Ranobes getRanobe() {
        return retrofit.create(Ranobes.class);
    }

    public static Users getUser() {
        return retrofit.create(Users.class);
    }
}

