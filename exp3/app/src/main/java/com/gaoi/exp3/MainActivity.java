package com.gaoi.exp3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        List<News> news = new ArrayList<News>();
        MyDB openHelper = new MyDB(MainActivity.this, "news.db", null, 1);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from news", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String source = cursor.getString(cursor.getColumnIndex("source"));
            News item = new News(id, title, date, source);
            news.add(item);
        }

        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.item, Collections.singletonList(news.toString()));
        listView.setAdapter(adapter);
    }

    private List<News> getData() {
        List<News> news = new ArrayList<News>();
        MyDB openHelper = new MyDB(MainActivity.this, "news.db", null, 1);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from news", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String source = cursor.getString(cursor.getColumnIndex("source"));
            News item = new News(id, title, date, source);
            news.add(item);
        }
        return news;
    }
}