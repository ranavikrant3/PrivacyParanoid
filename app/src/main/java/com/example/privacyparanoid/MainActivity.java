package com.example.privacyparanoid;
//Privacy Protection by github.com/vikrantrana1996
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context mycontext = getApplicationContext();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 0);

        //For CameraActivity
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openCameraActivity();
            }
        });

        //For Data Wiping Activity
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openDeviceWipeActivity();
            }
        });
        //For LocationActivity
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openLocationActivity();
            }
        });
        //For RemoteWipeActivity
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openRemoteWipeActivity();
            }
        });

        //For removing device admin
       Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                removeAdmin(mycontext);
            }
        });


    }

    public void openCameraActivity(){
        Intent intent = new Intent(this, CameraActivity.class);
        this.startActivity(intent);
    }
    public void openDeviceWipeActivity(){
        Intent intent = new Intent(this, DeviceWipeActivity.class);
        this.startActivity(intent);
    }
    public void openLocationActivity(){
        Intent intent = new Intent(this, LocationActivity.class);
        this.startActivity(intent);
    }
    public void openRemoteWipeActivity(){
        Intent intent = new Intent(this, RemoteWipeActivity.class);
        this.startActivity(intent);
    }

    public static Boolean checkAdmin(DevicePolicyManager device_policy_manager,ComponentName device_admin_receiver){
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
    public void removeAdmin(Context context){

        DevicePolicyManager device_policy_manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName device_admin_receiver = new ComponentName(context, MyDeviceAdminReceiver.class);
        Toast.makeText(context, "Going to remove admin!!!", Toast.LENGTH_SHORT).show();
        manageAdmin(context,device_admin_receiver);

    }
}