package com.example.nan.h_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingSpinnerEdit extends AppCompatActivity {
    private String strEditType, saveType;
    private int strEditKey;
    private EditText editType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settinf_spinner_edit);
       strEditType = getIntent().getExtras().getString("type");
        strEditKey = getIntent().getExtras().getInt("id");

        editType = findViewById(R.id.edt_spin_edit);
        editType.setHint(strEditType);


        Button button_edit = findViewById(R.id.btn_spin_edit);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveType = editType.getText().toString();
                Toast.makeText(getApplicationContext(), "mm" + strEditType + "\n" + saveType, Toast.LENGTH_SHORT).show();
                if (saveType.equals("")) {
                    showAlert();
                } else {
                    EditDatatoSQLite(strEditType, strEditKey, saveType);
                }


            }
        });
    }

    private void showAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    private void EditDatatoSQLite(String oldValue, int oldKey, String newValue) {


        TypeTABLE typeTABLE = new TypeTABLE(this);
        String strings = typeTABLE.editValueToSQLite(oldKey, oldValue, newValue);
        editType.setText("");
        Toast.makeText(getApplicationContext(), "Finish" + "\n" + strings, Toast.LENGTH_SHORT);
        Intent ii = new Intent(this, SettingSpinType.class);
        startActivity(ii);
        finish();
    }
}
