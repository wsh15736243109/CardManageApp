package com.itboye.cardmanage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;


public class FragmentPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mList;

    public FragmentPageAdapter(FragmentManager fm, List<Fragment> fragments, List<String> list) {
        super(fm);
        mFragments = fragments;
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mList == null) {
            return "";
        } else {
            return mList.get(position);
        }
    }
}
