package com.gaoi.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient mOkHttpClient;
    final String url = "http://192.168.43.46:9999/web_service_for_android/MyServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        OkHttpClient.Builder builder = null;
        builder = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
    }

    public void getAsynHttp(View view) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url);
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call mcall = mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.fillInStackTrace());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                System.out.println("network response = " + response.networkResponse());
                System.out.println("cache response = " + response.cacheResponse());
                String str = response.body().string();
                Log.i("------responsebody------", str);
                class MyRunnable implements Runnable {
                    String str;

                    public MyRunnable(String msg) {
                        str = msg;
                    }

                    public void run() {
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    }
                }
                runOnUiThread(new MyRunnable(str));
            }
        });
    }

    public void postAsynHttp(View view) {
        RequestBody formBody = new FormBody.Builder()
                .add("type", "2")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.fillInStackTrace());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("network response = " + response.networkResponse());
                System.out.println("cache response = " + response.cacheResponse());
                final String str = response.body().string();
                Log.i("------responsebody------", str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
