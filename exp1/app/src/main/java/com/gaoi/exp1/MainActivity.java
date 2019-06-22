package com.gaoi.exp1;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    EditText name_text, id_text;
    RadioGroup sex_radiogroup;
    Spinner class_spi;
    DatePicker date_picker;
    Button save_btn;
    String name, id, sex, class1, date;
    boolean correct = true;
    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_text = findViewById(R.id.name);
        id_text = findViewById(R.id.id);
        sex_radiogroup = findViewById(R.id.sex_radiogroup);
        class_spi = findViewById(R.id.class1);
        date_picker = findViewById(R.id.date);
        save_btn = findViewById(R.id.save);
    }

    public void save(View view) {
        name = name_text.getText().toString();
        id = id_text.getText().toString();
        for (int i = 0; i < sex_radiogroup.getChildCount(); i++) {
            RadioButton btn = (RadioButton) sex_radiogroup.getChildAt(i);
            if (btn.isChecked()) {
                sex = btn.getText().toString();
                break;
            }
        }
        class1 = class_spi.getSelectedItem().toString();
        date = date_picker.getYear() + "年" + (date_picker.getMonth() + 1) + "月" + date_picker.getDayOfMonth() + "日";

        check();
        if (correct) {
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("id", id);
            bundle.putString("sex", sex);
            bundle.putString("class1", class1);
            bundle.putString("date", date);
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtras(bundle);
            Log.i("show", "onClick: click");
            startActivity(intent);
        } else {
            alert(msg);
        }
    }

    private void check() {
        if (name.equals("")) {
            msg = "姓名不能为空！";
            correct = false;
            return;
        }

        if (id_text.equals("")) {
            msg = "学号不能为空！";
            correct = false;
            return;
        }

        if (id.length() <= 10) {
            for (int i = 0; i < id.length(); i++) {
                if (id.charAt(i) >= 48 && id.charAt(i) <= 57) return;
                else {
                    msg = "学号必须为数字！";
                    correct = false;
                    return;
                }
            }
        } else {
            msg = "学号长度不能大于10！";
            correct = false;
            return;
        }
    }

    private void alert(String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setIcon(R.drawable.ic_launcher_foreground);
        alert.setTitle("提示");
        alert.setMessage(msg);
        alert.show();
    }
}
