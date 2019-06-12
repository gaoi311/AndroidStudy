package com.gaoi.bcr1;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ad);
        builder.setTicker("广播消息");
        builder.setContentTitle("您有一条广播消息");
        builder.setContentText("这里是广播消息演示，请忽略");
        notificationManager.notify(1, builder.build());
    }
}
