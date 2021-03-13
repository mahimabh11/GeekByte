package com.example.geekapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit_Name extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText fname;
    EditText lname;
    Button update2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__name);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        pref=getApplicationContext().getSharedPreferences("UserData", 0); // 0 - for private mode
        editor = pref.edit();
        fname=findViewById(R.id.firstName);
        lname=findViewById(R.id.lastName);
        update2=findViewById(R.id.update2);
        if(pref.contains("FirstName"))
        {
            String username=pref.getString("FirstName",null);
            fname.setText(username);
        }
        if(pref.contains("LastName"))
        {
            String username=pref.getString("LastName",null);
            lname.setText(username);
        }
        update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((fname.getText().toString()).length() == 0) {
                    Toast.makeText(Edit_Name.this, "Please enter valid name", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (pref.contains("FirstName")) {
                        editor.remove("FirstName");
                        editor.commit();
                        editor.putString("FirstName", fname.getText().toString());
                        editor.commit();
                    } else {
                        editor.putString("FirstName", fname.getText().toString());
                        editor.commit();
                    }
                    if (pref.contains("LastName")) {
                        //String user_name=pref.getString("FirstName",null);
                        editor.remove("LastName");
                        editor.commit();
                        editor.putString("LastName", lname.getText().toString());
                        editor.commit();
                    } else {
                        editor.putString("LastName", lname.getText().toString());
                        editor.commit();
                    }
                    Toast.makeText(Edit_Name.this, "Your name has been updated successfully!", Toast.LENGTH_SHORT).show();
                    if(!pref.contains("Phone"))
                    {
                        Intent intent= new Intent(Edit_Name.this, Edit_Number.class);
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