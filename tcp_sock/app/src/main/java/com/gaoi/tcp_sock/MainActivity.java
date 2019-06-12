package com.gaoi.tcp_sock;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    String str;
    Handler handler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                textView.append(str + "\n");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);


    }

    public void send(View view) {
        new Thread() {
            public void run() {
                try {
                    Socket socket = new Socket("192.168.43.46", 38888);
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    PrintStream printStream = new PrintStream(outputStream);
                    printStream.print(editText.getText().toString());

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    str = socket.getRemoteSocketAddress().toString().substring(1) + ":" + socket.getPort() + ">>>" + bufferedReader.readLine();
                    handler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void clear(View view) {
        textView.setText("");
    }
}
