package com.example.joy.cafe.adapter;

/**
 * Created by root on 12/17/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.joy.cafe.fragment.OverViewFragment;
import com.example.joy.cafe.fragment.ReviewFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                OverViewFragment tab1 = new OverViewFragment();
                return tab1;
            case 1:
                ReviewFragment tab2 = new ReviewFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}