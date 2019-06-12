package com.gaoi.bcr1;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send1(View view){
        Intent intent = new Intent(MainActivity.this, MyReceiver.class);
        sendBroadcast(intent);
    }

    public void send2(View view){
        Intent intent = new Intent();
        intent.setAction("com.gaoi.bcr1");
        sendBroadcast(intent);
    }
}
