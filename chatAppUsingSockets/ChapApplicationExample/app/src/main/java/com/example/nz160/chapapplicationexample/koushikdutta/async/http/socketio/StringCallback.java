package com.example.nz160.chapapplicationexample.koushikdutta.async.http.socketio;

import com.koushikdutta.async.http.socketio.*;

public interface StringCallback {
    public void onString(String string, com.koushikdutta.async.http.socketio.Acknowledge acknowledge);
}