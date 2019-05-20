package com.example.shikimoriclient.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.R;
import com.example.shikimoriclient.adapters.CustomFragmentStatePagerAdapter;
import com.example.shikimoriclient.adapters.ExpandableListAdapter;
import com.example.shikimoriclient.adapters.ExpandableTypedCounter;
import com.example.shikimoriclient.data.ExpandableListAnimeData;
import com.example.shikimoriclient.data.Searchable;
import com.example.shikimoriclient.data.SearchableAnimeData;
import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class CustomFilterDialog {
    private Activity activity;
    private View view;
    private AlertDialog alertDialog;
    private ExpandableListView expandableListView;
    private Button findButton;
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableTypedCounter filterCounter;
    private MaterialViewPager materialViewPager;

    private LinkedHashMap<String, List<String>> expandableListDetail;
    private List<String> expandableListTitle;

    private Searchable searchFilterData;

    private int tabId;

    public CustomFilterDialog(Activity activity, MaterialViewPager materialViewPager) {
        this.activity = activity;
        this.materialViewPager = materialViewPager;
    }

    private void findByParams(HashMap<String, String> params) {
        CustomFragmentStatePagerAdapter adapter = (CustomFragmentStatePagerAdapter) materialViewPager.getViewPager().getAdapter();
        adapter.updateFilter(params);
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


    //TODO: Save filter (?)
    private void setFilterCounter() {
        switch (tabId) {
            case 0: {
                searchFilterData = new SearchableAnimeData();
                expandableListDetail = ExpandableListAnimeData.getData();
                expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
                break;
            }
            case 1: {
                //manga
              /*  expandableListDetail = ExpandableListAnimeData.getParamsValueByColumnId();
                expandableListTitle = new ArrayList<>(expandableListDetail.keySet());*/
                break;
            }
            case 2: {
                //ranobe
               /* expandableListDetail = ExpandableListAnimeData.getParamsValueByColumnId();
                expandableListTitle = new ArrayList<>(expandableListDetail.keySet());*/
                break;
            }
        }
        List<Integer> childrensCount = new LinkedList<>();
        for (int i = 0; i < expandableListTitle.size(); i++) {
            childrensCount.add(expandableListDetail.get(expandableListTitle.get(i)).size());
        }
        filterCounter = new ExpandableTypedCounter(expandableListTitle.size(), childrensCount, Arrays.asList(3, 4));
    }

    private void setCompConfiguration() {
        setFilterCounter();
        expandableListAdapter = new ExpandableListAdapter(activity, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void setListeners() {
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            filterCounter.incCounter(groupPosition, childPosition);
            return false;

        });
        findButton.setOnClickListener(v -> {
            SearchFilter searchFilter = new SearchFilter();
            for (ExpandableTypedCounter counter : filterCounter.getCounter().keySet()) {
                if (filterCounter.getCounter().get(new ExpandableTypedCounter(counter.getGroupId(), counter.getChildId())) == 1) {
                    searchFilter.setCombineParam(searchFilterData.getParamsNameByColumnId(counter.getGroupId()), searchFilterData.getParamsValueByColumnId().get(counter.getGroupId()).get(counter.getChildId()), false);
                }
                if (filterCounter.getCounter().get(new ExpandableTypedCounter(counter.getGroupId(), counter.getChildId())) == 2) {
                    searchFilter.setCombineParam(searchFilterData.getParamsNameByColumnId(counter.getGroupId()), searchFilterData.getParamsValueByColumnId().get(counter.getGroupId()).get(counter.getChildId()), true);
                }
            }
            findByParams(searchFilter.getParams());
        });
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }
}
