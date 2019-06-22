package com.gaoi.exp5;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    File sound;
    MediaRecorder mediaRecord;
    MediaPlayer mediaPlayer;
    final String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askPermission();
    }

    public void start_record(View view) {
        Toast.makeText(MainActivity.this, "开始录音", Toast.LENGTH_SHORT).show();
        try {
            sound = new File(Environment.getExternalStorageDirectory().getCanonicalFile() + "/sound.mp3");
            mediaRecord = new MediaRecorder();
            mediaRecord.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecord.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecord.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecord.setOutputFile(sound.getAbsolutePath());
            mediaRecord.prepare();
            mediaRecord.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop_record(View view) {
        Toast.makeText(MainActivity.this, "停止录音", Toast.LENGTH_SHORT).show();
        if (sound != null && sound.exists()) {
            mediaRecord.stop();
            mediaRecord.release();
            mediaRecord = null;
        }
    }

    public void start_play(View view) {
        Toast.makeText(MainActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("/sdcard/sound.mp3");
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause_play(View view) {
        Toast.makeText(MainActivity.this, "暂停播放", Toast.LENGTH_SHORT).show();
        mediaPlayer.pause();
    }

    public void stop_play(View view) {
        Toast.makeText(MainActivity.this, "停止播放", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setMessage("是否允许应用使用麦克风?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        }
    }

    public void askPermission() {
        int i = ContextCompat.checkSelfPermission(this, permissions[1]);
        Log.i("show", "askPermission: " + i + PackageManager.PERMISSION_GRANTED);
        if (i != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }
}