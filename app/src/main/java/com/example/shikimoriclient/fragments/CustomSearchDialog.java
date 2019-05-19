package com.example.shikimoriclient.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.shikimoriclient.BackEnd.api.Animes;
import com.example.shikimoriclient.BackEnd.api.Api;
import com.example.shikimoriclient.BackEnd.api.Mangas;
import com.example.shikimoriclient.BackEnd.api.Ranobe;
import com.example.shikimoriclient.BackEnd.dao.anime.AnimeSimple;
import com.example.shikimoriclient.BackEnd.dao.manga.MangaSimple;
import com.example.shikimoriclient.BackEnd.dao.ranobe.RanobeSimple;
import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.R;
import com.example.shikimoriclient.adapters.CustomFragmentStatePagerAdapter;
import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO: Refactor like CustomFilterDialog

public class CustomSearchDialog {
    private ArrayAdapter<String> searchListAdapter;
    private Activity activity;
    private AlertDialog alertDialog;
    private MaterialViewPager materialViewPager;
    private ListView listView;
    private int tabId;

    public CustomSearchDialog(Activity activity, MaterialViewPager materialViewPager) {
        this.activity = activity;
        this.materialViewPager = materialViewPager;
    }

    private void searchByString(String str) {
        CustomFragmentStatePagerAdapter adapter = (CustomFragmentStatePagerAdapter) materialViewPager.getViewPager().getAdapter();
        HashMap<String, String> params = new HashMap<>();
        params.put("search", str);
        adapter.updateFilter(params);
        adapter.notifyDataSetChanged();
    }

    public void show() {

        final AlertDialog.Builder adb = new AlertDialog.Builder(activity);
        @SuppressLint("InflateParams") View view = activity.getLayoutInflater().inflate(R.layout.search_dialog_layout, null);
        listView = view.findViewById(R.id.list);

        final EditText searchBox = view.findViewById(R.id.searchBox);
        adb.setView(view);
        alertDialog = adb.create();
        alertDialog.setCancelable(true);

        listView.setOnItemClickListener((adapterView, view1, id, l) -> {
            searchByString(searchListAdapter.getItem(id));
            alertDialog.dismiss();
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            private Timer timer = new Timer();
            private final long DELAY = 100;

            @Override
            public void afterTextChanged(Editable editable) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                Api.initalize();
                                List<String> findLists = new LinkedList<>();
                                SearchFilter filter = new SearchFilter();
                                String searchText = searchBox.getText().toString();
                                if (!searchText.equals("")) {
                                    switch (tabId) {
                                        case 0: {
                                            Animes api = Api.getAnimes();
                                            filter.setParam("search", searchText);
                                            Call<List<AnimeSimple>> call = api.getList(filter.getParams());
                                            call.enqueue(new Callback<List<AnimeSimple>>() {
                                                @RequiresApi(api = Build.VERSION_CODES.N)
                                                @Override
                                                public void onResponse(Call<List<AnimeSimple>> call, Response<List<AnimeSimple>> response) {
                                                    if (response.body() != null) {
                                                        if (searchText.matches("[A-z0-9]*")) {
                                                            for (AnimeSimple anime : response.body()) {
                                                                findLists.add(anime.getName());
                                                            }
                                                        } else {
                                                            if (searchText.matches("[А-яЁё0-9]*")) {
                                                                for (AnimeSimple anime : response.body()) {
                                                                    findLists.add(anime.getRussian());
                                                                }
                                                            }
                                                        }
                                                        searchListAdapter = new ArrayAdapter<>(activity, R.layout.items_view_layout, R.id.text1, findLists);
                                                        listView.setAdapter(searchListAdapter);
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<List<AnimeSimple>> call, Throwable t) {

                                                }
                                            });
                                            break;
                                        }
                                        case 1: {
                                            Mangas api = Api.getMangas();
                                            filter.setParam("search", searchText);
                                            Call<List<MangaSimple>> call = api.getList(filter.getParams());
                                            call.enqueue(new Callback<List<MangaSimple>>() {
                                                @RequiresApi(api = Build.VERSION_CODES.N)
                                                @Override
                                                public void onResponse(Call<List<MangaSimple>> call, Response<List<MangaSimple>> response) {
                                                    if (response.body() != null) {
                                                        if (searchText.matches("[A-z0-9]*")) {
                                                            for (MangaSimple manga : response.body()) {
                                                                findLists.add(manga.getName());
                                                            }
                                                        } else {
                                                            if (searchText.matches("[А-яЁё0-9]*")) {
                                                                for (MangaSimple manga : response.body()) {
                                                                    findLists.add(manga.getRussian());
                                                                }
                                                            }
                                                        }
                                                        searchListAdapter = new ArrayAdapter<>(activity, R.layout.items_view_layout, R.id.text1, findLists);
                                                        listView.setAdapter(searchListAdapter);
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<List<MangaSimple>> call, Throwable t) {

                                                }
                                            });
                                            break;
                                        }
                                        case 2: {
                                            Ranobe api = Api.getRanobe();
                                            filter.setParam("search", searchText);
                                            Call<List<RanobeSimple>> call = api.getList(filter.getParams());
                                            call.enqueue(new Callback<List<RanobeSimple>>() {
                                                @RequiresApi(api = Build.VERSION_CODES.N)
                                                @Override
                                                public void onResponse(Call<List<RanobeSimple>> call, Response<List<RanobeSimple>> response) {
                                                    if (response.body() != null) {
                                                        if (searchText.matches("[A-z0-9]*")) {
                                                            for (MangaSimple manga : response.body()) {
                                                                findLists.add(manga.getName());
                                                            }
                                                        } else {
                                                            if (searchText.matches("[А-яЁё0-9]*")) {
                                                                for (MangaSimple manga : response.body()) {
                                                                    findLists.add(manga.getRussian());
                                                                }
                                                            }
                                                        }
                                                        searchListAdapter = new ArrayAdapter<>(activity, R.layout.items_view_layout, R.id.text1, findLists);
                                                        listView.setAdapter(searchListAdapter);
                                                    }
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
                        },
                        DELAY
                );
                if (searchBox.getText().toString().equals("")) {
                    searchListAdapter = new ArrayAdapter<>(activity, R.layout.items_view_layout, R.id.text1, Collections.emptyList());
                    listView.setAdapter(searchListAdapter);
                }
            }
        });

        searchBox.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (!searchBox.getText().toString().equals("")) {
                    searchByString(searchBox.getText().toString());
                    alertDialog.dismiss();
                    handled = true;
                }
            }
            return handled;
        });
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        alertDialog.show();
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }
}