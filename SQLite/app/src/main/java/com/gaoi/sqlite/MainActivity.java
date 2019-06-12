package com.gaoi.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ID, name, age, findID;
    TextView findresult;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = findViewById(R.id.editText);
        name = findViewById(R.id.editText2);
        age = findViewById(R.id.editText3);
        findID = findViewById(R.id.editText4);
        findresult = findViewById(R.id.textView5);

        MyDataBae myDataBae = new MyDataBae(MainActivity.this);
        database = myDataBae.getWritableDatabase();
    }

    public void find(View view){
        findresult.setText("");
        Cursor cursor;
        if (findID.getText().toString().isEmpty()) {
            cursor = database.query("user", null, null, null, null, null, null);
        } else {
            String sql = "select * from user where id=?";
            cursor = database.rawQuery(sql, new String[]{findID.getText().toString()});
        }
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            findresult.append("编号：" + id + " 姓名：" + name + " 年龄：" + age + "\n");
        }
        ID.setText("");
        name.setText("");
        age.setText("");
        findID.setText("");
    }

    public void insert(View view){
        String sql = "insert into user(id,name,age) values (?,?,?)";
        database.execSQL(sql, new Object[]{Integer.parseInt(ID.getText().toString()), name.getText().toString(), Integer.parseInt(age.getText().toString())});
        ID.setText("");
        name.setText("");
        age.setText("");
        findID.setText("");
        Toast.makeText(MainActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
    }

    public void delete(View view){
        String sql = "delete from user where id=?";
        database.execSQL(sql, new Object[]{Integer.parseInt(findID.getText().toString())});
        ID.setText("");
        name.setText("");
        age.setText("");
        findID.setText("");
        Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
    }

    public void update(View view){
        String sql = "update user set name=?,age=? where id=?";
        database.execSQL(sql, new Object[]{name.getText().toString(), Integer.parseInt(age.getText().toString()), Integer.parseInt(ID.getText().toString())});
        ID.setText("");
        name.setText("");
        age.setText("");
        findID.setText("");
        Toast.makeText(MainActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
    }
}
