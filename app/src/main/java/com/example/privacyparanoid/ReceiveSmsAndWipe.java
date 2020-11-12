package com.example.privacyparanoid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsMessage;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class ReceiveSmsAndWipe extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences wipeKeyStorage = context.getSharedPreferences("WipeKeyStorage",MODE_PRIVATE);
        long dangerous_wipe_key=wipeKeyStorage.getLong("dangerous_wipe_key",0);

        if (dangerous_wipe_key==0){return;}
        SmsMessage array_of_sms[] = android.provider.Telephony.Sms.Intents.getMessagesFromIntent(intent);
        int sms_index=0;
        for (sms_index=0;sms_index<array_of_sms.length;sms_index++){
            String sms = array_of_sms[sms_index].getDisplayMessageBody();
            //Toast.makeText(context, sms, Toast.LENGTH_LONG).show();
            if (sms.equals(Long.toString(dangerous_wipe_key))){
                Toast.makeText(context, "DESTROYING DEVICE!!!", Toast.LENGTH_LONG).show();

            }

        }
    }
}
