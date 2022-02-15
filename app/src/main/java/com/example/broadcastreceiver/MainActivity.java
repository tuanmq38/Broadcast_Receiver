package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
/*
 * Answers to questions
 *
 * Q1 - What is a system broadcast?
 * A1 - A message that the Android system sends when a system event occurs.
 *
 * Q2 - Which pair of methods do you use to register and unregister your broadcast receiver dynamically?
 * A2 - registerReceiver() and unRegisterReceiver().
 *
 * Q3 - Which of the following are true?
 * A3 - Broadcast receivers can't see or capture the intents used to start an activity.
 * Using a broadcast intent, you can't find or start an activity.
 *
 * Q4 - Which class is used to mitigate the security risks of broadcast receivers when the broadcasts are not cross-application (that is, when broadcasts are sent and received by the same app)?
 * A4 - LocalBroadcastManager
 * */

public class MainActivity extends AppCompatActivity {
    private MyReceiver mReceiver = new MyReceiver();
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        this.registerReceiver(mReceiver, filter);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver,
                        new IntentFilter(ACTION_CUSTOM_BROADCAST));

        textView = findViewById(R.id.textView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
    }

    //Button click event listening
    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        int num = randomNum();
        textView.setText(String.format(String.valueOf(num)));
        customBroadcastIntent.putExtra("random", num);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }

    public int randomNum(){
        Random random = new Random();
        return random.nextInt(20) + 1;
    }
}