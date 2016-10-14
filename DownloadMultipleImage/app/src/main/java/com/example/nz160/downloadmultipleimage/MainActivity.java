package com.example.nz160.downloadmultipleimage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView intro_images;
    public int currentimageindex=0;

    private int dotsCount=0;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
    private JSONArray banners;
    private JsonParser jsp;
    public static final String BaseUrl = "http://192.168.2.79:8080/Compliance4AllBackend/";

    String url=BaseUrl+"Compliance4AllMain";
    private List<SetterGetter> list=new ArrayList<>();
    private String Imgurl=BaseUrl+"BannerImages";;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsp = new JsonParser();
        new Connection().execute();

        pager_indicator = (LinearLayout)findViewById(R.id.viewPagerCountDots);
        intro_images = (ImageView)findViewById(R.id.pager_introduction);

    }

    private void AnimateandSlideShow() {
        Picasso.with(this)
                .load(Imgurl + "?image=" +list.get(currentimageindex % list.size()).getImages() )
                .placeholder(R.drawable.launchericon) // optional
                .error(R.drawable.launchericon)         // optional
                .into(intro_images);
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[currentimageindex% list.size()].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
        currentimageindex++;

        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.left_in);

        intro_images.startAnimation(rotateimage);

    }
    // to add dotes to the view pager
    private void setUiPageViewController() {

        dotsCount = list.size();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(0, 0, 0, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }
    //Connection to server
    class Connection extends AsyncTask<String,String,JSONObject > {

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("email","null"));
            JSONObject jsonObject = jsp.makeHttpRequest("Post", url, params);
            System.out.println("JSONVALPRINT "+jsonObject);
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            //Go to home.java if internet  conneted
            try {

                    System.out.println("RESULT1234 " + result);
                    JSONObject product = new JSONObject(result.toString());
                    System.out.println("product_arr " + product);
                    banners = product.getJSONArray("banners");
                    System.out.println("banners1234 " + banners);

                    list=getAllLiveProducts(banners);
                    System.out.println("IMAGES " + list.size());
                    setUiPageViewController();
                final Handler mHandler = new Handler();

                AnimationSet set = new AnimationSet(true);
                Animation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(500);
                set.addAnimation(animation);
                final Runnable mUpdateResults = new Runnable() {
                    public void run() {

                            AnimateandSlideShow();

                    }
                };
                int delay =1000; // delay for 1 sec.

                int period = 8000; // repeat every 4 sec.

                Timer timer = new Timer();

                timer.scheduleAtFixedRate(new TimerTask() {

                    public void run() {

                        mHandler.post(mUpdateResults);

                    }
                }, delay, period);
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("EXCEPTIONISNULL " + e.toString());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("EXCEPTIONISNULL " + e.toString());
            }
        }
    }
    //Get all live_web list details
    public List<SetterGetter> getAllLiveProducts(JSONArray product_arr) {
        List<SetterGetter> productList = new ArrayList<SetterGetter>();
        try {
            for (int i = 0; i < product_arr.length(); i++) {
                SetterGetter sg = new SetterGetter();
                JSONObject obj = null;
                try {
                    obj = (JSONObject) product_arr.get(i);
                    sg.setImages(obj.get("banner_images").toString());
                    productList.add(sg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e){

        }
        return productList;

    }
}
