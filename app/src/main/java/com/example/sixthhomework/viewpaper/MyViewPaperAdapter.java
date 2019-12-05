package com.example.sixthhomework.viewpaper;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sixthhomework.MainActivity;


public class MyViewPaperAdapter extends FragmentStatePagerAdapter {

    public MyViewPaperAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return MainActivity.pages.get(position);
    }

    @Override
    public int getCount() {
        return MainActivity.titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MainActivity.titles.get(position % MainActivity.titles.size());
    }
}
