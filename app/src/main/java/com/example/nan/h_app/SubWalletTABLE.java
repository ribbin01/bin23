package com.example.nan.h_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Nan on 18/11/2560.
 */

public class SubWalletTABLE {
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase createSQLite, writeSQLite, readSQLite, editSQLite, deleteSQLite;

    public static final String TABLE_WALLET = "walletTable";
    public static final String COLUMN_WALLLET_ID = "_wallet_id";
    public static final String COLUMN_WALLET_NAME = "wallet_name";
    // String SUB_WALLET_TABLE_NAME = saveType;
    public static final String SUB_WALLET_COLUMN_ID = "_subwallet_id";
    public static final String SUB_WALLET_COLUMN_DATE = "subwallet_date";
    public static final String SUB_WALLET_COLUMN_RECORD_NAME = "subwallet_record_name";
    public static final String SUB_WALLET_COLUMN_RECORD_IN = "subwallet_record_in";
    public static final String SUB_WALLET_COLUMN_RECORD_OUT = "subwallet_record_out";
    public static final String SUB_WALLET_COLUMN_DETAIL = "subwallet_detail";


    public SubWalletTABLE(Context context) {
        myOpenHelper = new MyOpenHelper(context);
        writeSQLite = myOpenHelper.getWritableDatabase();
        readSQLite = myOpenHelper.getReadableDatabase();
        editSQLite = myOpenHelper.getWritableDatabase();
        deleteSQLite = myOpenHelper.getWritableDatabase();
        createSQLite = myOpenHelper.getWritableDatabase();
    }

    public void createNewWallet(String str) {

        createSQLite.execSQL(str);

    }

    public Cursor readAllDataSubWallet(String savetype) {
        String strselect = new String("SELECT * FROM " + savetype);
        Cursor cursor = readSQLite.rawQuery(strselect, null);
        Log.d("sqlite", "" + strselect);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;

    }

    public String deleteSubWallet(String name, int date, int how, String del) {
        //  Log.d("Doo", "" + del);

        String Delete = new String("DELETE FROM " + del + " WHERE " + SUB_WALLET_COLUMN_RECORD_IN + " = '" + how + "' AND "
                + SUB_WALLET_COLUMN_RECORD_NAME + " = '" + name + "' AND " + SUB_WALLET_COLUMN_DATE + " = '" + date + "';");

        Log.d("Doo", "" + Delete);
   deleteSQLite.execSQL(Delete);
        return Delete;
    }

    public void dropSubWallet(String del) {
        //  Log.d("Doo", "" + del);
        String Drop = new String("DROP TABLE " + del + ";");

        //  Log.d("Doo", "" + Delete);
        deleteSQLite.execSQL(Drop);

    }

    public void editSubWallet(String oldValue, String newValue) {
        String Edit = new String("ALTER TABLE " + oldValue + " RENAME TO " + newValue + ";");
        editSQLite.execSQL(Edit);
        Log.d("DooDEE", "" + Edit);

    }

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

    public void addDataSubWallet(String Value) {
        writeSQLite.execSQL(Value);

    }
}
