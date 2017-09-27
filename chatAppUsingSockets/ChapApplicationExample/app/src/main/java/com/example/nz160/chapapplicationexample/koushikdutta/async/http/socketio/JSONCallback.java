package com.example.nz160.chapapplicationexample.koushikdutta.async.http.socketio;

import com.koushikdutta.async.http.socketio.*;

import org.json.JSONObject;

public interface JSONCallback {
    public void onJSON(JSONObject json, com.koushikdutta.async.http.socketio.Acknowledge acknowledge);
}
    
