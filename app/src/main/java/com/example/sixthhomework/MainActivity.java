package com.example.sixthhomework;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.sixthhomework.viewpaper.MyViewPaperAdapter;
import com.example.sixthhomework.viewpaper.ViewPagerFirstFragment;
import com.example.sixthhomework.viewpaper.ViewPagerSecondFragment;
import com.example.sixthhomework.viewpaper.ViewPagerThirdFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static ArrayList<String> titles = new ArrayList<>();
    public static ArrayList<Fragment> pages = new ArrayList<>();
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        tabLayout = (TabLayout) findViewById(R.id.title);
        viewPager = (ViewPager) findViewById(R.id.pager);
        addTitle();
        putTitle();
        setPages();
        MyViewPaperAdapter adapter = new MyViewPaperAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void addTitle() {
        titles.add("呼入电话");
        titles.add("呼出电话");
        titles.add("未接电话");
    }

    private void putTitle() {
        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }
    }

    private void setPages() {
        Fragment firstFragment = new ViewPagerFirstFragment();
        pages.add(firstFragment);
        Fragment secondFragment = new ViewPagerSecondFragment();
        pages.add(secondFragment);
        Fragment thirdFragment = new ViewPagerThirdFragment();
        pages.add(thirdFragment);
    }

    @Override
    public void onClick(View v) {

    }
}
