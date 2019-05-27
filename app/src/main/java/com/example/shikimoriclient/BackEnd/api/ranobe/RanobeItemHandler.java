package com.example.shikimoriclient.BackEnd.api.ranobe;

import android.content.Context;
import android.content.Intent;

import com.example.shikimoriclient.BackEnd.api.Api;
import com.example.shikimoriclient.BackEnd.api.ItemHandler;
import com.example.shikimoriclient.BackEnd.dao.ranobe.Ranobe;
import com.example.shikimoriclient.DetailInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RanobeItemHandler implements ItemHandler {

    @Override
    public void findById(int id, Context context) {
        Ranobes api = Api.getRanobe();
        Call<Ranobe> call = api.getRanobe(id);
        call.enqueue(new Callback<Ranobe>() {
            @Override
            public void onResponse(Call<Ranobe> call, Response<Ranobe> response) {
                Intent intent = new Intent(context, DetailInfo.class);
                intent.putExtra("Item", response.body());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<Ranobe> call, Throwable t) {

            }
        });
    }
}
