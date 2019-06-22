package com.gaoi.exp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name_text, pwd_text;
    CheckBox me;
    String name, pwd;
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_text = findViewById(R.id.name);
        pwd_text = findViewById(R.id.pwd);
        me = findViewById(R.id.me);
        sharedPreference = getSharedPreferences("exp2", Context.MODE_PRIVATE);
        editor = sharedPreference.edit();

        name = name_text.getText().toString();
        pwd_text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        name_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (sharedPreference.contains(name)) {
                        pwd_text.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        pwd_text.setText(sharedPreference.getString(name, ""));
                    }
                }
            }
        });
    }

    public void login(View view) {
        name = name_text.getText().toString();
        pwd = pwd_text.getText().toString();

        if (me.isChecked()) {
            editor.putString(name, pwd);
            editor.commit();
        }
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

}