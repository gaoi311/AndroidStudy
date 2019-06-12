package com.gaoi.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void locate(View view) {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean ok = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {//开了定位服务
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {// 没有权限，申请权限。
                    ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 100);
                    Toast.makeText(MainActivity.this, "请再次点击定位按钮", Toast.LENGTH_LONG).show();
                } else {
                    getLocation();//getLocation为定位方法
                }
            } else {
                getLocation();//getLocation为定位方法
            }
        } else {
            Toast.makeText(MainActivity.this, "请开启GPS服务再试", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    public void getLocation() {
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 2000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                textView.setText("");
                textView.append("经度是：" + location.getLongitude());
                textView.append("\n纬度是：" + location.getLatitude());
                textView.append("\n高度是：" + location.getAltitude());
                textView.append("\n方向是：" + location.getBearing());
                textView.append("\n速度是：" + location.getSpeed());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }
}
