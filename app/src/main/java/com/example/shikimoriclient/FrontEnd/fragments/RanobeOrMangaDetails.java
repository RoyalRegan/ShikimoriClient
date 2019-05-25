package com.example.shikimoriclient.FrontEnd.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shikimoriclient.BackEnd.api.Api;
import com.example.shikimoriclient.BackEnd.dao.manga.Manga;
import com.example.shikimoriclient.R;
import com.squareup.picasso.Picasso;

public class RanobeOrMangaDetails extends Fragment {
    private TextView nameText;
    private TextView typeText;
    private TextView episodeCountText;
    private TextView durationText;
    private TextView statusText;
    private TextView genresText;
    private TextView desctiptionText;
    private ImageView animeImage;

    private static Bundle instanceBundle = new Bundle();

    private Manga manga;

    public static RanobeOrMangaDetails newInstance(Manga manga) {
        instanceBundle.putSerializable("Manga", manga);
        return new RanobeOrMangaDetails();
    }

    public RanobeOrMangaDetails() {
        manga = (Manga) instanceBundle.getSerializable("Manga");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_ranobe_or_manga_details, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComp(view);
    }

    private void initializeComp(View view) {
        nameText = view.findViewById(R.id.mangaName);
        typeText = view.findViewById(R.id.mangaType);
        episodeCountText = view.findViewById(R.id.mangaTomsCount);
        durationText = view.findViewById(R.id.mangaCharapters);
        statusText = view.findViewById(R.id.mangaStatus);
        genresText = view.findViewById(R.id.mangaGenres);
        desctiptionText = view.findViewById(R.id.mangaDescription);
        animeImage = view.findViewById(R.id.mangaImage);
        setCompConfiguration();
        fillComp();
        setListeners();
    }

    private void setLayout() {

    }

    @SuppressLint("SetTextI18n")
    private void fillComp() {
        nameText.setText(manga.getName());
        typeText.setText(manga.getKind());
        episodeCountText.setText(Integer.toString(manga.getVolumes()));
        durationText.setText(Integer.toString(manga.getChapters()));
        statusText.setText(manga.getStatus().toString());
        StringBuilder genres = new StringBuilder();
        for (int i = 0; i < manga.getGenres().length; i++) {
            genres.append(manga.getGenres()[i].getRussian()).append(",");
        }
        genres.deleteCharAt(manga.getGenres().length - 1);
        genresText.setText(genres.toString());
        desctiptionText.setText(manga.getDescription().replaceAll("\\[.+]", ""));
        Picasso.get().load(Api.baseURL + manga.getImage().getOriginal()).into(animeImage);
    }

    private void setCompConfiguration() {

    }

    private void setListeners() {

    }
}
