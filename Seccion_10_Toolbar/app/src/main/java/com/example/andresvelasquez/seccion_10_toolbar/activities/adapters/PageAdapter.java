package com.example.andresvelasquez.seccion_10_toolbar.activities.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.andresvelasquez.seccion_10_toolbar.activities.fragments.FirstFragment;
import com.example.andresvelasquez.seccion_10_toolbar.activities.fragments.SecondFragment;
import com.example.andresvelasquez.seccion_10_toolbar.activities.fragments.ThirdFragment;

public class PageAdapter extends FragmentStatePagerAdapter{
    int numberOfTabs;
    public PageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return new ThirdFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
