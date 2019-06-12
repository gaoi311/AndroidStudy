package com.gaoi.activitis;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button button3;
    Button button;
    ImageView imageView;
    EditText editText2;
    EditText editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("usernamae", editText2.getText().toString());
                intent.putExtra("passwd", editText3.getText().toString());
                System.out.println(editText2.getText());
                MainActivity.this.startActivity(intent);
            }
        });

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:10086"));
                if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.CALL_PHONE") == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.CALL_PHONE"}, 123);
                    startActivity(intent);
                }
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.ACTION_VIEW") == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.ACTION_VIEW"}, 123);
                    startActivity(intent);
                }
            }
        });
    }
}
