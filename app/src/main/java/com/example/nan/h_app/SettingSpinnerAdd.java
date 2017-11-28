package com.example.nan.h_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingSpinnerAdd extends AppCompatActivity {
    private EditText edtType;
    private String saveType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_spinner_add);
        edtType = findViewById(R.id.edt_spin_add);
    }

    public void clickSave(View view) {
        saveType = edtType.getText().toString();
        if (saveType.equals("")) {
            showAlert();
        } else {
            TypeTABLE typeTABLE = new TypeTABLE(getApplicationContext());
            boolean bl = typeTABLE.checkVallue(saveType);
            if (bl == false) {
                showRepeat(saveType);
            } else {
                showLog(saveType);
                confirmData();
            }
        }
    }

    private void confirmData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm?");
        builder.setMessage("Confirm to save " + saveType + "To type");
        builder.setCancelable(false);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                upDatatoSQLite();
                Intent ii = new Intent(getApplicationContext(), SettingSpinType.class);
                startActivity(ii);
                finish();
            }
        });
        builder.show();
    }

    private void upDatatoSQLite() {
        TypeTABLE typeTABLE = new TypeTABLE(this);
        String string = typeTABLE.addNewValueToSQLite(saveType);
        edtType.setText("");
        Toast.makeText(getApplicationContext(), "Finish", Toast.LENGTH_SHORT);
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    private void showRepeat(String saveType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    private void showLog(String check) {
        Log.d("TYPEEEEEEEE", "" + saveType);
    }
}

