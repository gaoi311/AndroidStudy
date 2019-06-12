package com.gaoi.activitis_res;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button button2;
    EditText editText2;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button2 = findViewById(R.id.button3);
        editText2 = findViewById(R.id.editText);
        textView2 = findViewById(R.id.textView);

        final Intent intent = getIntent();
        textView2.setText(intent.getStringExtra("first"));

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("second", editText2.getText().toString());
                setResult(2, intent1);
                finish();
            }
        });
    }
}
