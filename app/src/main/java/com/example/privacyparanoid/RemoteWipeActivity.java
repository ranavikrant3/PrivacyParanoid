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
    }
    public void enableAndGenerateWipeKey(Context context){
        SharedPreferences wipeKeyStorage = context.getSharedPreferences("WipeKeyStorage",MODE_PRIVATE);
        SharedPreferences.Editor wipeKeyEditor = wipeKeyStorage.edit();
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
            wipeKeyEditor.putLong("dangerous_wipe_key", randomKey);
            Toast.makeText(context,Long.toString(randomKey), Toast.LENGTH_LONG).show();
        }
    }
    public void disableWipeKey(Context context){
        SharedPreferences wipeKeyStorage = context.getSharedPreferences("WipeKeyStorage",MODE_PRIVATE);
        SharedPreferences.Editor wipeKeyEditor = wipeKeyStorage.edit();
        wipeKeyEditor.putLong("dangerous_wipe_key", 0L);
        Toast.makeText(context,"Disabled remote sms wipe!!!", Toast.LENGTH_LONG).show();
    }

}