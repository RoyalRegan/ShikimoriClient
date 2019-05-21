package com.example.shikimoriclient.BackEnd.api;

import com.example.shikimoriclient.BackEnd.api.anime.Animes;
import com.example.shikimoriclient.BackEnd.api.manga.Mangas;
import com.example.shikimoriclient.BackEnd.api.ranobe.Ranobe;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String baseApiURL = "https://shikimori.one/api/";
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

    public static Ranobe getRanobe() {
        return retrofit.create(Ranobe.class);
    }
}

