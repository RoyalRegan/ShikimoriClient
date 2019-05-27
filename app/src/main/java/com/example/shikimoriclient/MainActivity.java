package com.example.shikimoriclient;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.shikimoriclient.BackEnd.data.ExpandableListAnimeData;
import com.example.shikimoriclient.BackEnd.data.ExpandableListMangaData;
import com.example.shikimoriclient.BackEnd.data.ExpandableListRanobeData;
import com.example.shikimoriclient.BackEnd.data.SearchableAnimeData;
import com.example.shikimoriclient.BackEnd.data.SearchableMangaData;
import com.example.shikimoriclient.BackEnd.data.SearchableRanobeData;
import com.example.shikimoriclient.BackEnd.filter.FilterAdapter;
import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.FrontEnd.adapters.PagerAdapter;
import com.example.shikimoriclient.FrontEnd.dialogs.FilterDialog;
import com.example.shikimoriclient.FrontEnd.dialogs.SearchDialog;
import com.example.shikimoriclient.FrontEnd.dialogs.SigInDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import static com.example.shikimoriclient.BackEnd.util.Util.updateAllRecycleView;
import static com.example.shikimoriclient.BackEnd.util.Util.updateRecycleView;

//TODO: Checking tokens
//TODO: Icon into NavigationView
//TODO: infoScreen
//TODO: Delete text into searchScreen
//TODO: Refactor Alot

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private FloatingActionsMenu fabMenu;
    private FloatingActionButton fabFilter;
    private FloatingActionButton fabSearch;
    private FloatingActionButton fabBack;
    private ViewPager viewPager;
    private SearchDialog searchDialog;
    private FilterDialog filterDialog;
    private SigInDialog sigInDialog;
    private SearchFilter animeFilter;
    private SearchFilter mangaFilter;
    private SearchFilter ranobeFilter;

    private HashMap<String, String> userData;

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
        navigationView = findViewById(R.id.nav_view);
        actionBar = getSupportActionBar();
        fabMenu = findViewById(R.id.fabMenu);
        fabFilter = findViewById(R.id.fabFilter);
        fabSearch = findViewById(R.id.fabSearch);
        fabBack = findViewById(R.id.fabBack);
        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        searchDialog = new SearchDialog(this, viewPager);
        filterDialog = new FilterDialog(this, viewPager);
        sigInDialog = new SigInDialog(this);
        userData = new HashMap<>();
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
        checkUserData();
    }

    @SuppressLint("SimpleDateFormat")
    public void checkUserData() {
        File file = new File(getFilesDir(), "userInfo");
        if (file.exists()) {
            try {
                BufferedReader bis = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bis.readLine()) != null) {
                    userData.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") + 1));
                }
                bis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            UserInfoHandler.Token = "Bearer " + userData.get("access_token");
            //user_icon_url
        } else {
            navigationView.getMenu().getItem(0).setVisible(false);
        }
        updateTabs();
    }

    private void updateTabs() {
        File file = new File(getFilesDir(), "userInfo");
        if (file.exists()) {
            navigationView.getMenu().getItem(0).setVisible(true);
            navigationView.getMenu().getItem(0).setTitle(userData.get("user_nickname"));
            // navigationView.getMenu().getItem(0).setIcon();
            navigationView.getMenu().getItem(3).setTitle("Выйти");
        } else {
            navigationView.getMenu().getItem(0).setVisible(false);
            navigationView.getMenu().getItem(3).setTitle("Войти");
        }
        navigationView.getMenu().getItem(1).setChecked(true);
    }

    private void setCompConfiguration() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), TAB_COUNT);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(TAB_COUNT);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setHomeButtonEnabled(true);
        navigationView.getMenu().getItem(1).setChecked(true);
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
                    if (animeFilter.isFilterHas("mylist")) {
                        String mylist = animeFilter.getParams().get("mylist");
                        animeFilter.rest();
                        animeFilter.setParam("mylist", mylist);
                    } else {
                        animeFilter.rest();
                    }
                    updateRecycleView(viewPager, animeFilter);
                    break;
                }
                case 1: {
                    if (mangaFilter.isFilterHas("mylist")) {
                        String mylist = mangaFilter.getParams().get("mylist");
                        mangaFilter.rest();
                        mangaFilter.setParam("mylist", mylist);
                    } else {
                        mangaFilter.rest();
                    }
                    updateRecycleView(viewPager, mangaFilter);
                    break;
                }
                case 2: {
                    if (ranobeFilter.isFilterHas("mylist")) {
                        String mylist = ranobeFilter.getParams().get("mylist");
                        ranobeFilter.rest();
                        ranobeFilter.setParam("mylist", mylist);
                    } else {
                        ranobeFilter.rest();
                    }
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
        if (id == R.id.nav_list) {
            animeFilter.rest();
            mangaFilter.rest();
            ranobeFilter.rest();
            updateAllRecycleView(viewPager, animeFilter);
        } else if (id == R.id.nav_checklist) {
            animeFilter.rest();
            animeFilter.setParam("mylist", "completed");
            mangaFilter.rest();
            mangaFilter.setParam("mylist", "completed");
            ranobeFilter.rest();
            ranobeFilter.setParam("mylist", "completed");
            updateAllRecycleView(viewPager, animeFilter);
        } else if (id == R.id.enter_exit) {
            if (item.getTitle() == "Войти") {
                sigInDialog.show();
            }
            if (item.getTitle() == "Выйти") {
                File file = new File(getFilesDir(), "userInfo");
                file.delete();
                updateTabs();
                UserInfoHandler.Token = "";
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
