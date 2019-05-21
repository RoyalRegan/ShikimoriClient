package com.example.shikimoriclient.BackEnd.util;

import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.FrontEnd.adapters.CustomFragmentStatePagerAdapter;
import com.github.florent37.materialviewpager.MaterialViewPager;

public class Util {

    public static void updateRecycleView(MaterialViewPager materialViewPager, SearchFilter searchFilter) {
        CustomFragmentStatePagerAdapter adapter = (CustomFragmentStatePagerAdapter) materialViewPager.getViewPager().getAdapter();
        adapter.setFilter(searchFilter);
        adapter.updateTab(materialViewPager.getViewPager().getCurrentItem());
    }
}
