package com.example.nz160.scrollanimation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton mFabButton;
    private LinearLayout linear;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFabButton = (ImageButton) findViewById(R.id.fabButton);
        linear = (LinearLayout) findViewById(R.id.linear);
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }
            @Override
            public void onShow() {
                showViews();
            }
        });
    }

    private void hideViews() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mFabButton.getLayoutParams();
        int linearMargin = lp.bottomMargin;
        mFabButton.animate().translationY(linear.getHeight()+linearMargin+mFabButton.getHeight())
                .setInterpolator(new AccelerateInterpolator(2)).start();
        linear.animate().translationY(linear.getHeight()+linearMargin)
                .setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
        linear.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        //added view types
        private static final int TYPE_HEADER = 2;
        private static final int TYPE_ITEM = 1;

        //modified creating viewholder, so it creates appropriate holder for a given viewType
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            if (viewType == TYPE_ITEM) {
                final View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
                return RecyclerItemViewHolder.newInstance(view);
            } else if (viewType == TYPE_HEADER) {
                final View view = LayoutInflater.from(context).inflate(R.layout.recycler_header, parent, false);
                return new RecyclerHeaderViewHolder(view);
            }
            throw new RuntimeException("There is no type that matches the type " + viewType +
                    " + make sure your using types    correctly");
        }

        //modifed ViewHolder binding so it binds a correct View for the Adapter
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            if (!isPositionHeader(position)) {
                RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
                holder.setItemText("Position "+position);
            }
        }

        //our old getItemCount()
        public int getBasicItemCount() {
            return 20;
        }

        //our new getItemCount() that includes header View
        @Override
        public int getItemCount() {
            return getBasicItemCount() + 1; // header
        }

        //added a method that returns viewType for a given position
        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position)) {
                return TYPE_HEADER;
            }
            return TYPE_ITEM;
        }

        //added a method to check if given position is a header
        private boolean isPositionHeader(int position) {
            return position == 0;
        }
    }

    static  class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView mItemTextView;

        public RecyclerItemViewHolder(final View parent, TextView itemTextView) {
            super(parent);
            mItemTextView = itemTextView;
        }

        public static RecyclerItemViewHolder newInstance(View parent) {
            TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
            return new RecyclerItemViewHolder(parent, itemTextView);
        }

        public void setItemText(CharSequence text) {
            mItemTextView.setText(text);
        }
    }

    class RecyclerHeaderViewHolder extends RecyclerView.ViewHolder {
        public RecyclerHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {
        private static final int HIDE_THRESHOLD = 20;
        private int scrolledDistance = 0;
        private boolean controlsVisible = true;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                onHide();
                controlsVisible = false;
                scrolledDistance = 0;
            } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                onShow();
                controlsVisible = true;
                scrolledDistance = 0;
            }

            if((controlsVisible && dy>0) || (!controlsVisible && dy<0)) {
                scrolledDistance += dy;
            }
        }

        public abstract void onHide();
        public abstract void onShow();

    }
}
