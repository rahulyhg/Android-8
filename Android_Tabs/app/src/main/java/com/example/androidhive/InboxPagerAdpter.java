package com.example.androidhive;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

//Adapter class to populate the banner viewpager
class InboxPagerAdpter extends PagerAdapter {

    private Context mContext;
    private int[] mResources={R.drawable.ic_launcher,
            R.drawable.icon_inbox,
            R.drawable.icon_inbox,
            R.drawable.icon_inbox,
            R.drawable.icon_inbox,
            R.drawable.icon_inbox
    };

    public InboxPagerAdpter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.inbox_list_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.lw_image);
        imageView.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}
