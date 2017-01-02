package com.example.nz160.tejaswini;

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

public class JsonParser {

    static InputStream inputstream=null;
    static String json=null;
    private JSONObject jb;

    public JsonParser(){}

    public JSONObject makeHttpRequest(String method,String url, List<NameValuePair> params){
        System.out.println("PARAMETERS "+method+" url "+url+" params "+params);

        try{
            if(method.equals("Post")) {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                System.out.println("URLIS httpPost " + httpPost);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                System.out.println("URLIS httpResponse " + httpResponse);

                HttpEntity httpEntity = httpResponse.getEntity();
                System.out.println("URLIS httpEntity " + httpEntity);

                inputstream = httpEntity.getContent();
                System.out.println("URLIS inputstream " + inputstream);

            } else if(method.equals("Get")){
                DefaultHttpClient defaultHttpClient=new DefaultHttpClient();
                HttpGet httpGet=new HttpGet(url);
                HttpResponse response=defaultHttpClient.execute(httpGet);
                HttpEntity httpEntity=response.getEntity();
                inputstream=httpEntity.getContent();
                System.out.println("URLIS "+url);
            }

            BufferedReader br=new BufferedReader(new InputStreamReader(inputstream));
            String line=null;
            System.out.println("URLIS br " + br);

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
            System.out.println("EXCEPTION " + "JSONException "+e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EXCEPTION " + "Exception "+e.toString());
        }

        return jb;
    }
}



