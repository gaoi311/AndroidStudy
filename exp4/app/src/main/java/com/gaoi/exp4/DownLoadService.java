package com.gaoi.exp4;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DownLoadService extends IntentService {
    OkHttpClient client;
    String sdPath;
    Notification.Builder notification;

    public DownLoadService() {
        super("DownLoadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if ("download".equals(action)) {
                initOkhttp();
                downLoad();
            }
        }
    }

    public void downLoad(){
        Request request = new Request.Builder()
                .url("http://192.168.43.46:9999/web_service_for_android/MyServlet?filename=hello.txt")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("show", "下载失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File("/sdcard/hello.txt"));
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                    //通知
                    createNotificationChannel("mychannel","channel","渠道描述",NotificationManager.IMPORTANCE_LOW);
                    notification = new Notification.Builder(getApplicationContext())
                            .setChannelId("mychannel")
                            .setContentTitle("通知")
                            .setContentText("下载完成")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setAutoCancel(true);
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.notify(3, notification.build());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createNotificationChannel(String channel_id, String channel_name,String channel_desc,int importance){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //配置通知渠道id,渠道名称（用户可以看到），渠道优先级
        NotificationChannel mChannel = new NotificationChannel(channel_id, channel_name,importance);
        //配置通知渠道的描述
        mChannel.setDescription(channel_desc);
        //配置通知出现时的闪灯（如果 android 设备支持的话）
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.RED);
        //配置通知出现时的震动（如果 android 设备支持的话）
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
        manager.createNotificationChannel(mChannel);
    }

    public void initOkhttp(){
        File sdcache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        client = builder.build();
    }

}
