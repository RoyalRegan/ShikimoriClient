package com.example.shikimoriclient;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.BackEnd.filter.FilterAdapter;
import com.example.shikimoriclient.BackEnd.data.ExpandableListAnimeData;
import com.example.shikimoriclient.BackEnd.data.ExpandableListRanobeData;
import com.example.shikimoriclient.BackEnd.data.ExpandableListMangaData;
import com.example.shikimoriclient.BackEnd.data.SearchableAnimeData;
import com.example.shikimoriclient.BackEnd.data.SearchableMangaData;
import com.example.shikimoriclient.BackEnd.data.SearchableRanobeData;
import com.example.shikimoriclient.FrontEnd.dialogs.SearchDialog;
import com.example.shikimoriclient.FrontEnd.dialogs.FilterDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.example.shikimoriclient.FrontEnd.adapters.CustomFragmentStatePagerAdapter;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import static com.example.shikimoriclient.BackEnd.util.Util.updateRecycleView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabFilter;
    private FloatingActionButton fabSearch;
    private FloatingActionButton fabBack;
    private ViewPager viewPager;
    private SearchDialog searchDialog;
    private FilterDialog filterDialog;
    private TabLayout tabLayout;
    private SearchFilter animeFilter;
    private SearchFilter mangaFilter;
    private SearchFilter ranobeFilter;
    private AVLoadingIndicatorView progressbar;
    private WebView web;

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
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        progressbar = findViewById(R.id.avi);
        searchDialog = new SearchDialog(this, viewPager);
        filterDialog = new FilterDialog(this, viewPager);
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
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(TAB_COUNT);
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
            switch (viewPager.getCurrentItem()) {
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
            switch (viewPager.getCurrentItem()) {
                case 0: {
                    filterDialog.setFilter(animeFilter);
                    break;
                }
                case 1: {
                    filterDialog.setFilter(mangaFilter);
                    break;
                }
                case 2: {
                    filterDialog.setFilter(ranobeFilter);
                    break;
                }
            }
            fabMenu.collapse();
            filterDialog.show();
        });
        fabBack.setOnClickListener(view -> {
            switch (viewPager.getCurrentItem()) {
                case 0: {
                    animeFilter.rest();
                    updateRecycleView(viewPager, animeFilter);
                    break;
                }
                case 1: {
                    mangaFilter.rest();
                    updateRecycleView(viewPager, mangaFilter);
                    break;
                }
                case 2: {
                    ranobeFilter.rest();
                    updateRecycleView(viewPager, ranobeFilter);
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
        int id = item.getItemId();

        if (id == R.id.name)
        {

        }
        else
            if (id == R.id.nav_list)
            {

            }
            else
                if (id == R.id.nav_checklist)
                {

                }
                else
                    if (id == R.id.enter_exit)
                    {
                        Dialog auth_dialog;
                        auth_dialog = new Dialog(MainActivity.this);
                        auth_dialog.setContentView(R.layout.dialog_authorization);
                        web = auth_dialog.findViewById(R.id.webv);
                        web.loadUrl("file:///android_asset/index.html");
                        auth_dialog.show();
                    }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
