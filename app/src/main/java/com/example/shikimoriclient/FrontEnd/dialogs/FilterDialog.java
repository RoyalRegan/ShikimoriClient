package com.example.shikimoriclient.FrontEnd.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.MainActivity;
import com.example.shikimoriclient.R;
import com.example.shikimoriclient.FrontEnd.adapters.ExpandableListAdapter;
import com.example.shikimoriclient.BackEnd.data.ExpandableListAnimeData;
import com.example.shikimoriclient.BackEnd.data.ExpandableListRanobeData;
import com.example.shikimoriclient.BackEnd.data.ExpandableListMangaData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.shikimoriclient.BackEnd.util.Util.updateRecycleView;

public class FilterDialog {
    private Activity activity;
    private View view;
    private AlertDialog alertDialog;
    private ViewPager viewPager;
    private ExpandableListView expandableListView;
    private Button findButton;

    private SearchFilter searchFilter;
    private LinkedHashMap<String, List<String>> expandableListDetail;
    private List<String> expandableListTitle;

    public FilterDialog(Activity activity, ViewPager viewPager) {
        this.activity = activity;
        this.viewPager = viewPager;
    }

    private void findByParams(SearchFilter searchFilter) {
        updateRecycleView(viewPager, searchFilter);
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
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(activity, expandableListTitle, expandableListDetail, searchFilter.getFilterAdapter());
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void setListeners() {
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            if (expandableListView.getExpandableListAdapter().getGroup(groupPosition).equals("СПИСОК")) {
                if (MainActivity.loggedIn) {
                    searchFilter.getFilterAdapter().changeCondition(groupPosition, childPosition);
                } else {
                    Toast.makeText(activity, "Войдите в аккаунт что бы получить достпук к собственным спискам", Toast.LENGTH_LONG).show();
                }
            } else {
                searchFilter.getFilterAdapter().changeCondition(groupPosition, childPosition);
            }
            ((ExpandableListAdapter) expandableListView.getExpandableListAdapter()).notifyDataSetChanged();
            return false;
        });
        findButton.setOnClickListener(v -> {
            findByParams(searchFilter);
        });
    }

    public void setFilter(SearchFilter searchFilter) {
        switch (viewPager.getCurrentItem()) {
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
