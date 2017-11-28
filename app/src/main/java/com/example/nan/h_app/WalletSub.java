package com.example.nan.h_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class WalletSub extends AppCompatActivity {
    private TextView textView_wallet;
    private EditText Name, how, detail, date;

    private Spinner spinnerList;
    private SQLiteDatabase mDb;

    private Cursor mCursor;
    private String saveType;
    private ArrayAdapter<String> adapterDir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_sub);
        final String name = getIntent().getExtras().getString("tv");
        textView_wallet = findViewById(R.id.tv_subwallet01);
        textView_wallet.setText(name);
        spinnerList = findViewById(R.id.spin_subwallet_spin);
        MyOpenHelper mHelper = new MyOpenHelper(this);
        mDb = mHelper.getWritableDatabase();
        mCursor = mDb.rawQuery("SELECT * FROM typeTable ", null);
        final ArrayList<String> str = new ArrayList<String>();
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            str.add(mCursor.getString(mCursor.getColumnIndex(MyOpenHelper.COL_TYPE)));
            mCursor.moveToNext();
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
        date = findViewById(R.id.edt_subwallet_date);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getDefault());

        Date datee = new Date();
        String strDate = dateFormat.format(datee);
        Log.d("Date", "" + strDate);
        date.setText(strDate);
        date.setClickable(true);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                final Calendar myCalender = Calendar.getInstance();
                int day = myCalender.get(Calendar.DAY_OF_MONTH);
                int month = myCalender.get(Calendar.MONTH);
                int year = myCalender.get(Calendar.YEAR);
                Toast.makeText(getApplicationContext(), "" + day + "\n" + month + "\n" + year + "\n" , Toast.LENGTH_SHORT).show();

             final DatePickerDialog myTimeListener = new DatePickerDialog(getApplicationContext(),new DatePickerDialog.OnDateSetListener(){



             });
            }
        });
    }

    public void onAddData(View view) {

        SubWalletTABLE subWalletTABLE = new SubWalletTABLE(getApplicationContext());
        String table = getIntent().getExtras().getString("tv");
        String strDate = date.getText().toString();
        Log.d("date", "" + strDate);
        String strName = Name.getText().toString();
        Log.d("name", "" + strName);
        String strMoney = how.getText().toString();
        Log.d("how", "" + strMoney);
        String strDetail = detail.getText().toString();
        Log.d("detail", "" + strDetail);
        Log.d("type", "" + saveType + table);
        String send1 = new String("INSERT INTO " + table);
        String send2 = new String("(subwallet_date,subwallet_record_name,subwallet_record_in,subwallet_record_out,subwallet_detail) ");
        String send3 = new String("VALUES ('" + strDate + "','" + strName + "','" + strMoney + "','null','" + strDetail + "');");
        String send = send1 + send2 + send3;
        Log.d("Long", send);
        subWalletTABLE.addDataSubWallet(send);
        Intent rr = new Intent(WalletSub.this, MainActivity.class);
        rr.putExtra("table", table);
        startActivity(rr);
        finish();
    }

    public void showdata(View view) {
        String table = getIntent().getExtras().getString("tv");
        Intent rr = new Intent(WalletSub.this, ShowWallet.class);
        rr.putExtra("table", table);
        startActivity(rr);
        finish();
    }

    public void onDelWallet(View view) {
        String name = getIntent().getExtras().getString("tv");
        WalletTABLE walletTABLE = new WalletTABLE(getApplicationContext());
        SubWalletTABLE subWalletTABLE = new SubWalletTABLE(getApplicationContext());
        walletTABLE.deleteWallet(name);
        subWalletTABLE.dropSubWallet(name);
        Intent i = new Intent(WalletSub.this, ActivityWallet.class);
        startActivity(i);
        finish();

    }

    public void onEditWallet(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Rename");
        alertDialog.setMessage("Enter Password");

        final EditText input = new EditText(WalletSub.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String name = getIntent().getExtras().getString("tv");
                        String password = input.getText().toString();
                        WalletTABLE walletTABLE = new WalletTABLE(getApplicationContext());
                        SubWalletTABLE subWalletTABLE = new SubWalletTABLE(getApplicationContext());
                        walletTABLE.editWalletToSQLite(name, password);
                        subWalletTABLE.editSubWallet(name, password);
                        Intent i = new Intent(getApplicationContext(), ActivityWallet.class);
                        startActivity(i);
                        finish();

                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    private void showRepeat(String saveType) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("HaveRepeat");
        builder.setMessage(saveType + "\n" + "Have to set" + "\n" + "Please Fill Again");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showAlert() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("HaveSpace");
        builder.setMessage("Fill in the blank");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, ActivityWallet.class);
        startActivity(i);
        finish();
    }


}