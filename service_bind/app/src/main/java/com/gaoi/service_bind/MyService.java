package com.gaoi.service_bind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    int count;
    boolean flag;

    public class myBind extends Binder{
        public int getCount(){
            return count;
        }

        public void setCount(int arg){
            count = arg;
        }
    }

    myBind mb = new myBind();

    @Override
    public IBinder onBind(Intent intent) {
        return mb;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        flag = true;
        new Thread(){
            public void run(){
                while (flag){
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
