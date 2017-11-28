package com.example.nan.h_app;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowWallet extends AppCompatActivity {
    ArrayList<HashMap<String, String>> MyArrList;
    Cursor mCursor;
    SubWalletTABLE subWalletTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_wallet);
        String text = getIntent().getExtras().getString("table");
        TextView tv = findViewById(R.id.tv_show_wallet);
        Log.d("tablr", text);
        tv.setText(text);
        final ListView lisView1 = findViewById(R.id.lv_subwallet_show);
        subWalletTABLE = new SubWalletTABLE(this);
        mCursor = subWalletTABLE.readAllDataSubWallet(text);
        ArrayList<String> str = new ArrayList<String>();
        mCursor.moveToFirst();
        final TextView textView2 = findViewById(R.id.tv_show_02);
        MyArrList = new ArrayList<HashMap<String, String>>();


        while (!mCursor.isAfterLast()) {
            final HashMap<String, String> map;
            map = new HashMap<String, String>();
            str.add(mCursor.getString(mCursor.getColumnIndex("subwallet_record_in")) + "\n");


            map.put("How", mCursor.getString(mCursor.getColumnIndex("subwallet_record_in")));
            map.put("Code", mCursor.getString(mCursor.getColumnIndex("subwallet_date")));
            map.put("Country", mCursor.getString(mCursor.getColumnIndex("subwallet_record_name")));
            MyArrList.add(map);
            mCursor.moveToNext();

        }
        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        lisView1.setAdapter(new CountryAdapter(this));
        lisView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, final View myView, final int position, long mylng) {


                //        Toast.makeText(ShowWallet.this, "" + name + "\n" + date + "\n" + How, Toast.LENGTH_SHORT).show();

                PopupMenu popupMenu = new PopupMenu(ShowWallet.this, textView2);
                popupMenu.getMenuInflater().inflate(R.menu.menu01, popupMenu.getMenu());
                popupMenu.getDragToOpenListener();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String iteme = item.toString();
                        mCursor.moveToPosition(position);
                        String name = mCursor.getString(mCursor.getColumnIndex("subwallet_record_name"));
                        int date = mCursor.getInt(mCursor.getColumnIndex("subwallet_date"));
                        int How = mCursor.getInt(mCursor.getColumnIndex("subwallet_record_in"));
                        String text = getIntent().getExtras().getString("table");
                        //  SubWalletTABLE subWalletTABLE1 = new SubWalletTABLE(ShowWallet.this);
                        switch (iteme) {
                            case "Show":
                                Toast.makeText(ShowWallet.this, "Showwwww", Toast.LENGTH_SHORT).show();
                                break;
                            case "Edit":
                                Toast.makeText(ShowWallet.this, "Edittttt", Toast.LENGTH_SHORT).show();
                                break;
                            case "Delete":


                                SubWalletTABLE subWalletTABLEDelete = new SubWalletTABLE(getApplicationContext());
                                subWalletTABLEDelete.deleteSubWallet(name, date, How, text);



                                break;
                        }
                        recreate();
                        return true;
                    }
                });

                popupMenu.show();

            }
        });

    }

    // private void DeleteData(String name, String date, String how, String text, int position) {

    //     String s = subWalletTABLE.deleteSubWallet(name, date, how, text);
    //     recreate();
    //  }
/*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Command for : [" +mCursor.getString(mCursor.getColumnIndex(SubWalletTABLE.SUB_WALLET_COLUMN_RECORD_NAME)) + "]");
        String[] menuItems = Cmd;

        for (int i = 0; i<menuItems.length; i++) {

            menu.add(Menu.NONE, i, i, menuItems[i]);

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int menuItemIndex = item.getItemId();
        String[] menuItems = Cmd;
        String CmdName = menuItems[menuItemIndex];
        String MemID = myData.getString(myData.getColumnIndex("MemberID"));
// Check Event Command
        if ("Edit".equals(CmdName)) {
            Toast.makeText(MainActivity.this,"Edit Command (MemberID = " + MemID + ")",Toast.LENGTH_LONG).show();
        } else if ("Delete".equals(CmdName)) {
            Toast.makeText(MainActivity.this,"Delete Command (MemberID = " + MemID + ")",Toast.LENGTH_LONG).show();
        } else if ("Hide".equals(CmdName)) {
            Toast.makeText(MainActivity.this,"Hide Command (MemberID = " + MemID + ")",Toast.LENGTH_LONG).show();
        }

        return true;

    }

*/


    public class CountryAdapter extends BaseAdapter {
        private Context context;

        public CountryAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return MyArrList.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            CountryHolder holder = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_show_wallet_column, null);
                holder = new CountryHolder();
                holder.How = convertView.findViewById(R.id.ColID);
                holder.Code = convertView.findViewById(R.id.ColCode);
                holder.Country = convertView.findViewById(R.id.ColCountry);
                convertView.setTag(holder);
            } else {
                holder = (CountryHolder) convertView.getTag();
            }
            holder.How.setText(MyArrList.get(position).get("How"));
            holder.Code.setText(MyArrList.get(position).get("Code"));
            holder.Country.setText(MyArrList.get(position).get("Country"));
            return convertView;
        }

    }

    public class CountryHolder {
        TextView How;
        TextView Code;
        TextView Country;

    }
}