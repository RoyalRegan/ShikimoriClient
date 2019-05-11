package com.example.shikimoriclient.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shikimoriclient.fragments.RecyclerViewFragment;

public class CustomFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private final int TAB_COUNT;

    public CustomFragmentStatePagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        TAB_COUNT = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        return RecyclerViewFragment.newInstance();
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

    enum Titles {
        ANIME,
        MANGA,
        RANOBE;
    }
}
