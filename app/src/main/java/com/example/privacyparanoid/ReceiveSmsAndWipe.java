package com.example.privacyparanoid;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class ReceiveSmsAndWipe extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferencesGetSet myobj=new SharedPreferencesGetSet();

        if (myobj.getKey()==0){return;}
        SmsMessage array_of_sms[] = android.provider.Telephony.Sms.Intents.getMessagesFromIntent(intent);
        int sms_index=0;
        for (sms_index=0;sms_index<array_of_sms.length;sms_index++){
            String sms = array_of_sms[sms_index].getDisplayMessageBody();

            if (sms.equals("wipe"+Long.toString(myobj.getKey()))){

                DevicePolicyManager device_policy_manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
                ComponentName device_admin_receiver = new ComponentName(context, MyDeviceAdminReceiver.class);
                if(checkAdmin(device_policy_manager,device_admin_receiver)){
                    //Real destruction begins here
                    device_policy_manager.wipeData(0);

                }
                else {
                    Toast.makeText(context, "Not a device admin!!!", Toast.LENGTH_LONG).show();
                }
            }

            if (sms.equals("lock"+Long.toString(myobj.getKey()))){
                DevicePolicyManager device_policy_manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
                ComponentName device_admin_receiver = new ComponentName(context, MyDeviceAdminReceiver.class);
                if(checkAdmin(device_policy_manager,device_admin_receiver)){
                    //Locking begins here
                    device_policy_manager.lockNow();

                }
                else {
                    Toast.makeText(context, "Not a device admin!!!", Toast.LENGTH_LONG).show();
                }
            }

            }

        }
    public static Boolean checkAdmin(DevicePolicyManager device_policy_manager, ComponentName device_admin_receiver){
        if (device_policy_manager.isAdminActive(device_admin_receiver)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    }


