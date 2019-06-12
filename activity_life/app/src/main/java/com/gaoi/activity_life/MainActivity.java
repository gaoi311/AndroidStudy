package com.gaoi.activity_life;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final String TAG = "--->>---";
    Button finish, startActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "---------OnCreate---------");
        finish = findViewById(R.id.finish);
        startActivity = findViewById(R.id.start);

        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "---------OnStart---------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "---------OnRestart---------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "---------OnStop---------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "---------OnPause---------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "---------OnResume---------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "---------OnDestroy---------");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "---------OnSaveInstanceState---------");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "---------OnRestoreInstanceState---------");
    }
}
