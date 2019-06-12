package com.gaoi.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("<<------>>", "service 创建成功");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int i = 100;
        while (i > 0){
            Log.i("<<------>>", i + "");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("<<------>>", "service 停止成功");
    }
}
