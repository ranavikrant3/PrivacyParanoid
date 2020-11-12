package com.example.privacyparanoid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ReceiveSmsAndWipe extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Hola", Toast.LENGTH_LONG).show();

        SmsMessage array_of_sms[] = android.provider.Telephony.Sms.Intents.getMessagesFromIntent(intent);
        int sms_index=0;
        for (sms_index=0;sms_index<array_of_sms.length;sms_index++){
            String sms = array_of_sms[sms_index].getDisplayMessageBody();
            Toast.makeText(context, sms, Toast.LENGTH_LONG).show();
        }
    }
}
