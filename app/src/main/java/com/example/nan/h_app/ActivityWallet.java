package com.example.nan.h_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.nan.h_app.fragment.WalletFragment;

import java.util.ArrayList;

public class ActivityWallet extends AppCompatActivity {
    private WalletTABLE walletTable;
    private ListView lvWallet;
    private Cursor mCursor;
    private MyOpenHelper myOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fg_wallet, new WalletFragment())
                    .commit();
        }

        Button btnWallet01 = findViewById(R.id.btn_wallet01);
        btnWallet01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivityWallet.this);
                alertDialog.setTitle("PASSWORD");
                alertDialog.setMessage("Enter Password");
                final EditText input = new EditText(ActivityWallet.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                String saveType = input.getText().toString();
                                walletTable = new WalletTABLE(getApplicationContext());
                                if (saveType.equals("")) {

                                    showAlert();

                                } else {

                                    boolean bl = walletTable.checkVallue(saveType);
                                    if (bl == false) {

                                        showRepeat(saveType);

                                    } else {
                                        walletTable.addNewWalletValueToSQLite(saveType);
                                        crateTable(saveType);
                                        Intent i = new Intent(getApplicationContext(), WalletSub.class);
                                        i.putExtra("tv", saveType);
                                        startActivity(i);

                                        finish();
                                    }
                                }

                            }
                        });

                alertDialog.show();

            }
        });

        lvWallet = findViewById(R.id.lv_wallet);
        walletTable = new WalletTABLE(this);
        mCursor = walletTable.readAllData();
        ArrayList<String> str = new ArrayList<String>();
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            str.add(mCursor.getString(mCursor.getColumnIndex(MyOpenHelper.WALLET_NAME)) + "\n");
            mCursor.moveToNext();
        }
        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        lvWallet.setAdapter(adapterDir);
        lvWallet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCursor.moveToPosition(position);
                String saveType = mCursor.getString(mCursor.getColumnIndex(WalletTABLE.COLUMN_WALLET_NAME));
                Intent i = new Intent(getApplicationContext(), WalletSub.class);
                i.putExtra("tv", saveType);
                startActivity(i);
            }
        });

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

    private void crateTable(String saveType) {
        String SUB_WALLET_TABLE_NAME = saveType;
        String SUB_WALLET_COLUMN_ID = "_subwallet_id";
        String SUB_WALLET_COLUMN_DATE = "subwallet_date";
        String SUB_WALLET_COLUMN_RECORD_NAME = "subwallet_record_name";
        String SUB_WALLET_COLUMN_RECORD_IN = "subwallet_record_in";
        String SUB_WALLET_COLUMN_RECORD_OUT = "subwallet_record_out";
        String SUB_WALLET_COLUMN_DETAIL = "subwallet_detail";
        String CREATE_TABLE_Sub = "create table " + SUB_WALLET_TABLE_NAME + "("
                + SUB_WALLET_COLUMN_ID + " integer primary key,"
                + SUB_WALLET_COLUMN_DATE + " text,"
                + SUB_WALLET_COLUMN_RECORD_NAME + " text,"
                + SUB_WALLET_COLUMN_RECORD_IN + " integer,"
                + SUB_WALLET_COLUMN_RECORD_OUT + " integer,"
                + SUB_WALLET_COLUMN_DETAIL + " text);";

        SubWalletTABLE subWalletTABLE = new SubWalletTABLE(this);
        subWalletTABLE.createNewWallet(CREATE_TABLE_Sub);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


}