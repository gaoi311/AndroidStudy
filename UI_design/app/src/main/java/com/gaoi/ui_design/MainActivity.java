package com.gaoi.ui_design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.time.chrono.MinguoChronology;

public class MainActivity extends AppCompatActivity {
    String string_rad = "";
    String string_che = "";

    EditText editText1, editText2;
    Button button1, button2;

    ToggleButton toggleButton;
    ImageView imageView;
    Switch switch1;

    Button button;
    RadioGroup radioGroup;
    CheckBox checkBox, checkBox2, checkBox3;

    ProgressBar progressBar;
    SeekBar seekBar;

    RatingBar ratingBar;
    Button button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您点击了【登录】" + "\n用户名：" + editText1.getText() + "\n密码:" + editText2.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您点击了【取消】", Toast.LENGTH_LONG).show();
                finish();
            }
        });


        toggleButton = findViewById(R.id.toggleButton);
        imageView = findViewById(R.id.imageView);
        switch1 = findViewById(R.id.switch1);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    imageView.setImageResource(R.drawable.p1);
                } else {
                    imageView.setImageResource(R.drawable.p3);
                }
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    imageView.setImageResource(R.drawable.p2);
                } else {
                    imageView.setImageResource(R.drawable.p1);
                }
            }
        });


        button = findViewById(R.id.button4);
        radioGroup = findViewById(R.id.radiogroup);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                string_che = "";
                string_rad = "";
                int checked = radioGroup.getCheckedRadioButtonId();

                RadioButton r = findViewById(checked);
                string_rad = r.getText().toString();
                CheckBox[] checkBoxes = {checkBox, checkBox2, checkBox3};
                for (int i = 0; i < checkBoxes.length; i++){
                    if (checkBoxes[i].isChecked()){
                        string_che += checkBoxes[i].getText();
                    }
                }
                Toast.makeText(MainActivity.this, "您选择的水果是：" + string_rad + "，城市是：" + string_che, Toast.LENGTH_LONG).show();
                return;
            }
        });



        progressBar = findViewById(R.id.progressBar3);
        seekBar = findViewById(R.id.seekBar);
        imageView.setAlpha(seekBar.getProgress());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                imageView.setAlpha(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        ratingBar = findViewById(R.id.ratingBar);
        button5 = findViewById(R.id.button5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您的评分是：" + ratingBar.getRating(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
