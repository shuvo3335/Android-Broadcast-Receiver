package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    //ImageView ChargingImageView;
    GifImageView ChargingImageView;
    IntentFilter ChargingIntentFilter;
    ChargingBroadcasrReceiver chargingBroadcasrReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChargingImageView = findViewById(R.id.chargingImage);
        ChargingIntentFilter = new IntentFilter();
        ChargingIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        ChargingIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        chargingBroadcasrReceiver = new ChargingBroadcasrReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(chargingBroadcasrReceiver,ChargingIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(chargingBroadcasrReceiver);
    }

    private void showCharging(Boolean isCharging){
        if (isCharging){
            ChargingImageView.setImageResource(R.drawable.power);
            Toast.makeText(getApplicationContext(),"charging",Toast.LENGTH_SHORT).show();
        }else {
            ChargingImageView.setImageResource(R.drawable.charger_not);
        }
    }
    private class ChargingBroadcasrReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean isCharging = action.equals(Intent.ACTION_POWER_CONNECTED);
            showCharging(isCharging);
        }
    }
}


