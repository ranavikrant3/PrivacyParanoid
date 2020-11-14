package com.example.privacyparanoid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class RemoteWipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_wipe);

        if(ContextCompat.checkSelfPermission(RemoteWipeActivity.this,Manifest.permission.READ_SMS)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 0);
        }


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
        Button button16 = (Button) findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                howToUse(getApplicationContext());
            }
        });


    }
    public void howToUse(Context context){
        Toast infotoast=Toast.makeText(context,"To lock your device, send SMS: lock<secretkey>\nTo wipe your device, send SMS: wipe<secretkey>", Toast.LENGTH_LONG);
        infotoast.show();
        infotoast.show();

    }

    public static Boolean checkAdmin(DevicePolicyManager device_policy_manager, ComponentName device_admin_receiver){
        if (device_policy_manager.isAdminActive(device_admin_receiver)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public void manageAdmin(Context context, ComponentName device_admin_receiver){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.settings","com.android.settings.DeviceAdminSettings"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void enableAndGenerateWipeKey(Context context){

        DevicePolicyManager device_policy_manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName device_admin_receiver = new ComponentName(context, MyDeviceAdminReceiver.class);
        if(checkAdmin(device_policy_manager,device_admin_receiver)){
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
                Toast.makeText(context,"Your secret key is: "+Long.toString(randomKey), Toast.LENGTH_LONG).show();
            }
        }
        else {
            manageAdmin(context,device_admin_receiver);
        }


    }

    public void getCurrentWipeKey(Context context){
        SharedPreferencesGetSet myobj=new SharedPreferencesGetSet();
        if (myobj.getKey()==0){
            Toast.makeText(context,"You have not set your secret key yet!!!", Toast.LENGTH_LONG).show();
        }
        else {
        Toast.makeText(context,"Your secret key is: "+Long.toString(myobj.getKey()), Toast.LENGTH_LONG).show();
        }
    }
    public void disableWipeKey(Context context){
        SharedPreferencesGetSet myobj=new SharedPreferencesGetSet();
        myobj.setKey(0L);
        Toast.makeText(context,"Disabled remote device wipe!!!", Toast.LENGTH_SHORT).show();
    }

}