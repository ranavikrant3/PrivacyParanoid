package com.example.privacyparanoid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        final Context mycontext = getApplicationContext();
        //For enabling fake location
        Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                enableFake(mycontext);
            }
        });
        //For disabling fake location
        Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                disableFake(mycontext);
            }
        });
    }
    public void enableFake(Context context){
        Location fake_location_gps = new Location(LocationManager.GPS_PROVIDER);
        Location fake_location_network = new Location(LocationManager.NETWORK_PROVIDER);
        fake_location_gps.setLatitude(0);
        fake_location_gps.setLongitude(0);
        fake_location_gps.setAccuracy((float) 0.001);
        fake_location_gps.setAltitude(0);
        fake_location_gps.setSpeed(0);
        long system_time_now=System.currentTimeMillis();
        fake_location_gps.setTime(system_time_now);
        fake_location_gps.setElapsedRealtimeNanos(System.nanoTime());

        fake_location_network.setLatitude(0);
        fake_location_network.setLongitude(0);
        fake_location_network.setAccuracy((float) 0.001);
        fake_location_network.setAltitude(0);
        fake_location_network.setSpeed(0);
        long system_time_now2=System.currentTimeMillis();
        fake_location_network.setTime(system_time_now2);
        fake_location_network.setElapsedRealtimeNanos(System.nanoTime());
        LocationManager location_manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            location_manager.addTestProvider(LocationManager.GPS_PROVIDER, false, false, false, false, true, true, true, 0, 1);
            location_manager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);
            location_manager.setTestProviderLocation(LocationManager.GPS_PROVIDER, fake_location_gps);
            location_manager.addTestProvider(LocationManager.NETWORK_PROVIDER, false, false, false, false, true, true, true, 0, 1);
            location_manager.setTestProviderEnabled(LocationManager.NETWORK_PROVIDER, true);
            location_manager.setTestProviderLocation(LocationManager.NETWORK_PROVIDER, fake_location_network);
            Toast.makeText(context, "Fake Location enabled!!!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Fake Location failed. Make sure you have given the permissions in developer settings.", Toast.LENGTH_SHORT).show();
        }
    }
    public void disableFake(Context context){
        LocationManager location_manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try{
            location_manager.setTestProviderEnabled(LocationManager.GPS_PROVIDER,false);
            location_manager.setTestProviderEnabled(LocationManager.NETWORK_PROVIDER,false);
            Toast.makeText(context, "Fake Location disabled!!!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context, "Fake Location already disabled!!!", Toast.LENGTH_SHORT).show();
        }
    }

}