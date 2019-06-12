package com.gaoi.service_bind;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    MyService.myBind myBind;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBind = (MyService.myBind) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this, MyService.class);
    }

    public void bind(View view){
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void unbind(View view){
        unbindService(serviceConnection);
    }

    public void getValue(View view){
        Toast.makeText(MainActivity.this, myBind.getCount() + "", Toast.LENGTH_SHORT).show();
    }

    public void setValue(View view){
        myBind.setCount(0);
    }

}
