package com.gaoi.scrollview;

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
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent it = new Intent(MainActivity.this, MainActivity.class);
        final PendingIntent pit = PendingIntent.getActivity(MainActivity.this, 0, it, 0);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        notificationManager = (NotificationManager) MainActivity.this.getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("channelid1", "channelname", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "channelid1");
                builder.setSmallIcon(R.drawable.ad);
//                builder.setTicker("新消息");
                builder.setContentTitle("下载");
                builder.setContentText("正在下载");
//                builder.setDefaults(Notification.DEFAULT_LIGHTS);
                notificationManager.notify(1, builder.build());
                builder.setProgress(100, 0, false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            builder.setProgress(100, i, false);
                            notificationManager.notify(1, builder.build());
                            builder.setContentText("下载" + i + "%");
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        builder.setContentTitle("下载");
                        builder.setContentText("下载完成");
                        builder.setProgress(100, 100, false);
                        builder.setContentIntent(pit);
                        builder.setAutoCancel(true);
                        notificationManager.notify(1, builder.build());
                    }
                }).start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.cancel(1);
            }
        });
    }
}
