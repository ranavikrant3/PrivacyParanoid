package com.example.privacyparanoid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DeviceWipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_wipe);
        final Context mycontext = getApplicationContext();
        //For enabling wipe
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                enableWipe(mycontext);
            }
        });
        //For disabling wipe
        Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                disableWipe(mycontext);
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




    public void enableWipe(Context context){
        DevicePolicyManager device_policy_manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName device_admin_receiver = new ComponentName(context, MyDeviceAdminReceiver.class);
        if(checkAdmin(device_policy_manager,device_admin_receiver)){

            device_policy_manager.setMaximumFailedPasswordsForWipe(device_admin_receiver, 20);
            Toast.makeText(context, "Password Brute Force Wipe Enabled!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            manageAdmin(context,device_admin_receiver);
        }
    }
    public void disableWipe(Context context){
        DevicePolicyManager device_policy_manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName device_admin_receiver = new ComponentName(context, MyDeviceAdminReceiver.class);
        if(checkAdmin(device_policy_manager,device_admin_receiver)){
            device_policy_manager.setMaximumFailedPasswordsForWipe(device_admin_receiver,Integer.MAX_VALUE);
            Toast.makeText(context, "Password Brute Force Wipe Disabled!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            manageAdmin(context,device_admin_receiver);
        }
    }
}