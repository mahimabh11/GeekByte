package com.example.geekapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_Bio extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText bio;
    Button update5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__bio);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        pref=getApplicationContext().getSharedPreferences("UserData", 0); // 0 - for private mode
        editor = pref.edit();
        update5=findViewById(R.id.update5);
        bio=findViewById(R.id.setbio);
        // If user's bio already exists, display it in the editable textbox so that the user can
        if(pref.contains("Bio"))
        {
            String username=pref.getString("Bio",null);
            bio.setText(username);
        }
        update5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((bio.getText().toString()).length() == 0) {
                    Toast.makeText(Edit_Bio.this, "Please enter valid details", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (pref.contains("Bio")) {
                        editor.remove("Bio");
                        editor.commit();
                        editor.putString("Bio", bio.getText().toString());
                        editor.commit();
                    } else {
                        editor.putString("Bio", bio.getText().toString());
                        editor.commit();
                    }
                    Toast.makeText(Edit_Bio.this, "Your Bio has been updated successfully!", Toast.LENGTH_SHORT).show();
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