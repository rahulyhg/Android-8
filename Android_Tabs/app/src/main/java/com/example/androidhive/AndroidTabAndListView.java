package com.example.androidhive;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabAndListView extends TabActivity implements TabHost.OnTabChangeListener,
        ViewPager.OnPageChangeListener {
	// TabSpec Names
	private static final String INBOX_SPEC = "Inbox";
	private static final String OUTBOX_SPEC = "Outbox";
    TabHost tabHost;
    private android.support.v4.view.ViewPager viewPager;
    private InboxPagerAdpter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();

        // Inbox Tab
        TabSpec inboxSpec = tabHost.newTabSpec(INBOX_SPEC);
        // Tab Icon
        inboxSpec.setIndicator(INBOX_SPEC, getResources().getDrawable(R.drawable.icon_inbox));
        Intent inboxIntent = new Intent(this, InboxActivity.class);
        // Tab Content
        inboxSpec.setContent(inboxIntent);

        // Outbox Tab
        TabSpec outboxSpec = tabHost.newTabSpec(OUTBOX_SPEC);
        outboxSpec.setIndicator(OUTBOX_SPEC, getResources().getDrawable(R.drawable.icon_outbox));
        Intent outboxIntent = new Intent(this, OutboxActivity.class);
        outboxSpec.setContent(outboxIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(inboxSpec); // Adding Inbox tab
        tabHost.addTab(outboxSpec); // Adding Outbox tab

        tabHost.setOnTabChangedListener(this);
        setReference();

    }
    //Function to set banner images in view pager
    public void setReference() {

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        mAdapter = new InboxPagerAdpter(this);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);

    }

    @Override
    public void onTabChanged(String tabId) {
        int selectedPg=tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedPg);
        HorizontalScrollView horizontalScrollView=(HorizontalScrollView)findViewById(R.id.hscrollview);
        View view=tabHost.getCurrentTabView();
        int scrollPos=view.getLeft()-(horizontalScrollView.getWidth()-view.getWidth())/2;
        horizontalScrollView.smoothScrollBy(scrollPos,0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}