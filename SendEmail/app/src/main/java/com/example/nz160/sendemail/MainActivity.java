package com.example.nz160.sendemail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {

    Session session;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button send = (Button) this.findViewById(R.id.sendEmail);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                Properties properties=new Properties();
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "587");
                session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("nteju37@gmail.com", "#ganesha715#");
                    }
                });
                progressDialog = progressDialog.show(MainActivity.this,"","Sending..",true);
                Retrivefeedback task=new Retrivefeedback();
                task.execute();
            }

            class Retrivefeedback extends AsyncTask <String,String,Void> {
                @Override
                protected Void doInBackground(String... params) {
                    Message message=new MimeMessage(session);
                    try {
                        message.setFrom(new InternetAddress("nteju37@gmail.com"));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nteju37@gmail.com"));
                        message.setSubject("ghtyeheyh");
                        message.setContent("sdsdfsdf", "text/html;charset=utf-8");
                        Transport.send(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                        System.out.println("EXCEPTION IS "+e.toString());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    progressDialog.dismiss();
                }
            }
        });

    }

}
