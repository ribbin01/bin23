package com.example.nan.h_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Nan on 18/11/2560.
 */

public class SettingApp extends AppCompatActivity {
    private ListView lv_setting;
    private String[] strings_setting = {"List Type", "Spinner setType", "Set Notification"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        lv_setting = findViewById(R.id.lv_setting);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, strings_setting);
        lv_setting.setAdapter(adapter);
        lv_setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                if (arg2 == 0) {
                    Toast.makeText(getApplicationContext(), "เลือก" + arg2, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), SettingListType.class);
                    startActivity(i);
                    finish();


                } else if (arg2 == 1) {
                    Toast.makeText(getApplicationContext(), "เลือก" + arg2, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), SettingSpinType.class);
                    startActivity(i);
                    finish();

                } else if (arg2 == 2) {
                    Toast.makeText(getApplicationContext(), "เลือก" + arg2, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), SetNotification.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "เลือก" + arg2, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}