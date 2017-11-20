package com.example.luckychuan.courseselect.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * TabLayout使用的PagerAdapter
 */

public class CourseSelectPagerAdapter extends FragmentPagerAdapter {

    private static final String[] title = {"金花校区", "曲江校区"};
    private ArrayList<Fragment> fragments;


    public CourseSelectPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
