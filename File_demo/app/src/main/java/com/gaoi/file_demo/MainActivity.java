package com.gaoi.file_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    String fileNameString = "gaoi.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
    }

    public void save(View view) throws FileNotFoundException {
        FileOutputStream fileOutputStream = openFileOutput(fileNameString, MODE_APPEND);
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(editText.getText().toString());
    }

    public void read(View view) throws IOException {
        File file = new File(this.getFilesDir() + "/" + fileNameString);
        if (!file.exists()){
            Toast.makeText(MainActivity.this, "该应用暂时没有数据文件", Toast.LENGTH_SHORT).show();
            return;
        }
        FileInputStream fileInputStream = openFileInput(fileNameString);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String tempstring;
        textView.setText("文件路径为" + MainActivity.this.getFilesDir() + "/" + fileNameString);
        while ((tempstring = bufferedReader.readLine()) != null){
            textView.append("\n" + tempstring);
        }
    }

    public void delete(View view){
        File file = new File(this.getFilesDir() + "/" + fileNameString);
        if (!file.exists()){
            Toast.makeText(MainActivity.this, "该应用暂时没有数据文件", Toast.LENGTH_SHORT).show();
            return;
        }
        deleteFile(fileNameString);
        Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        textView.setText("");
    }
}
