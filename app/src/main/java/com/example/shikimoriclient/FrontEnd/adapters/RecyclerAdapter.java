package com.example.shikimoriclient.FrontEnd.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shikimoriclient.BackEnd.api.Api;
import com.example.shikimoriclient.BackEnd.api.Handler;
import com.example.shikimoriclient.BackEnd.api.anime.AnimeHandler;
import com.example.shikimoriclient.BackEnd.api.manga.MangaHandler;
import com.example.shikimoriclient.BackEnd.api.ranobe.RanobeHandler;
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
        viewHolder.titleName.setText(itemSimple.getName());
        viewHolder.titleNameRus.setText(itemSimple.getRussian());
        Picasso.get().load(Api.baseURL + itemSimple.getImage().getOriginal()).into(viewHolder.titleImage);
        viewHolder.setItemClickListener((view, position, isLongClicK) -> {
            if (!isLongClicK) {
                ItemSimple selectedItemSimple = itemsList.get(position);
                Handler handler = null;
                if (selectedItemSimple instanceof AnimeSimple) {
                    handler = new AnimeHandler();
                }
                if (selectedItemSimple instanceof MangaSimple) {
                    handler = new MangaHandler();
                }
                if (selectedItemSimple instanceof RanobeSimple) {
                    handler = new RanobeHandler();
                }
                handler.findById(selectedItemSimple.getId(),view.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView titleImage;
        TextView titleName;
        TextView titleNameRus;
        ItemClickListener itemClickListener;

        void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleImage = itemView.findViewById(R.id.thumbnail);
            titleName = itemView.findViewById(R.id.title);
            titleNameRus = itemView.findViewById(R.id.title_rus);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.OnClick(v, getAdapterPosition(), true);
            return true;
        }
    }

    public <T extends ItemSimple> void addItems(List<T> titels) {
        itemsList.addAll(titels);
        notifyDataSetChanged();
    }
}
