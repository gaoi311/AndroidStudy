package com.gaoi.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        sharedPreferences = getSharedPreferences("first", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int count = sharedPreferences.getInt("count", 1);
        Toast.makeText(MainActivity.this, "APP使用次数是" + count, Toast.LENGTH_SHORT).show();
        editor.putInt("count", ++count);
        editor.commit();
    }

    public void save(View view){
        editor.putString("1", editText.getText().toString());
        editor.commit();
    }

    public void read(View view){
        String string = sharedPreferences.getString("1", "");
        textView.setText(string);
    }
}
