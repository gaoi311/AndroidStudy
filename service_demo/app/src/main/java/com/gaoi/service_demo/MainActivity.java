package com.gaoi.service_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = new Intent(MainActivity.this, MyService.class);
    }

    public void start(View view){
        service.putExtra("1", "启动service...");
        startService(service);
    }

    public void stop(View view){
        stopService(service);
    }
}
