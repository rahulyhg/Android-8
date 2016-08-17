package com.example.androidhive;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,TabHost.OnTabChangeListener {
    TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher
    };

    ViewPager viewPager;
    TabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViewPager();
        initTabHost();
    }

    private void initTabHost() {
        tabhost=(TabHost)findViewById(android.R.id.tabhost);
        tabhost.setup();
        Resources res=getResources();

        String[] tabNames={"tab1","Tab2"};
        Drawable mySelector = getResources().getDrawable(R.drawable.ic_launcher);
        TabHost.TabSpec tabspec;

        for(int i=0;i<tabNames.length;i++) {
            tabspec = tabhost.newTabSpec(tabNames[i]);
            // Tab Icon
            tabspec.setIndicator(tabNames[i], getResources().getDrawable(R.drawable.icon_inbox));
            // Tab Content
            tabspec.setContent(new TabContent(this));


            tabhost.addTab(tabspec);
        }
        tabhost.setOnTabChangedListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabhost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int selectedPg=tabhost.getCurrentTab();
        viewPager.setCurrentItem(selectedPg);
        HorizontalScrollView horizontalScrollView=(HorizontalScrollView)findViewById(R.id.hscrollview);
        View view=tabhost.getCurrentTabView();
        int scrollPos=view.getLeft()-(horizontalScrollView.getWidth()-view.getWidth())/2;
        horizontalScrollView.smoothScrollBy(scrollPos, 0);
    }

    class TabContent implements TabHost.TabContentFactory {
        Context context;
        public TabContent(Context applicationContext) {
            context=applicationContext;
        }

        @Override
        public View createTabContent(String tag) {
            View view= new View(context);
            view.setMinimumHeight(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    public void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.addFragment(new InboxAdapter(), "ONE");
        adapter.addFragment(new OutboxActivity(), "TWO");

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);

    }

  /*  private void setupCustomTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("ONE");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.speaker, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("TWO");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.speaker, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("THREE");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.speaker, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }*/

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            System.out.println("mFragmentList " + position);
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
