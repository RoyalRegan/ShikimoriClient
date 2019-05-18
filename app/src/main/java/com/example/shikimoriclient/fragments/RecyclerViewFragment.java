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
import com.example.shikimoriclient.BackEnd.dao.anime.AnimeSimple;
import com.example.shikimoriclient.BackEnd.filter.AnimeFilter;
import com.example.shikimoriclient.R;
import com.example.shikimoriclient.adapters.RecyclerAdapter;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
    private AnimeFilter filter;
    private Animes api;

    private static Bundle bundle = new Bundle();

    private int pageNumber = 1;
    private boolean isLoading = true;
    private int pastVisibleItems = 0;
    private int visibleItemCount = 0;
    private int totalItemCount = 0;
    private int previousTotal = 0;
    private int viewThreshold = 20;

    public static android.support.v4.app.Fragment newInstance(HashMap<String, String> filterParams) {
        bundle.putSerializable("Filter", filterParams);
        return new RecyclerViewFragment();
    }

    public static android.support.v4.app.Fragment newInstance() {
        return new RecyclerViewFragment();
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
        if (bundle.getSerializable("Filter") == null) {
            filter = new AnimeFilter();
        } else {
            //noinspection MoveFieldAssignmentToInitializer,unchecked
            filter = new AnimeFilter((HashMap<String, String>) bundle.getSerializable("Filter"));
        }
        Api.initalize();
        api = Api.getAnimes();
        setCompConfiguration();
        setListeners();
    }

    private void initializeAdapter() {
        Call<List<AnimeSimple>> call = api.getList(filter.getParams());
        call.enqueue(new Callback<List<AnimeSimple>>() {
            @Override
            public void onResponse(Call<List<AnimeSimple>> call, Response<List<AnimeSimple>> response) {
                adapter = new RecyclerAdapter(response.body());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<AnimeSimple>> call, Throwable t) {

            }
        });
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
        filter.setParam("page", Integer.toString(pageNumber));
        Call<List<AnimeSimple>> call = api.getList(filter.getParams());
        call.enqueue(new Callback<List<AnimeSimple>>() {
            @Override
            public void onResponse(Call<List<AnimeSimple>> call, Response<List<AnimeSimple>> response) {
                adapter.addItems(response.body());
            }

            @Override
            public void onFailure(Call<List<AnimeSimple>> call, Throwable t) {

            }
        });
    }
}


