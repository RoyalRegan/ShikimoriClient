package com.example.shikimoriclient.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shikimoriclient.fragments.RecyclerViewFragment;

import java.util.HashMap;

public class CustomFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private final int TAB_COUNT;
    private HashMap<String, String> filterParams;

    public CustomFragmentStatePagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        TAB_COUNT = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        if (filterParams != null) {
            return RecyclerViewFragment.newInstance(i, filterParams);
        } else {
            return RecyclerViewFragment.newInstance(i);
        }
    }

    //TODO: Update only current tab not all
    public int getItemPosition(Object object) {
        return POSITION_NONE;
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

    public void updateFilter(HashMap<String, String> filterParams) {
        this.filterParams = filterParams;
    }

    enum Titles {
        ANIME,
        MANGA,
        RANOBE;
    }
}
