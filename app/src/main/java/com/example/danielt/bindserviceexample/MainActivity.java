package com.example.danielt.bindserviceexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //啟動 MyService
        Intent i = new Intent();
        i.setClass(MainActivity.this,MyService.class);
        bindService(i,this,BIND_AUTO_CREATE);
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        if (service != null) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            if (myBinder != null) {
                myService = (MyService) myBinder.getService();
                //能夠直接呼叫 MyService 的方法
                myService.toast();
            }
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        myService = null;
    }

    //既然 Service 是綁定的，當 Activity 停止時，Service 也應該一同移除
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(this);
        myService = null;
    }
}
