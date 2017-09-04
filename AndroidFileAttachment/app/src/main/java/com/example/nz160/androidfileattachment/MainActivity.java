package com.example.nz160.androidfileattachment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 1;
    private TextView display;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=(TextView)findViewById(R.id.display);
        imageview=(ImageView)findViewById(R.id.imageview);
    }

    public void attachFile(View view){
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if(isStoragePermissionGranted(intent)) {
            // Start the Intent
            startActivityForResult(Intent.createChooser(intent, "Select file"),1 );
        }

    }
    public  boolean isStoragePermissionGranted(Intent galleryIntent) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                System.out.println("PERMISSSION CHECKING " + "Permission is granted");
                return true;
            } else {
                System.out.println("PERMISSSION CHECKING " + "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]
                        {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            System.out.println("PERMISSSION CHECKING " + "Permission is granted");
            return true;
        }
    }
    // on uploaded set the profile image
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {

                // Get the Image from data
                System.out.println("CHECKING " + requestCode);
                final Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                System.out.println("CHECKING " + filePathColumn);

                // Get the cursor
                Cursor cursor =getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                final String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                String str = selectedImage.toString().substring(selectedImage.toString().lastIndexOf('/') + 1);
                System.out.println("CHECKING " + str);

                display.setText(str);

                System.out.println("CHECKING " + str);
                display.setText(str);

                cursor.close();

                imageview.setImageBitmap(BitmapFactory
                        .decodeFile(selectedImage.toString()));

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadImageToServer(imgDecodableString);
                        System.out.println("CHECKING " + "uploadImageToServer");
                    }
                });
                t.start();
            } else {
                Toast.makeText(getApplicationContext(), "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (ArrayStoreException e) {
            Toast.makeText(getApplicationContext(), "Something went wrong " + e.toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }
    // Upload the uploaded image to server and update the database
    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected String uploadImageToServer(String filepath) {
        String responseString = null;
        System.out.println("CHECKING " + "uploadImageToServer filepath "+filepath);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://netzealous.com/submitResume-uma.php");
        System.out.println("CHECKING " + "uploadImageToServer httppost "+httppost.toString());
        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener() {

                        @Override
                        public void transferred(long num) {

                        }
                    });

            File sourceFile = new File(filepath);
            Log.d("sourceFile5678", sourceFile.toString());
            System.out.println("CHECKING " + "uploadImageToServer sourceFile "+sourceFile.toString());

            // Adding file data to http body
            entity.addPart("image", new FileBody(sourceFile));

            // Extra parameters if you want to pass to server
            entity.addPart("name", new StringBody("Tejaswini"));
            entity.addPart("designation", new StringBody("Android Developer"));
            entity.addPart("email", new StringBody("tejaswini.n@netzealous.com"));
            entity.addPart("message", new StringBody("Hello"));
            httppost.setEntity(entity);

            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            System.out.println("CHECKING " + "uploadImageToServer response "+entity.toString());

            HttpEntity r_entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("CHECKING " + "uploadImageToServer statusCode "+statusCode);

            System.out.println("RESPONSEIS "+responseString);
            if (statusCode == 200) {
                // Server response
                responseString = EntityUtils.toString(r_entity);

            } else {
                responseString = "Error occurred! Http Status Code: "
                        + statusCode;
            }

            /*user_profile_pic = imgDecodableString;
            System.out.println("uploadImageToServer " + responseString + " " + user_profile_pic);
*/
        } catch (ClientProtocolException e) {
            responseString = e.toString();
            System.out.println("CHECKING " + "ClientProtocolException "+e.toString());
        } catch (IOException e) {
            responseString = e.toString();
            System.out.println("CHECKING " + "IOException "+e.toString());
        }

        return responseString;
    }


}
