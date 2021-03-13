package com.example.geekapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_Number extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText phone;
    Button update3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__number);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        pref=getApplicationContext().getSharedPreferences("UserData", 0); // 0 - for private mode
        editor = pref.edit();
        update3=findViewById(R.id.update3);
        phone=findViewById(R.id.number);
        if(pref.contains("Phone"))
        {
            String username=pref.getString("Phone",null);
            phone.setText(username);
        }
        update3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((phone.getText().toString().trim()).length() <10|| !PhoneNumberUtils.isGlobalPhoneNumber(phone.getText().toString().trim())) {
                    Toast.makeText(Edit_Number.this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                if (pref.contains("Phone")) {
                    editor.remove("Phone");
                    editor.commit();
                    editor.putString("Phone", phone.getText().toString().trim());
                    editor.commit();
                } else {
                    editor.putString("Phone", phone.getText().toString().trim());
                    editor.commit();
                }
                Toast.makeText(Edit_Number.this, "Your phone number has been updated successfully!", Toast.LENGTH_SHORT).show();
                    if(!pref.contains("Email"))
                    {
                        Intent intent= new Intent(Edit_Number.this, Edit_email.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                    }
                finish();
            }
        }

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == android.R.id.home ) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}