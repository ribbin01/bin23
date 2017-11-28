package com.example.nan.h_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Ribbin on 13-Nov-17.
 */

public class TypeTABLE {

    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writeSQLite, readSQLite, editSQLite, deleteSQLite;
    public static final String TABLE_TYPE = "typeTable";
    public static final String COLUMN_TYPE_ID = "_type_id";
    public static final String COLUMN_TYPE = "type";


    public TypeTABLE(Context context) {
        myOpenHelper = new MyOpenHelper(context);
        writeSQLite = myOpenHelper.getWritableDatabase();
        readSQLite = myOpenHelper.getReadableDatabase();
        editSQLite = myOpenHelper.getWritableDatabase();
        deleteSQLite = myOpenHelper.getWritableDatabase();
    }


    public Cursor readAllData() {
        Cursor cursor = readSQLite.rawQuery("SELECT _type_id , type FROM typeTable", null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;

    }


    public String addNewValueToSQLite(String str) {
        ContentValues contentValues;
        contentValues = new ContentValues();//
        contentValues.put(COLUMN_TYPE, str);//
        writeSQLite.insert(TABLE_TYPE, null, contentValues);
        return contentValues.toString();
    }

    public String editValueToSQLite(int oleKey, String oldValue, String newValue) {
        Log.d("TestString", "" + oleKey + "\n" + oldValue + "\n" + newValue);
        editSQLite.execSQL("UPDATE " + TABLE_TYPE + " SET " + COLUMN_TYPE + "='" + newValue + "' WHERE " + COLUMN_TYPE_ID + "='" + oleKey + "'" + " AND " + COLUMN_TYPE + "='" + oldValue + "';");
        return newValue;
    }

    public String deleteValueToSQLite(int oleKey, String oldValue) {
        String Delete = new String("DELETE FROM " + TABLE_TYPE + " WHERE " + COLUMN_TYPE_ID + " = '" + oleKey + "' AND " + COLUMN_TYPE + " = '" + oldValue + "';");
        Log.d("TestString", "" + Delete);
        deleteSQLite.execSQL(Delete);
        return Delete;
    }

    public boolean checkVallue(String check) {


        Cursor cursor = readSQLite.rawQuery("SELECT type FROM typeTable WHERE type='" + check + "'", null);
        boolean bl = new Boolean("");
        if (cursor.getCount() == 0) {
            bl = true;
        } else {
            bl = false;
        }

        return bl;

    }

}
