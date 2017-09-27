package com.example.nz160.chapapplicationexample.koushikdutta.async.http.socketio;

import com.koushikdutta.async.http.socketio.*;

public interface ConnectCallback {
    public void onConnectCompleted(Exception ex, com.koushikdutta.async.http.socketio.SocketIOClient client);
}