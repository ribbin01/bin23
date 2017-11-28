package com.example.nan.h_app;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Nan on 25/11/2560.
 */

public class ServiceNoti extends IntentService {

    public ServiceNoti() {
        super("Timer Service");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Timer", "Timer service has started.");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        boolean finish = false;
       /* if (intent == null) {
            int time = 5;
            for (int i = 0; i < time; i++) {
                Log.v("Timerพพ", "i intent is null =" + i);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
            NotificationCompat.Builder nb = new NotificationCompat.Builder(this);
            nb.setContentText("tt");
            nb.setContentTitle("fasdfa");
            nb.setSmallIcon(R.mipmap.ic_launcher);
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(1, nb.build());
            return;
        }*/
        ResultReceiver receiver = intent.getParcelableExtra("recive");
        int Min = intent.getIntExtra("Min", 0);
        int Hr = intent.getIntExtra("Hr", 0);
        Calendar calendar = Calendar.getInstance();


        int HrNow = calendar.get(Calendar.HOUR_OF_DAY);
        int MinNow = calendar.get(Calendar.MINUTE);

        Log.d("Time", "HrNow=" + HrNow + "\nMinNow=" + MinNow);
        Log.d("Time", "HR=" + Hr + "\nMin=" + Min);

        for (int i = 0; i < Min; i++) {
            Log.d("Count", "Sec=" + i);
            try {
                Thread.sleep(1000);

            } catch (Exception e) {


            }

        }

        showNotification();
        Bundle bundle = new Bundle();
        bundle.putString("message", "Counting done ...!");
        receiver.send(1234, bundle);
    }

    private void getCurrentDataTime() {
        //    String stg = "21AprilV2";
        Calendar calendar = Calendar.getInstance();
        int HrNow = calendar.get(Calendar.HOUR_OF_DAY);
        int MinNow = calendar.get(Calendar.MINUTE);
        Log.d("Time", "HR=" + HrNow + "\nMin=" + MinNow);
        //  showTime(Hr, Min);
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();

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


