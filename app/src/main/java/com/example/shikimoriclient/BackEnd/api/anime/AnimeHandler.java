package com.example.shikimoriclient.BackEnd.api.anime;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.shikimoriclient.BackEnd.api.Api;
import com.example.shikimoriclient.BackEnd.api.Handler;
import com.example.shikimoriclient.BackEnd.dao.anime.Anime;
import com.example.shikimoriclient.DetailInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeHandler implements Handler {
    @Override
    public void findById(int id, Context context) {
        Animes api = Api.getAnimes();
        Call<Anime> call = api.getAnime(id);
        call.enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {
                Anime anime = response.body();
                if (anime != null) {
                    Intent intent = new Intent(context, DetailInfo.class);
                    intent.putExtra("Item", anime);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}
