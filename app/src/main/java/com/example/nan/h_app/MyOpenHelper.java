package com.example.nan.h_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ribbin on 13-Nov-17.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    public static final String COL_TYPE = "type";
    public static final String COL_ID = "_type_id";
    public static final String WALLET_ID = "_wallet_id";
    public static final String WALLET_NAME = "wallet_name";
    private static final String DATABASE_NAME = "My_Wallet.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE01 = "create table typeTable(_type_id integer primary key,type text); ";
    private static final String CREATE_TABLE02 = "create table walletTable(_wallet_id integer primary key,wallet_name text); ";

    public MyOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE01);
        db.execSQL(CREATE_TABLE02);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
