package com.tunyin.ui.adapter.mine;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tunyin.ui.fragment.mine.MsgListFragment;

public class MsgVPAdapter extends FragmentPagerAdapter {

    private String[] mTitles;

    public MsgVPAdapter(@NonNull FragmentManager fm, String[] titles) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mTitles = titles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return MsgListFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
