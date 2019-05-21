package com.example.shikimoriclient.FrontEnd.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.R;
import com.example.shikimoriclient.FrontEnd.adapters.ExpandableListAdapter;
import com.example.shikimoriclient.BackEnd.data.ExpandableListAnimeData;
import com.example.shikimoriclient.BackEnd.data.ExpandableListRanobeData;
import com.example.shikimoriclient.BackEnd.data.ExpandableListMangaData;
import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.shikimoriclient.BackEnd.util.Util.updateRecycleView;

public class FilterDialog {
    private Activity activity;
    private View view;
    private AlertDialog alertDialog;
    private MaterialViewPager materialViewPager;
    private ExpandableListView expandableListView;
    private Button findButton;

    private SearchFilter searchFilter;
    private LinkedHashMap<String, List<String>> expandableListDetail;
    private List<String> expandableListTitle;

    public FilterDialog(Activity activity, MaterialViewPager materialViewPager) {
        this.activity = activity;
        this.materialViewPager = materialViewPager;
    }

    private void findByParams(SearchFilter searchFilter) {
        updateRecycleView(materialViewPager, searchFilter);
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
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(activity, expandableListTitle, expandableListDetail,searchFilter.getFilterAdapter());
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void setListeners() {
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            searchFilter.getFilterAdapter().changeCondition(groupPosition, childPosition);
            ((ExpandableListAdapter) expandableListView.getExpandableListAdapter()).notifyDataSetChanged();
            return false;
        });
        findButton.setOnClickListener(v -> {
            findByParams(searchFilter);
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
