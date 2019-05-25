package com.example.shikimoriclient.FrontEnd.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shikimoriclient.BackEnd.filter.SearchFilter;
import com.example.shikimoriclient.FrontEnd.fragments.RecyclerViewFragment;
import com.example.shikimoriclient.FrontEnd.fragments.Updatable;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private final int TAB_COUNT;
    private SearchFilter searchFilter;
    private int selectedTab;
    private int updateStatementTab = 0;

    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        TAB_COUNT = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        return RecyclerViewFragment.newInstance(i);
    }

    public int getItemPosition(Object object) {
        Updatable view = (Updatable) object;
        if (view != null) {
            if (updateStatementTab == selectedTab) {
                view.update(searchFilter);
            }
            updateStatementTab++;
            if (updateStatementTab % 3 == 0) {
                updateStatementTab = 0;
            }
        }
        return super.getItemPosition(object);
    }

    public void updateTab(int selectedTab) {
        this.selectedTab = selectedTab;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Titles.values()[0].name();
            case 1:
                return Titles.values()[1].name();
            case 2:
                return Titles.values()[2].name();
            default:
                return "Page " + position;
        }
    }

    public void setFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    enum Titles {
        ANIME,
        MANGA,
        RANOBE;
    }
}
