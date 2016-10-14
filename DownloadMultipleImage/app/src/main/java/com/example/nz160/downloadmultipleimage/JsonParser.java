package com.example.nz160.downloadmultipleimage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by nz160 on 02-03-2016.
 */
public class JsonParser {

    static InputStream inputstream=null;
    static String json=null;
    private JSONObject jb;

    public JsonParser(){

    }

    public JSONObject makeHttpRequest(String method,String url, List<NameValuePair> params){
        try{
            System.out.println("PARAMETERS "+method+" url "+url);
            if(method.equals("Post")) {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputstream = httpEntity.getContent();

            } else if(method.equals("Get")){
                DefaultHttpClient defaultHttpClient=new DefaultHttpClient();
                url=url+"?"+params;
                HttpGet httpGet=new HttpGet(url);
                HttpResponse response=defaultHttpClient.execute(httpGet);
                HttpEntity httpEntity=response.getEntity();
                inputstream=httpEntity.getContent();
                System.out.println("URLIS "+url);
            }

            BufferedReader br=new BufferedReader(new InputStreamReader(inputstream));
            String line=null;

            StringBuilder sb=new StringBuilder();

            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }

            inputstream.close();
            json=sb.toString();
            System.out.println("RESPONSE IS "+json);

            jb=new JSONObject(json);
            System.out.println("JSONObjectValue "+jb.getString("list"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION "+"UnsupportedEncodingException");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION " + "ClientProtocolException");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("EXCEPTION " + "IOException");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jb;
    }
}



