package com.gaoi.service_demo;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    boolean flag;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onCreate() {
        Log.i("<<--------------------------->>", "创建service");
        flag = true;
        super.onCreate();
    }

    @SuppressLint("LongLogTag")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("<<--------------------------->>", "启动service");
        String str = intent.getStringExtra("1");
        Log.i("<<--------------------------->>", str);
        new Thread(){
            public void run(){
                while (flag){
                    Log.i("<<--------------------------->>", "hello");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onDestroy() {
        Log.i("<<--------------------------->>", "停止service");
        super.onDestroy();
        flag = false;
    }
}
