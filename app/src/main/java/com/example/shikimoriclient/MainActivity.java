package com.example.shikimoriclient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;

import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.BackEnd.filter.FilterAdapter;
import com.example.shikimoriclient.data.ExpandableListAnimeData;
import com.example.shikimoriclient.data.ExpandableListRanobeData;
import com.example.shikimoriclient.data.ExpandableListMangaData;
import com.example.shikimoriclient.data.SearchableAnimeData;
import com.example.shikimoriclient.data.SearchableMangaData;
import com.example.shikimoriclient.data.SearchableRanobeData;
import com.example.shikimoriclient.fragments.CustomSearchDialog;
import com.example.shikimoriclient.fragments.CustomFilterDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.shikimoriclient.adapters.CustomFragmentStatePagerAdapter;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

//TODO: Fix tab background
//TODO: Fix tab swiping
//TODO: Fix rotate screen

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabFilter;
    private FloatingActionButton fabSearch;
    private FloatingActionButton fabBack;
    private MaterialViewPager materialViewPager;
    private CustomSearchDialog searchDialog;
    private CustomFilterDialog customFilterDialog;

    private SearchFilter animeFilter;

    private SearchFilter mangaFilter;

    private SearchFilter ranobeFilter;

    private static final int TAB_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComp();
        setLayout();
    }


    private void initializeComp() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        fabMenu = findViewById(R.id.fabMenu);
        fabFilter = findViewById(R.id.fabFilter);
        fabSearch = findViewById(R.id.fabSearch);
        fabBack = findViewById(R.id.fabBack);
        materialViewPager = findViewById(R.id.materialViewPager);
        searchDialog = new CustomSearchDialog(this, materialViewPager);
        customFilterDialog = new CustomFilterDialog(this, materialViewPager);
        setCompConfiguration();
        fillComp();
        setListeners();
    }

    private void setLayout() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void fillComp() {
        LinkedHashMap<String, List<String>> expandableListDetail = ExpandableListAnimeData.getData();
        List<String> expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        List<Integer> childrensCount = new LinkedList<>();
        for (int i = 0; i < expandableListTitle.size(); i++) {
            childrensCount.add(expandableListDetail.get(expandableListTitle.get(i)).size());
        }

        FilterAdapter animeFilterAdapter = new FilterAdapter(expandableListTitle.size(), childrensCount, Arrays.asList(3, 4));
        animeFilter = new SearchFilter(animeFilterAdapter, new SearchableAnimeData());

        expandableListDetail = ExpandableListMangaData.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        childrensCount = new LinkedList<>();
        for (int i = 0; i < expandableListTitle.size(); i++) {
            childrensCount.add(expandableListDetail.get(expandableListTitle.get(i)).size());
        }
        FilterAdapter mangaFilterAdapter = new FilterAdapter(expandableListTitle.size(), childrensCount, Arrays.asList(3, 4));
        mangaFilter = new SearchFilter(mangaFilterAdapter, new SearchableMangaData());

        expandableListDetail = ExpandableListRanobeData.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        childrensCount = new LinkedList<>();
        for (int i = 0; i < expandableListTitle.size(); i++) {
            childrensCount.add(expandableListDetail.get(expandableListTitle.get(i)).size());
        }
        FilterAdapter ranobeFilterAdapter = new FilterAdapter(expandableListTitle.size(), childrensCount, Arrays.asList(2, 3));
        ranobeFilter = new SearchFilter(ranobeFilterAdapter, new SearchableRanobeData());
    }

    private void setCompConfiguration() {
        CustomFragmentStatePagerAdapter pagerAdapter = new CustomFragmentStatePagerAdapter(getSupportFragmentManager(), TAB_COUNT);
        materialViewPager.getViewPager().setAdapter(pagerAdapter);
        materialViewPager.getViewPager().setOffscreenPageLimit(TAB_COUNT);
        materialViewPager.getPagerTitleStrip().setViewPager(materialViewPager.getViewPager());

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setHomeButtonEnabled(true);
    }

    private void setListeners() {
        fabMenu.setOnClickListener(view -> {
            if (!fabMenu.isExpanded()) {
                showFABMenu();
            } else {
                fabMenu.collapse();
            }
        });

        fabSearch.setOnClickListener(view -> {
            switch (materialViewPager.getViewPager().getCurrentItem()) {
                case 0: {
                    searchDialog.setFilter(animeFilter);
                    break;
                }
                case 1: {
                    searchDialog.setFilter(mangaFilter);
                    break;
                }
                case 2: {
                    searchDialog.setFilter(ranobeFilter);
                    break;
                }
            }
            fabMenu.collapse();
            searchDialog.show();
        });

        fabFilter.setOnClickListener(view -> {
            switch (materialViewPager.getViewPager().getCurrentItem()) {
                case 0: {
                    customFilterDialog.setFilter(animeFilter);
                    break;
                }
                case 1: {
                    customFilterDialog.setFilter(mangaFilter);
                    break;
                }
                case 2: {
                    customFilterDialog.setFilter(ranobeFilter);
                    break;
                }
            }
            fabMenu.collapse();
            customFilterDialog.show();
        });
        fabBack.setOnClickListener(view -> {
            switch (materialViewPager.getViewPager().getCurrentItem()) {
                case 0: {
                    animeFilter.rest();
                    CustomFragmentStatePagerAdapter adapter = (CustomFragmentStatePagerAdapter) materialViewPager.getViewPager().getAdapter();
                    adapter.setFilter(animeFilter.getParams());
                    adapter.notifyDataSetChanged();
                    break;
                }
                case 1: {
                    mangaFilter.rest();
                    CustomFragmentStatePagerAdapter adapter = (CustomFragmentStatePagerAdapter) materialViewPager.getViewPager().getAdapter();
                    adapter.setFilter(mangaFilter.getParams());
                    adapter.notifyDataSetChanged();
                    break;
                }
                case 2: {
                    ranobeFilter.rest();
                    CustomFragmentStatePagerAdapter adapter = (CustomFragmentStatePagerAdapter) materialViewPager.getViewPager().getAdapter();
                    adapter.setFilter(ranobeFilter.getParams());
                    adapter.notifyDataSetChanged();
                    break;
                }
            }
            fabMenu.collapse();
        });
    }

    private void showFABMenu() {
        if (fabMenu.isExpanded()) {
            fabBack.animate().translationY(-100);
            fabFilter.animate().translationY(-55);
            fabSearch.animate().translationY(-10);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        /*int id = item.getItemId();

        if (id == R.id.nav_list) {

        } else if (id == R.id.nav_checklist) {

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_notifications) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);*/
        return true;
    }
}
