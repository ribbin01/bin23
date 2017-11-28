package com.example.nan.h_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Nan on 25/11/2560.
 */
@SuppressLint("ParcelCreator")
public class MessageReciver extends ResultReceiver {
//private MainActivity.Message message;
    // public MessageReciver(MainActivity.Message message) {

    private SetNotification.Message message;

    public MessageReciver(SetNotification.Message message) {
        super(new Handler());
        this.message = message;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        message.displayMessage(resultCode, resultData);
        //super.onReceiveResult(resultCode, resultData);
    }



}
