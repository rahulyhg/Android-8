package com.example.nz160.tejaswini;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServerConnection extends AsyncTask<String,String,JSONObject> {

    private static  String type="null";
    Context context;
    private JsonParser jsp;
    public static JSONArray jsonarray;
    private Dialog dialog;
    public static JSONArray product_price_array;
    String url = Utils.BaseUrl + "ProductDetails";
    public static JSONObject product;
    Activity activity;
    public ServerConnection(Context context, String type){
        this.context=context;
        this.type=type;
        System.out.println("TYPEIS "+type);

    }

    @Override
    protected void onPreExecute() {
        jsp=new JsonParser();
        //Set progress bar as dialog with no title bar and no background
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.spinner);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected JSONObject doInBackground(String... args) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("email", 0); // 0 - for private mode
        //Obtain the key value stored in shared preference if the user is logged in else
        // we will get the default value i..e null. set the va lue of is logged in to true
        String key = pref.getString("email", "null");
        System.out.println("KEYYYY "+key);
        params.add(new BasicNameValuePair("live_product_id", "501064LIVE"));
        params.add(new BasicNameValuePair("recorded_product_id", "null"));
        params.add(new BasicNameValuePair("webinar_pack_product_id", "null"));
        params.add(new BasicNameValuePair("email", key));
        System.out.println("List<NameValuePair> " + params.toString());

        JSONObject jsonObject = jsp.makeHttpRequest("Post", url, params);
        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        try {
            product = new JSONObject(result.toString());

            if(result.toString().equals("{}")){
            //Go to home.java if internet  conneted

            } else {
                if(type.equals("live"))
                jsonarray=product.getJSONArray("product_details");
                else
                    jsonarray=product.getJSONArray("rec_product_details");
                if(type.equals("recorded"))
                    product_price_array=product.getJSONArray("rec_product_price");
                else
                    product_price_array=product.getJSONArray("live_product_price");
            }
            System.out.println("PRODUCTDETAOLRESULT " + product_price_array);
            if(jsonarray.length()!=0) {
                Intent i = new Intent(context, DetailPage.class);
                i.putExtra("index", "detailpage");
                context.startActivity(i);
            } else {
                Toast.makeText(context,"Product Not found ",Toast.LENGTH_LONG).show();
            }
            dialog.cancel();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EXCEPTIONISNULL " + e.toString());
            dialog.cancel();
        }

    }

    //Get all recorded_web list details
    public static  List<SetterGetter> getProducts(JSONArray product_arr) {
        List<SetterGetter> productList = new ArrayList<SetterGetter>();

        for (int i = 0; i < product_arr.length(); i++) {
            SetterGetter sg=new SetterGetter();
            JSONObject obj = null;
            try {

                obj = (JSONObject) product_arr.get(i);
                System.out.println("DetailJSONARRPRODUCT overview " + obj.get("overview").toString() + " " +
                        "duration " + obj.get("webinar_duration").toString() + "product_name " +
                        obj.get("product_name").toString() + " product_price" +
                        "FIRST_NAME name " + obj.get("person_name").toString());
                sg.setproductId(obj.get("product_id").toString());
                String desc=obj.get("description").toString();
                if(desc.equals("desc-") || desc.equals("desc-null") ){
                    desc="";
                } else {
                    String split_str[]=obj.get("description").toString().split("-");
                    desc=split_str[1];
                }
                sg.setDescription(desc);
                System.out.println("RECORDEDARRAY " + type);
                String dateStr=obj.get("webinar_date").toString();
                if(dateStr.equals("date-") || dateStr.equals("date-null") || type.equals("recorded")){
                    dateStr="";
                } else {
                    String split_str[]=obj.get("webinar_date").toString().split("-");
                    dateStr=split_str[1];
                }
                String timeStr=obj.get("webinar_time").toString();
                if(timeStr.equals("time-") || timeStr.equals("time-null") || type.equals("recorded")){
                    timeStr="";
                } else {
                    String split_str[]=obj.get("webinar_time").toString().split("-");
                    timeStr=split_str[1];
                }
                String designationStr=obj.get("personDesignation").toString();
                if(designationStr.equals("attrvalue-") || designationStr.equals("attrvalue-null")){
                    designationStr="";
                } else {
                    String split_str[]=obj.get("personDesignation").toString().split("-");
                    designationStr=split_str[1];
                }
                String lastNameStr=obj.get("personLastName").toString();
                if(lastNameStr.equals("lastname-") || lastNameStr.equals("lastname-null")){
                    lastNameStr="";
                } else {
                    String split_str[]=obj.get("personLastName").toString().split("-");
                    lastNameStr=split_str[1];
                }
                sg.setPersonLastName(lastNameStr);
                sg.setPersonDesignation(designationStr);
                sg.setWebinarDate(dateStr);
                sg.setWebinarTime(timeStr);
                String duration_str=obj.get("webinar_duration").toString();
                System.out.println("PRINTING WEB DURATION " + duration_str);
                if(duration_str.equals("duration-") || duration_str.equals("duration-null")){
                    duration_str="";
                } else {
                    String split_str[]=obj.get("webinar_duration").toString().split("-");
                    duration_str=split_str[1];
                }
                sg.setWebinarDuration(duration_str);
                sg.setName(obj.get("product_name").toString());
                String overviewstr=obj.get("overview").toString();
                if(overviewstr.equals("overview-") || overviewstr.equals("overview-null")){
                    overviewstr="";
                } else {
                    String split_str[]=obj.get("overview").toString().split("-");
                    overviewstr=split_str[1];
                }
                sg.setOverView(overviewstr);
                System.out.println("SERVERCONNECTIONDETAILS  " + obj.get("overview").toString());
                sg.setPersonName(obj.get("person_name").toString());
                String brief=obj.get("person_profile").toString();
                if(brief.equals("brief-") || brief.equals("brief-null") ){
                    brief="";
                } else {
                    String split_str[]=obj.get("person_profile").toString().split("-");
                    brief=split_str[1];
                }
                sg.setPersonProfile(brief);
                String image=obj.get("profile_img").toString();
                if(image.equals("img-") || image.equals("img-null") ){
                    image="";
                } else {
                    String split_str[]=obj.get("profile_img").toString().split("-");
                    image=split_str[1];
                }
                sg.setPersonProfileImg(image);
                sg.setPersonId(obj.get("person_id").toString());
                productList.add(sg);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

                return productList;
    }

    //Get all recorded_web list details
    public static List<SetterGetter> getProductPrice(JSONArray product_arr) {
        List<SetterGetter> productList = new ArrayList<SetterGetter>();
        try {
            for (int i = 0; i < product_arr.length(); i++) {
                SetterGetter sg = new SetterGetter();
                JSONObject obj = null;

                obj = (JSONObject) product_arr.get(i);
                sg.setproductId(obj.get("product_id").toString());
                String str=obj.get("price").toString();
                if(str.equals("price-") || str.equals("price-null")){
                    str="";
                } else {
                    String split_str[]=obj.get("price").toString().split("-");
                    str=split_str[1];
                }
                sg.setPrice(str);

                System.out.println("getProductPrice setproductId " + obj.get("product_id").toString() +
                        " setPrice " + obj.get("price").toString());
                productList.add(sg);
            }
            } catch (Exception e) {
                e.printStackTrace();
            }

        return productList;
    }
}
