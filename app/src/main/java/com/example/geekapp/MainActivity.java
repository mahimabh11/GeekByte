package com.example.geekapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int GET_FROM_GALLERY = 3;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private ImageView imageView;
    TextView name;
    TextView phone;
    TextView email;
    TextView bio;
    Context context;
    CardView name_card;
    CardView phone_card;
    CardView email_card;
    CardView bio_card;
    ConstraintLayout mainlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_GeekApp);
        setContentView(R.layout.activity_main);
        context=this;
        mainlayout=findViewById(R.id.mainlayout); // the constraint layout of the main screen
        pref=getApplicationContext().getSharedPreferences("UserData", 0); // 0 - for private mode
        editor = pref.edit();
        // If the FirstName does not exist in the Shared Preferences, it implies new user
        if(!pref.contains("FirstName"))
        {
            mainlayout.setVisibility(View.INVISIBLE);
            Button proceed;
            // opens welcome dialog
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_entry);
            proceed=dialog.findViewById(R.id.proceed);
            // On clicking the "Lets go" button, redirects to name editing activity
            // Since we only have a single button on the dialog, I have used setOnClickListener for the specific button instead of
            // generalizing it
            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(MainActivity.this, Edit_Name.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    dialog.dismiss();
                }
            });
            dialog.show();

        }
        imageView = (ImageView) findViewById(R.id.profilepic);
        name=findViewById(R.id.name_ans);
        phone=findViewById(R.id.phone_ans);
        email=findViewById(R.id.email_ans);
        bio=findViewById(R.id.bio_ans);
        bio.setOnClickListener(this);


        // The following code is to display the user's details on the profile page if they exist
        String temp="";
        if(pref.contains("FirstName"))
        {
            String username=pref.getString("FirstName",null);
            temp=temp+username+" ";
        }
        if(pref.contains("LastName"))
        {
            String username=pref.getString("LastName",null);
            temp=temp+username;
        }
        name.setText(temp);
        if(pref.contains("Phone"))
        {
            String t="";
            t=pref.getString("Phone",null);
            phone.setText(t);
        }
        if(pref.contains("Email"))
        {
            String t="";
            t=pref.getString("Email",null);
            email.setText(t);
        }
        if(pref.contains("Bio"))
        {
            String t="";
            t=pref.getString("Bio",null);
            bio.setText(t);
        }
        if(pref.contains("Image"))
        {
            String t="";
            t=pref.getString("Image",null);
            Uri img_uri=Uri.parse(t);
            imageView.setImageURI(img_uri);
        }

        name_card=findViewById(R.id.Name_title);
        phone_card=findViewById(R.id.Phone_title);
        email_card=findViewById(R.id.email_title);
        bio_card=findViewById(R.id.Bio_view);

        name_card.setOnClickListener(this);
        phone_card.setOnClickListener(this);
        email_card.setOnClickListener(this);
        bio_card.setOnClickListener(this);
        imageView.setOnClickListener(this);

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        mainlayout.setVisibility(View.VISIBLE);
        String temp="";
        if(pref.contains("FirstName"))
        {
            String username=pref.getString("FirstName",null);
            temp=temp+username+" ";
        }
        if(pref.contains("LastName"))
        {
            String username=pref.getString("LastName",null);
            temp=temp+username;
        }
        name.setText(temp);
        if(pref.contains("Phone"))
        {
            String t="";
            t=pref.getString("Phone",null);
            phone.setText(t);
        }
        if(pref.contains("Email"))
        {
            String t="";
            t=pref.getString("Email",null);
            email.setText(t);
        }
        if(pref.contains("Bio"))
        {
            String t="";
            t=pref.getString("Bio",null);
            bio.setText(t);
        }
        if(pref.contains("Image"))
        {
            String t="";
            t=pref.getString("Image",null);
            Uri img_uri=Uri.parse(t);
            imageView.setImageURI(img_uri);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode== GET_FROM_GALLERY) {
//            Bitmap bitmap = getPath(data.getData());
//            imageView.setImageBitmap(bitmap);
            Uri myuri=data.getData();
            imageView.setImageURI(myuri);
            System.out.println(myuri);
            editor.putString("Image",myuri.toString());
            editor.commit();
            Toast.makeText(MainActivity.this, "Your profile picture has been updated successfully!", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        System.out.println("Yayyy!");
        switch (v.getId()) {

            case R.id.Name_title:
                intent= new Intent(MainActivity.this, Edit_Name.class);
                System.out.println("name1");
                startActivity(intent);
                overridePendingTransition(0,0);
                System.out.println("name");
                break;

            case R.id.Phone_title:
                intent= new Intent(MainActivity.this, Edit_Number.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;

            case R.id.email_title:
                intent= new Intent(MainActivity.this, Edit_email.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;

            case R.id.Bio_view:
                System.out.println("BIO ");
                intent= new Intent(MainActivity.this, Edit_Bio.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;

            case R.id.bio_ans:
                System.out.println("BIO ");
                intent= new Intent(MainActivity.this, Edit_Bio.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;

            case R.id.profilepic:
                startActivityForResult(
                        new Intent(
                                Intent.ACTION_OPEN_DOCUMENT,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        ),
                        GET_FROM_GALLERY
                );
                break;

        }
    }
}