package com.example.shikimoriclient.FrontEnd.adapters;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shikimoriclient.BackEnd.api.Api;
import com.example.shikimoriclient.BackEnd.api.ItemHandler;
import com.example.shikimoriclient.BackEnd.api.anime.AnimeItemHandler;
import com.example.shikimoriclient.BackEnd.api.manga.MangaItemHandler;
import com.example.shikimoriclient.BackEnd.api.ranobe.RanobeItemHandler;
import com.example.shikimoriclient.BackEnd.dao.ItemSimple;
import com.example.shikimoriclient.BackEnd.dao.anime.AnimeSimple;
import com.example.shikimoriclient.BackEnd.dao.manga.MangaSimple;
import com.example.shikimoriclient.BackEnd.dao.ranobe.RanobeSimple;
import com.example.shikimoriclient.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<ItemSimple> itemsList;
    private boolean clicked = false;

    public <T extends ItemSimple> RecyclerAdapter(List<T> animeList) {
        itemsList = new ArrayList<>();
        itemsList.addAll(animeList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_info, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        ItemSimple itemSimple = itemsList.get(i);
        viewHolder.itemName.setText(itemSimple.getName());
        viewHolder.itemNameRus.setText(itemSimple.getRussian());
        Picasso.get().load(Api.baseURL + itemSimple.getImage().getOriginal()).into(viewHolder.itemImage);
        viewHolder.setItemClickListener((view, position, isLongClicK) -> {
            if (!clicked) {
                clicked = true;
                ItemSimple selectedItemSimple = itemsList.get(position);
                ItemHandler itemHandler = null;
                if (selectedItemSimple instanceof AnimeSimple) {
                    itemHandler = new AnimeItemHandler();
                }
                if (selectedItemSimple instanceof MangaSimple) {
                    itemHandler = new MangaItemHandler();
                }
                if (selectedItemSimple instanceof RanobeSimple) {
                    itemHandler = new RanobeItemHandler();
                }
                itemHandler.findById(selectedItemSimple.getId(), view.getContext());
                new Handler().postDelayed(() -> clicked = false, 1000);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView itemImage;
        TextView itemName;
        TextView itemNameRus;
        ItemClickListener itemClickListener;

        void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.thumbnail);
            itemName = itemView.findViewById(R.id.title);
            itemNameRus = itemView.findViewById(R.id.title_rus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v, getAdapterPosition(), false);
        }
    }

    public <T extends ItemSimple> void addItems(List<T> titels) {
        itemsList.addAll(titels);
        notifyDataSetChanged();
    }
}
