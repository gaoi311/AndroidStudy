package com.gaoi.activitis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        textView = findViewById(R.id.textView2);
        String username = intent.getStringExtra("username");
        String passwd = intent.getStringExtra("passwd");
        textView.setText(username + "  " + passwd);
        System.out.println(username + passwd);
    }
}
