package com.gaoi.exp4;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button btnLoad;
    private TextView show;
    private boolean permission = false;
    private final String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        askPermission();
    }

    public void bind(){
        btnLoad =  findViewById(R.id.btnLoad);
        show = findViewById(R.id.show);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DownLoadService.class);
                intent.setAction("download");
                if(permission)
                    startService(intent);
            }
        });
    }

    public void askPermission(){

        int sdcard = ContextCompat.checkSelfPermission(this, permissions[0]);
        int internet = ContextCompat.checkSelfPermission(this, permissions[1]);
        Log.i("show", "askPermission: " + sdcard  + internet + PackageManager.PERMISSION_GRANTED);
        if (sdcard != PackageManager.PERMISSION_GRANTED || internet != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 321);
        }else{
            permission = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 321){
            if(grantResults.length > 0){
                permission = true;
            }
        }else{
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(this)
                        .setMessage("是否允许应用使用网络和SD卡?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, permissions, 321);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create()
                        .show();
            }
        }
    }

}
