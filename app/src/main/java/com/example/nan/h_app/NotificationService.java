package com.example.nan.h_app;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class NotificationService extends Service {
    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showNotification();
        ResultReceiver receiver = intent.getParcelableExtra("recive");
        int time = intent.getIntExtra("Time", 0);


        for (int i = 0; i < time; i++) {
            //  String stg = "21AprilV2";
            Log.v("Timer", "i intent is not null=" + i);
            try {
                Thread.sleep(1000);

            } catch (Exception e) {
            }
            if(false){
                onDestroy();
            }
        }


       // showNotification();
        Bundle bundle = new Bundle();
        bundle.putString("message", "Counting done ...!");
        receiver.send(1234, bundle);


        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "bbb", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }

    public void showNotification() {


        NotificationCompat.Builder nb = new NotificationCompat.Builder(this);
        nb.setContentText("tt");
        nb.setContentTitle("fasdfa");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(1, nb.build());


    }
}
