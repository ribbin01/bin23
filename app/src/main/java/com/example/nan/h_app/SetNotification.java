package com.example.nan.h_app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nan on 25/11/2560.
 */

public class SetNotification extends AppCompatActivity {
    Switch aSwitch;
    int hr, min;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    Button btn_noti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notification);
        aSwitch = findViewById(R.id.swc_noti);
        btn_noti = findViewById(R.id.btn_noti);
    }

    public void onSwitch(View view) {

        if (aSwitch.isChecked()) {
            getTimeFromDevice();


            //    MessageReciver reciver = new MessageReciver(new Message());

            //   Intent i = new Intent(this, NotificationService.class);
            //  i.putExtra("Time", 25);
            //  i.putExtra("recive", reciver);
            //   startService(i);


        } else {
            Intent intent = new Intent(this, ServiceNoti.class);

            stopService(intent);


            Intent i = new Intent(this, NotificationService.class);
            stopService(i);
        }
    }

    public class Message {
        public void displayMessage(int resultCode, Bundle resultData) {
            String message = resultData.getString("message");
            Toast.makeText(getApplicationContext(), resultCode + "" + message, Toast.LENGTH_SHORT).show();

        }
    }

    public void getTimeFromDevice() {
        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);
        final TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);
                }

                hr = view.getCurrentHour();
                min = view.getCurrentMinute();
                getTime();

            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(SetNotification.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose hour:");

        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        timePickerDialog.show();


    }


    public void getTime() {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setHours(hr);
        date.setMinutes(min);
        String strDate = dateFormat.format(date);


        Log.d("Date", "" + strDate);

        Log.d("TT", "" + min);
    //    btn_noti.setText(strDate);
        MessageReciver reciver = new MessageReciver(new Message());
        Intent intent = new Intent(getApplicationContext(), ServiceNoti.class);

        pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, min, pendingIntent);


        Log.d("YY", "" + hr + ":" + min);
        intent.putExtra("Min", min);
        intent.putExtra("Hr", hr);
        intent.putExtra("recive", reciver);
        startService(intent);


    }

}