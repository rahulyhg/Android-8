package com.example.nz160.chapapplicationexample.koushikdutta.async.http.socketio;

import com.koushikdutta.async.http.socketio.*;

import org.json.JSONArray;

public interface EventCallback {
    public void onEvent(String event, JSONArray argument, com.koushikdutta.async.http.socketio.Acknowledge acknowledge);
}