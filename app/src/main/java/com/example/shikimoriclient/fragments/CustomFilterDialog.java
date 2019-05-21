package com.example.shikimoriclient.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.R;
import com.example.shikimoriclient.adapters.CustomFragmentStatePagerAdapter;
import com.example.shikimoriclient.adapters.ExpandableListAdapter;
import com.example.shikimoriclient.data.ExpandableListAnimeData;
import com.example.shikimoriclient.data.ExpandableListRanobeData;
import com.example.shikimoriclient.data.ExpandableListMangaData;
import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class CustomFilterDialog {
    private Activity activity;
    private View view;
    private AlertDialog alertDialog;
    private MaterialViewPager materialViewPager;
    private ExpandableListView expandableListView;
    private Button findButton;

    private SearchFilter searchFilter;
    private LinkedHashMap<String, List<String>> expandableListDetail;
    private List<String> expandableListTitle;

    public CustomFilterDialog(Activity activity, MaterialViewPager materialViewPager) {
        this.activity = activity;
        this.materialViewPager = materialViewPager;
    }

    private void findByParams(HashMap<String, String> params) {
        CustomFragmentStatePagerAdapter adapter = (CustomFragmentStatePagerAdapter) materialViewPager.getViewPager().getAdapter();
        adapter.setFilter(params);
        adapter.notifyDataSetChanged();
        alertDialog.dismiss();
    }

    public void show() {
        final AlertDialog.Builder adb = new AlertDialog.Builder(activity);
        initializeComp();
        adb.setView(view);
        alertDialog = adb.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    private void initializeComp() {
        view = activity.getLayoutInflater().inflate(R.layout.expandable_filter_list, null);
        expandableListView = view.findViewById(R.id.expandableListView);
        findButton = view.findViewById(R.id.findButton);
        setCompConfiguration();
        setListeners();
    }

    private void setCompConfiguration() {
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(activity, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void setListeners() {
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            //TODO: Change + -
            searchFilter.getFilterAdapter().changeCondition(groupPosition, childPosition);
            return false;
        });
        findButton.setOnClickListener(v -> {
            findByParams(searchFilter.getParams());
        });
    }

    public void setFilter(SearchFilter searchFilter) {
        switch (materialViewPager.getViewPager().getCurrentItem()) {
            case 0: {
                expandableListDetail = ExpandableListAnimeData.getData();
                expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
                break;
            }
            case 1: {
                expandableListDetail = ExpandableListMangaData.getData();
                expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
                break;
            }
            case 2: {
                expandableListDetail = ExpandableListRanobeData.getData();
                expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
                break;
            }
        }
        this.searchFilter = searchFilter;
    }
}
