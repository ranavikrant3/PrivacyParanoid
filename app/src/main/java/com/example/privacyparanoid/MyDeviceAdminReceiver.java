package com.example.privacyparanoid;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyDeviceAdminReceiver  extends DeviceAdminReceiver {
    @Override
    public void onDisabled(Context context, Intent intent) {

        super.onDisabled(context, intent);
        Toast.makeText(context, "Device admin disabled", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEnabled(Context context, Intent intent) {

        super.onEnabled(context, intent);
        Toast.makeText(context, "Device admin enabled", Toast.LENGTH_SHORT).show();

    }
}
