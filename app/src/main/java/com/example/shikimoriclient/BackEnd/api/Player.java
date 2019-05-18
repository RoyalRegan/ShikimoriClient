package com.example.shikimoriclient.BackEnd.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Player {
    private static final String baseURL = "https://play.shikimori.org/";
    private static Retrofit retrofit;

    public static Retrofit initalize() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "auth-value")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                        .header("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8;q=0.7")
                        .header("Connection", "keep-alive")
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.56 (KHTML, like Gecko) Chrome" +
                                "/71.0.3578.98 Safari/537.36");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            OkHttpClient client = httpClient.build();
            retrofit = new Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).client(client).baseUrl(baseURL).build();
        }
        return retrofit;
    }

    public static Videos getVideos() {
        return retrofit.create(Videos.class);
    }
}