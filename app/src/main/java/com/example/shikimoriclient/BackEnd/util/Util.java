package com.example.shikimoriclient.BackEnd.util;

import android.support.v4.view.ViewPager;

import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.FrontEnd.adapters.PagerAdapter;

public class Util {

    public static void updateRecycleView(ViewPager viewPager, SearchFilter searchFilter) {
        PagerAdapter adapter = (PagerAdapter) viewPager.getAdapter();
        adapter.setFilter(searchFilter);
        adapter.updateTab(viewPager.getCurrentItem());
    }
}
