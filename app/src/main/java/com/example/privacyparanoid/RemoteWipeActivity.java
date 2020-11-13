package com.example.privacyparanoid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class RemoteWipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_wipe);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 0);
        Button button13 = (Button) findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                enableAndGenerateWipeKey(getApplicationContext());
            }
        });
        Button button14 = (Button) findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                disableWipeKey(getApplicationContext());
            }
        });
        Button button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                getCurrentWipeKey(getApplicationContext());
            }
        });

    }
    public void enableAndGenerateWipeKey(Context context){
       // SharedPreferences wipeKeyStorage = context.getSharedPreferences("WipeKeyStorage",MODE_PRIVATE);
        //SharedPreferences.Editor wipeKeyEditor = wipeKeyStorage.edit();
        SharedPreferencesGetSet myobj=new SharedPreferencesGetSet();
        Random random=new Random();
        Long randomKey=0L;
        for (int x=0;x<10000;x++){
            //No more than 10000 iterations for this
             randomKey=random.nextLong()% 10000000000L;
             if (randomKey>1000000000L){break;}
        }
        if (randomKey==0L){
            Toast.makeText(context,"Failed to generate a secret key!!!", Toast.LENGTH_LONG).show();
        }
        else {
            myobj.setKey(randomKey);
            Toast.makeText(context,"Your secret key is: wipe"+Long.toString(randomKey), Toast.LENGTH_LONG).show();
        }
    }
    public void getCurrentWipeKey(Context context){
        SharedPreferencesGetSet myobj=new SharedPreferencesGetSet();
        if (myobj.getKey()==0){
            Toast.makeText(context,"You have not set your secret key yet!!!", Toast.LENGTH_LONG).show();
        }
        else {
        Toast.makeText(context,"Your secret key is: wipe"+Long.toString(myobj.getKey()), Toast.LENGTH_LONG).show();
        }
    }
    public void disableWipeKey(Context context){
        SharedPreferencesGetSet myobj=new SharedPreferencesGetSet();
        myobj.setKey(0L);
        Toast.makeText(context,"Disabled remote device wipe!!!", Toast.LENGTH_SHORT).show();
    }

}