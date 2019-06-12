package com.gaoi.notification_demo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;
    Button button;
    boolean down_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_7);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel("channelid1", "channelname", NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(notificationChannel);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent intent = PendingIntent.getActivity(MainActivity.this, 0, new Intent(MainActivity.this, MainActivity.class), 0);
                final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "channelid1");
                builder.setContentTitle("带有进度条的通知")
                        .setContentText("正在等待下载")
                        //.setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(intent)
                        .setProgress(100, 0, false)
                        .setAutoCancel(true)
                        .addAction(R.mipmap.ic_launcher, "查看", intent);
                notificationManager.notify(3, builder.build());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int x = 0;
                        while (x < 100) {
                            x++;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            builder.setProgress(100, x, false)
                                    .setContentText(String.format("已下载%d%%", x));
                            notificationManager.notify(3, builder.build());
                        }
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        builder.setProgress(0, 0, false)
                                .setContentText("下载完毕");
                        notificationManager.notify(3, builder.build());
                    }
                }).start();
            }
        });
    }
}