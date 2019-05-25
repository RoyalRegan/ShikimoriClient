package com.example.shikimoriclient.BackEnd.api.manga;

import android.content.Context;
import android.content.Intent;

import com.example.shikimoriclient.BackEnd.api.Api;
import com.example.shikimoriclient.BackEnd.api.Handler;
import com.example.shikimoriclient.BackEnd.dao.manga.Manga;
import com.example.shikimoriclient.DetailInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangaHandler implements Handler {

    @Override
    public void findById(int id, Context context) {
        Mangas api = Api.getMangas();
        Call<Manga> call = api.getManga(id);
        call.enqueue(new Callback<Manga>() {
            @Override
            public void onResponse(Call<Manga> call, Response<Manga> response) {
                Intent intent = new Intent(context, DetailInfo.class);
                intent.putExtra("Item", response.body());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<Manga> call, Throwable t) {

            }
        });
    }
}