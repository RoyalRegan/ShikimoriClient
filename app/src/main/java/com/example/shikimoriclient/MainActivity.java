package com.example.shikimoriclient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.ajithvgiri.searchdialog.OnSearchItemSelected;
import com.ajithvgiri.searchdialog.SearchListItem;
import com.example.shikimoriclient.adapters.ExpandableListAdapter;
import com.example.shikimoriclient.data.ExpandableListData;
import com.example.shikimoriclient.fragments.CustomDialogFragment;
import com.example.shikimoriclient.fragments.CustomSearchDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.shikimoriclient.adapters.CustomFragmentStatePagerAdapter;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO: Fix tab name hiding
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
    private MaterialViewPager materialViewPager;
    private ExpandableListView expandableListView;
    private CustomSearchDialog searchDialog;

    private static final int TAB_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComp();
        setLayout();
        fillComp();
    }


    private void initializeComp() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        fabMenu = findViewById(R.id.fabMenu);
        fabFilter = findViewById(R.id.fabFilter);
        fabSearch = findViewById(R.id.fabSearch);
        materialViewPager = findViewById(R.id.materialViewPager);
        expandableListView = getLayoutInflater().inflate(R.layout.expandable_filter_list, null).findViewById(R.id.expandableListView);
        searchDialog = new CustomSearchDialog(this, "SearchDialog");
        setCompConfiguration();
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

        //TODO: Fix random triggers and change triggers on 3 state stuff
        //TODO: Single selection in some categories
        HashMap<String, List<String>> expandableListDetail = ExpandableListData.getData();
        List<String> expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        List<SearchListItem> searchListItems = new ArrayList<>();
        searchDialog.setSearchListItems(searchListItems);
        searchDialog.setOnItemSelected(new OnSearchItemSelected() {
            @Override
            public void onClick(int position, SearchListItem searchListItem) {
                //find by chosen name
            }
        });
    }

    private void setListeners() {
        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //обработка нажатия начальноой кнопки
                if (!fabMenu.isExpanded()) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Обработка нажатия кнопки поиска
                closeFABMenu();
                searchDialog.show();
            }
        });

        fabFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Обработка нажатия кнопки фильтра
                closeFABMenu();
                showDialog(expandableListView);
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }

    private void showFABMenu() {
        if (fabMenu.isExpanded()) {
            fabFilter.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
            fabSearch.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        }
    }

    private void closeFABMenu() {
        if (!fabMenu.isExpanded()) {
            fabFilter.animate().translationY(0);
            fabSearch.animate().translationY(0);
        }
    }

    public void showDialog(View v) {
        ((ViewGroup) v.getParent()).removeView(v);
        CustomDialogFragment dialog = new CustomDialogFragment(v);
        dialog.show(getSupportFragmentManager(), "Filter");
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_list) {

        } else if (id == R.id.nav_checklist) {

        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_notifications) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
