package com.example.shikimoriclient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.View;

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
    private FloatingActionButton fabBack;
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
        fabBack = findViewById(R.id.fabBack);
        materialViewPager = findViewById(R.id.materialViewPager);
        expandableListView = getLayoutInflater().inflate(R.layout.expandable_filter_list, null).findViewById(R.id.expandableListView);
        searchDialog = new CustomSearchDialog(this, materialViewPager);
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
            fabMenu.collapse();
            searchDialog.show();
        });

        fabFilter.setOnClickListener(view -> {
            fabMenu.collapse();
            showDialog(expandableListView);
        });

        fabBack.setOnClickListener(view -> {
            fabMenu.collapse();
        });
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> false);
    }

    private void showFABMenu() {
        if (fabMenu.isExpanded()) {
            fabBack.animate().translationY(-100);
            fabFilter.animate().translationY(-55);
            fabSearch.animate().translationY(-10);
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
