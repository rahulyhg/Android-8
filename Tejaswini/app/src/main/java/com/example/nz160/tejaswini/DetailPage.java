package com.example.nz160.tejaswini;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailPage extends Activity implements ViewPager.OnPageChangeListener {

    private TabLayout tabhost;
    private android.support.v4.view.ViewPager viewPager;
    List<SetterGetter> product=new ArrayList<>();;
    public static String product_name;
    public static String product_id;
    public static String product_img;
    private int count=0;
    private Button register;
    public static String party_id="null";
    private List<SetterGetter> user=new ArrayList<>();
    private boolean is_logged_in;

    @TargetApi(Build.VERSION_CODES.M)
    public DetailPage() {}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_page);
            ImageView ic_date=(ImageView)findViewById(R.id.ic_date);
            ImageView ic_query=(ImageView)findViewById(R.id.ic_query);
            ImageView ic_hourglass_empty = (ImageView)findViewById(R.id.ic_hourglass_empty);
            View textView3=(View)findViewById(R.id.textView3);
                viewPager = (ViewPager)findViewById(R.id.viewpager);

            initTabHost();

            register = (Button)findViewById(R.id.register);

            if (ServerConnection.jsonarray.length() != 0) {
                product = ServerConnection.getProducts(ServerConnection.jsonarray);
                product_name = product.get(0).getName();
                product_id = product.get(0).getproductId();
                product_img = product.get(0).getPersonProfileImg();
                System.out.println("DetailPageproduct  " + product.get(0).getWebinarDuration() + " "
                        + product_id + " " + product_img);
                TextView dateview = (TextView) findViewById(R.id.detail_date);
                TextView detail_time = (TextView) findViewById(R.id.detail_time);

                TextView textview = (TextView) findViewById(R.id.titel_box_text);
                TextView productId = (TextView) findViewById(R.id.productId);
                textview.setText(product.get(0).getName());
                productId.setText(product.get(0).getproductId());
                final DateFormat writeFormat = new SimpleDateFormat("MMM dd, yyyy ");
                DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String dateStr = product.get(0).getWebinarDate();
                if (!dateStr.equals("")) {
                    try {
                        date = readFormat.parse(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("DAAAAAATE " + e.toString());
                    }
                    TextView detail_duration = (TextView) findViewById(R.id.detail_duration_time);
                    detail_duration.setText(product.get(0).getWebinarDuration());
                    dateview.setText(writeFormat.format(date));
                    detail_time.setText(product.get(0).getWebinarTime());
                } else {
                    ic_date.setVisibility(View.GONE);
                    ic_hourglass_empty.setVisibility(View.GONE);
                    ic_query.setVisibility(View.GONE);
                    textView3.setVisibility(View.GONE);
                }

                final Date finalDate = date;


        }
    }



    private void initTabHost() {

        MyDetailAdapter adapter = new MyDetailAdapter(getFragmentManager());
        adapter.addFragment(new Price(), "ONE");
        adapter.addFragment(new Overview(), "TWO");
        adapter.addFragment(new DetailViewSpeaker(), "Three");
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);

        tabhost=(TabLayout)findViewById(R.id.tabs);
        tabhost.setupWithViewPager(viewPager);

        tabhost.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedPg = tabhost.getSelectedTabPosition();
                viewPager.setCurrentItem(selectedPg);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView();
            }
        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        tabhost.setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}

class MyDetailAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public MyDetailAdapter(FragmentManager fm) {
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