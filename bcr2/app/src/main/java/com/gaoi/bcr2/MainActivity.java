package com.gaoi.bcr2;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver;
    MyReceiver2 myReceiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.gaoi.bcr1");
        registerReceiver(myReceiver, intentFilter);

        myReceiver2 = new MyReceiver2();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(myReceiver2, intentFilter1);
    }

    public void send(View view){
        Intent intent = new Intent();
        intent.setAction("com.gaoi.bcr1");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        unregisterReceiver(myReceiver2);
    }
}
