package com.example.geekapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Edit_email extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText email;
    Button update4;
    public static final Pattern EMAIL_ADDRESS = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+"\\@"+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
            "("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+")+");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_email);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        pref=getApplicationContext().getSharedPreferences("UserData", 0); // 0 - for private mode
        editor = pref.edit();
        update4=findViewById(R.id.update4);
        email=findViewById(R.id.set_email);
        if(pref.contains("Email"))
        {
            String username=pref.getString("Email",null);
            email.setText(username);
        }

        update4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches() || email.getText().toString().trim().length() <= 0) {
                    Toast.makeText(Edit_email.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();

                } else {
                    if (pref.contains("Email")) {
                        editor.remove("Email");
                        editor.commit();
                        editor.putString("Email", email.getText().toString());
                        editor.commit();
                    } else {
                        editor.putString("Email", email.getText().toString());
                        editor.commit();
                    }
                    Toast.makeText(Edit_email.this, "Your email address has been updated successfully!", Toast.LENGTH_SHORT).show();
                    if(!pref.contains("Bio"))
                    {
                        Intent intent= new Intent(Edit_email.this, Edit_Bio.class);
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