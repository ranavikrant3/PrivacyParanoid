package com.example.privacyparanoid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        final Context mycontext = getApplicationContext();
        //For disabling
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openDisabler(mycontext);
            }
        });
        //For enabling
        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                openEnabler(mycontext);
            }
        });
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

    public void openDisabler(Context context){
        DevicePolicyManager device_policy_manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName device_admin_receiver = new ComponentName(context, MyDeviceAdminReceiver.class);
        if(checkAdmin(device_policy_manager,device_admin_receiver)){
            device_policy_manager.setCameraDisabled(device_admin_receiver, true);
            Toast.makeText(context, "Camera Disabled!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            manageAdmin(context,device_admin_receiver);
        }
    }
    public void openEnabler(Context context){
        DevicePolicyManager device_policy_manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName device_admin_receiver = new ComponentName(context, MyDeviceAdminReceiver.class);
        if(checkAdmin(device_policy_manager,device_admin_receiver)){
            device_policy_manager.setCameraDisabled(device_admin_receiver, false);
            Toast.makeText(context, "Camera Enabled!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            manageAdmin(context,device_admin_receiver);
        }
    }
}