package com.example.shikimoriclient.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shikimoriclient.BackEnd.api.Animes;
import com.example.shikimoriclient.BackEnd.api.Api;
import com.example.shikimoriclient.BackEnd.api.Mangas;
import com.example.shikimoriclient.BackEnd.api.Ranobe;
import com.example.shikimoriclient.BackEnd.dao.anime.AnimeSimple;
import com.example.shikimoriclient.BackEnd.dao.manga.MangaSimple;
import com.example.shikimoriclient.BackEnd.dao.ranobe.RanobeSimple;
import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.R;
import com.example.shikimoriclient.adapters.RecyclerAdapter;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO: Think about cases
public class RecyclerViewFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private LinearLayoutManager layoutManager;
    private SearchFilter animeFilter;
    private SearchFilter mangaFilter;
    private SearchFilter ranobeFilter;

    private static Bundle instanceBundle = new Bundle();

    private Bundle bundle;

    private int pageNumber = 1;
    private boolean isLoading = true;
    private int pastVisibleItems = 0;
    private int visibleItemCount = 0;
    private int totalItemCount = 0;
    private int previousTotal = 0;
    private int viewThreshold = 20;

    //TODO: Remember scroll position

    // .___.
    // |   |
    // |   |
    // |___|
    // |   |
    // |   |
    //  \ /
    //   |
    //   |
    //   |
    //   i
    public static android.support.v4.app.Fragment newInstance(int id, HashMap<String, String> filterParams) {
        switch (id) {
            case 0: {
                instanceBundle.putSerializable("AnimeFilter", filterParams);
                instanceBundle.putString("Type", "Anime");
                break;
            }
            case 1: {
                instanceBundle.putSerializable("MangaFilter", filterParams);
                instanceBundle.putString("Type", "Manga");
                break;
            }
            case 2: {
                instanceBundle.putSerializable("RanobeFilter", filterParams);
                instanceBundle.putString("Type", "Ranobe");
                break;
            }
        }
        return new RecyclerViewFragment();
    }

    public static android.support.v4.app.Fragment newInstance(int id) {
        switch (id) {
            case 0: {
                instanceBundle.putString("Type", "Anime");
                break;
            }
            case 1: {
                instanceBundle.putString("Type", "Manga");
                break;
            }
            case 2: {
                instanceBundle.putString("Type", "Ranobe");
                break;
            }
        }
        return new RecyclerViewFragment();
    }
    //   i
    //   |
    //   |
    //   |
    //  / \
    // |   |
    // |   |
    // |___|
    // |   |
    // |   |
    // |   |
    // .___.


    public RecyclerViewFragment() {
        bundle = new Bundle();
        switch (instanceBundle.getString("Type")) {
            case "Anime": {
                bundle.putString("Type", "Anime");
                break;
            }
            case "Manga": {
                bundle.putString("Type", "Manga");
                break;
            }
            case "Ranobe": {
                bundle.putString("Type", "Ranobe");
                break;
            }
        }
        if (instanceBundle.getSerializable("AnimeFilter") != null) {
            bundle.putSerializable("AnimeFilter", instanceBundle.getSerializable("AnimeFilter"));
        }
        if (instanceBundle.getSerializable("MangaFilter") != null) {
            bundle.putSerializable("MangaFilter", instanceBundle.getSerializable("MangaFilter"));
        }
        if (instanceBundle.getSerializable("RanobeFilter") != null) {
            bundle.putSerializable("RanobeFilter", instanceBundle.getSerializable("RanobeFilter"));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycle_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComp(view);
        initializeAdapter();
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), recyclerView);
    }

    private void initializeComp(View view) {
        recyclerView = (view.findViewById(R.id.recyclerView));
        layoutManager = new GridLayoutManager(getContext(), 2);
        switch (bundle.getString("Type")) {
            case "Anime": {
                if (bundle.getSerializable("AnimeFilter") == null) {
                    animeFilter = new SearchFilter();
                } else {
                    //noinspection MoveFieldAssignmentToInitializer,unchecked
                    animeFilter = new SearchFilter((HashMap<String, String>) bundle.getSerializable("AnimeFilter"));
                }
                break;
            }
            case "Manga": {
                if (bundle.getSerializable("MangaFilter") == null) {
                    mangaFilter = new SearchFilter();
                } else {
                    //noinspection MoveFieldAssignmentToInitializer,unchecked
                    mangaFilter = new SearchFilter((HashMap<String, String>) bundle.getSerializable("MangaFilter"));
                }
                break;
            }
            case "Ranobe": {
                if (bundle.getSerializable("RanobeFilter") == null) {
                    ranobeFilter = new SearchFilter();
                } else {
                    //noinspection MoveFieldAssignmentToInitializer,unchecked
                    ranobeFilter = new SearchFilter((HashMap<String, String>) bundle.getSerializable("RanobeFilter"));
                }
                break;
            }
        }
        Api.initalize();
        setCompConfiguration();
        setListeners();
    }

    private void initializeAdapter() {
        switch (bundle.getString("Type")) {
            case "Anime": {
                Animes api = Api.getAnimes();
                Call<List<AnimeSimple>> call = api.getList(animeFilter.getParams());
                call.enqueue(new Callback<List<AnimeSimple>>() {
                    @Override
                    public void onResponse(Call<List<AnimeSimple>> call, Response<List<AnimeSimple>> response) {
                        recyclerAdapter = new RecyclerAdapter(response.body());
                        recyclerView.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<AnimeSimple>> call, Throwable t) {

                    }
                });
                break;
            }
            case "Manga": {
                Mangas api = Api.getMangas();
                Call<List<MangaSimple>> call = api.getList(mangaFilter.getParams());
                call.enqueue(new Callback<List<MangaSimple>>() {
                    @Override
                    public void onResponse(Call<List<MangaSimple>> call, Response<List<MangaSimple>> response) {
                        recyclerAdapter = new RecyclerAdapter(response.body());
                        recyclerView.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<MangaSimple>> call, Throwable t) {

                    }
                });
                break;
            }
            case "Ranobe": {
                Ranobe api = Api.getRanobe();
                Call<List<RanobeSimple>> call = api.getList(ranobeFilter.getParams());
                call.enqueue(new Callback<List<RanobeSimple>>() {
                    @Override
                    public void onResponse(Call<List<RanobeSimple>> call, Response<List<RanobeSimple>> response) {
                        recyclerAdapter = new RecyclerAdapter(response.body());
                        recyclerView.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<RanobeSimple>> call, Throwable t) {

                    }
                });
                break;
            }
        }
    }

    private void setCompConfiguration() {
        recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setListeners() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + viewThreshold)) {
                        pageNumber++;
                        performPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void performPagination() {
        switch (bundle.getString("Type")) {
            case "Anime": {
                Animes api = Api.getAnimes();
                animeFilter.setParam("page", Integer.toString(pageNumber));
                Call<List<AnimeSimple>> call = api.getList(animeFilter.getParams());
                call.enqueue(new Callback<List<AnimeSimple>>() {
                    @Override
                    public void onResponse(Call<List<AnimeSimple>> call, Response<List<AnimeSimple>> response) {
                        recyclerAdapter.addItems(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<AnimeSimple>> call, Throwable t) {

                    }
                });
                break;
            }
            case "Manga": {
                Mangas api = Api.getMangas();
                mangaFilter.setParam("page", Integer.toString(pageNumber));
                Call<List<MangaSimple>> call = api.getList(mangaFilter.getParams());
                call.enqueue(new Callback<List<MangaSimple>>() {
                    @Override
                    public void onResponse(Call<List<MangaSimple>> call, Response<List<MangaSimple>> response) {
                        recyclerAdapter.addItems(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<MangaSimple>> call, Throwable t) {

                    }
                });
                break;
            }
            case "Ranobe": {
                Ranobe api = Api.getRanobe();
                ranobeFilter.setParam("page", Integer.toString(pageNumber));
                Call<List<RanobeSimple>> call = api.getList(ranobeFilter.getParams());
                call.enqueue(new Callback<List<RanobeSimple>>() {
                    @Override
                    public void onResponse(Call<List<RanobeSimple>> call, Response<List<RanobeSimple>> response) {
                        recyclerAdapter.addItems(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<RanobeSimple>> call, Throwable t) {

                    }
                });
                break;
            }
        }
    }
}


