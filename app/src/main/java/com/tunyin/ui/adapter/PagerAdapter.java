package com.tunyin.ui.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tunyin.BaseFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WYiang on 2017/10/21.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;
    private List<BaseFragment> fragments;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public PagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public PagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        mTitles = Arrays.asList(titles);
    }

    public PagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.mTitles = titles;
    }

    public void setData(List<BaseFragment> fragments, List<String> titles) {
        this.fragments = fragments;
        this.mTitles = titles;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null)
            return mTitles.get(position);
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
