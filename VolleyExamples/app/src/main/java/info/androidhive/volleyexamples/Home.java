package info.androidhive.volleyexamples;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends Fragment {

    public Home(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_image,container,false);

        Bundle b = getArguments();
        if (b != null) {
            String con = b.getString("response");
            try {
                JSONObject jsonObject=new JSONObject(con);
                JSONArray live_product_arr = jsonObject.getJSONArray("live_list");
                System.out.println("Getting arguments "+live_product_arr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return view;
    }
}
