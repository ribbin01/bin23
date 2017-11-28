package com.example.nan.h_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingSpinType extends AppCompatActivity {
    private Spinner spinnerList;
    private SQLiteDatabase mDb;
    private MyOpenHelper mHelper;
    private Cursor mCursor;
    private String saveType;
    private ArrayAdapter<String> adapterDir;
    private Button show, addbtn, deletebtn, editbtn;
    private int po;
    private int indexCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_spin_type);

        spinnerList = findViewById(R.id.setting_spinner);
        addbtn = findViewById(R.id.btn_setting_spin_02);
        show = findViewById(R.id.btn_setting_spin_01);
        deletebtn = findViewById(R.id.btn_setting_spin_04);
        editbtn = findViewById(R.id.btn_setting_spin_03);
        mHelper = new MyOpenHelper(this);
        mDb = mHelper.getWritableDatabase();
        mCursor = mDb.rawQuery("SELECT * FROM typeTable ", null);
        int count = mCursor.getCount();
        final ArrayList<String> str = new ArrayList<String>();
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            str.add(mCursor.getString(mCursor.getColumnIndex(MyOpenHelper.COL_TYPE)));
            mCursor.moveToNext();
        }
        if (count == 0) {
            show.setClickable(false);
            editbtn.setClickable(false);
            deletebtn.setClickable(false);
        } else {
            editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    po = mCursor.getPosition();
                    mCursor.moveToPosition(po);
                    saveType = mCursor.getString(mCursor.getColumnIndex(TypeTABLE.COLUMN_TYPE));
                    indexCursor = mCursor.getInt(mCursor.getColumnIndex(TypeTABLE.COLUMN_TYPE_ID));
                    Intent yy = new Intent(getApplicationContext(), SettingSpinnerEdit.class);
                    yy.putExtra("type", saveType);
                    yy.putExtra("id", indexCursor);
                    startActivity(yy);
                    finish();
                }
            });
            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TypeTABLE typeTABLE = new TypeTABLE(getApplicationContext());
                    po = mCursor.getPosition();
                    mCursor.moveToPosition(po);
                    saveType = mCursor.getString(mCursor.getColumnIndex(TypeTABLE.COLUMN_TYPE));
                    indexCursor = mCursor.getInt(mCursor.getColumnIndex(TypeTABLE.COLUMN_TYPE_ID));
                    String dd = typeTABLE.deleteValueToSQLite(indexCursor, saveType);
                    recreate();

                }
            });

            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    po = mCursor.getPosition();
                    mCursor.moveToPosition(po);
                    saveType = mCursor.getString(mCursor.getColumnIndex(TypeTABLE.COLUMN_TYPE));
                    Toast.makeText(getApplicationContext(), "Your Selected : " + saveType, Toast.LENGTH_SHORT).show();
                }
            });
        }
        adapterDir = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
        spinnerList.setAdapter(adapterDir);
        spinnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View selectedItemView, int position, long id) {
                mCursor.moveToPosition(position);
                saveType = mCursor.getString(mCursor.getColumnIndex(TypeTABLE.COLUMN_TYPE));
                Toast.makeText(getApplicationContext(), "Your Selected : " + saveType, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SettingSpinnerAdd.class);
                startActivity(i);
                onStop();
                finish();
            }
        });


    }


}


