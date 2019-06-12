package com.gaoi.advanced_ui_design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListView listView2;
    ArrayAdapter<String> adapter;
    SimpleAdapter simpleAdapter;
    String[] strings = {"我的设备", "双卡和网络", "WLAN", "蓝牙", "个人热点", "更多连接方式", "显示", "壁纸", "个性主题", "声音和震动", "安全", "通知和状态栏", "桌面与最近任务"};
    List<Map<String, Object>> list;
    int[] images = new int[]{R.drawable.ad, R.drawable.ae, R.drawable.af, R.drawable.ag, R.drawable.ai, R.drawable.al, R.drawable.am, R.drawable.an};
    String[] name = {"美国", "法国", "英国", "俄罗斯", "比利时", "巴拉圭", "乌拉圭", "意大利"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.item, strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "您选择的是：" + strings[position], Toast.LENGTH_LONG).show();
            }
        });



        listView2 = findViewById(R.id.listView2);
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < images.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("001", images[i]);
            map.put("002", name[i]);
            list.add(map);
        }
        simpleAdapter = new SimpleAdapter(MainActivity.this, list, R.layout.item2, new String[]{"001", "002"}, new int[]{R.id.imageView3, R.id.textView3});
        listView2.setAdapter(simpleAdapter);
    }
}
