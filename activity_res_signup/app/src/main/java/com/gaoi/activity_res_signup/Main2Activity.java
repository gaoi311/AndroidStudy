package com.gaoi.activity_res_signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    GridView gridView;
    String[] strs = {"美国", "德国", "英国", "法国", "巴拉圭", "乌克兰", "乌拉圭", "墨西哥", "意大利"};
    int[] images = {R.drawable.ad, R.drawable.ae, R.drawable.af, R.drawable.ag, R.drawable.ai, R.drawable.al, R.drawable.am, R.drawable.an, R.drawable.ao};
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < strs.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("image", images[i]);
            map.put("str", strs[i]);
            list.add(map);
        }
        simpleAdapter = new SimpleAdapter(Main2Activity.this, list, R.layout.item, new String[]{"image", "str"}, new int[]{R.id.imageView2, R.id.textView});
        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("image", images[position]);
                System.out.println(images[position] + "***" + position);
                setResult(2, intent);    //key code
                finish();
            }
        });
    }
}
