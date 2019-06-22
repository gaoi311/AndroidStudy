package com.gaoi.exp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        textView.setText("姓名："+ bundle.getString("name") + "\n学号：" + bundle.getString("id") + "\n性别：" + bundle.getString("sex") + "\n班级：" + bundle.getString("class1") + "\n日期：" + bundle.getString("date"));
    }
}
