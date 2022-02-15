package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        if (intentAction != null) {
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    Toast.makeText(context, "Power disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    int randomNumber = intent.getExtras().getInt("random");
                    int square = randomNumber * randomNumber;
                    Toast.makeText(context, "Custom Broadcast Received\nSquare of the Random number: "
                            + square, Toast.LENGTH_LONG).show();
                    break;
            }
        }

        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            switch (state) {
                case 0:
                    Toast.makeText(context, "Headset was unplugged", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(context, "Headset is plugged", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }
    }
}