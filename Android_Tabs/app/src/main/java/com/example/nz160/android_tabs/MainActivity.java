package com.example.nz160.android_tabs;

import android.app.TabActivity;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,TabHost.OnTabChangeListener {
    TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.speaker,
            R.drawable.speaker,
            R.drawable.speaker
    };

    ViewPager viewPager;
    TabHost tabhost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViewPager();
        initTabHost();
    }

    private void initTabHost() {
        tabhost=(TabHost)findViewById(R.id.tabhost);
        tabhost.setup();
        TabHost.TabSpec tabSpec;
        Resources res=getResources();

        String[] tabNames={"tab1","Tab2","Tab3","Tab4","Tab5"};
        for(int i=0;i<tabNames.length;i++){
            tabSpec=tabhost.newTabSpec(tabNames[i])
                    .setIndicator(tabNames[i],res.getDrawable(R.drawable.speaker))
                    .setContent(new TabContent(getApplicationContext()));
            tabhost.addTab(tabSpec);
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
        horizontalScrollView.smoothScrollBy(scrollPos,0);
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
        adapter.addFragment(new Fragment1(), "ONE");
        adapter.addFragment(new Fragment2(), "TWO");
        adapter.addFragment(new Fragment3(), "THREE");

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);



    }

    private void setupCustomTabIcons() {

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
    }

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


