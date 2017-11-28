package com.example.nan.h_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Ribbin on 16-Nov-17.
 */

public class WalletTABLE {
    // create table walletTable(_wallet_id integer primary key,wallet_name text); ";

    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite, editSQLite, deleteSQLite;
    public static final String TABLE_WALLET = "walletTable";
    public static final String COLUMN_WALLLET_ID = "_wallet_id";
    public static final String COLUMN_WALLET_NAME = "wallet_name";

    public WalletTABLE(Context context) {
        myOpenHelper = new MyOpenHelper(context);
        writeSQLite = myOpenHelper.getWritableDatabase();
        readSQLite = myOpenHelper.getReadableDatabase();
        editSQLite = myOpenHelper.getWritableDatabase();
        deleteSQLite = myOpenHelper.getWritableDatabase();
    }

    public Cursor readAllData() {
        Cursor cursor = readSQLite.rawQuery("SELECT _wallet_id , wallet_name FROM walletTable", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }


    public String addNewWalletValueToSQLite(String str) {
        ContentValues contentValues;
        contentValues = new ContentValues();//
        contentValues.put(COLUMN_WALLET_NAME, str);//
        writeSQLite.insert(TABLE_WALLET, null, contentValues);
        return contentValues.toString();
    }

      public void editWalletToSQLite(String oldValue, String newValue) {
         String stredt = new String("UPDATE "+TABLE_WALLET+" SET "



                 +COLUMN_WALLET_NAME+" = '"+newValue+"' WHERE "+COLUMN_WALLET_NAME+" = '"+oldValue+"';");

       editSQLite.execSQL(stredt) ;

            Log.d("TestString", ""+stredt);
        //    editSQLite.execSQL("UPDATE " + TABLE_TYPE + " SET " + COLUMN_TYPE + "='" + newValue + "' WHERE " + COLUMN_TYPE_ID + "='" + oleKey + "'" + " AND " + COLUMN_TYPE + "='" + oldValue + "';");
         //   return newValue;
        }


    public void deleteWallet(String del) {
        String Delete = new String("DELETE FROM " + TABLE_WALLET + " WHERE " + COLUMN_WALLET_NAME + " = '" + del + "';");
    //   Log.d("TestString", "" + Delete);
        deleteSQLite.execSQL(Delete);

    }   /*

       */

    public boolean checkVallue(String check) {
        Cursor cursor = readSQLite.rawQuery("SELECT " + COLUMN_WALLET_NAME + " FROM " + TABLE_WALLET + " WHERE " + COLUMN_WALLET_NAME + "='" + check + "'", null);
        boolean bl = new Boolean("");
        if (cursor.getCount() == 0) {
            bl = true;
        } else {
            bl = false;
        }
        return bl;

    }

}
