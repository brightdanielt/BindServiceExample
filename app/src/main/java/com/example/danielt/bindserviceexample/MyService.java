package com.example.danielt.bindserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by danielt on 2018/3/8.
 */

public class MyService extends Service {

    MyService(){}

    MyBinder binder = new MyBinder();

    //符合規範的 Activity 成功啟動該 Service 時，透過該方法取得 MyBinder
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    //MyBinder 提供取得 MyService 的方法
    class MyBinder extends Binder {
        public Service getService() {
            return MyService.this;
        }

    }

    //取得 MyService 的 Activity，能透過取得的 MyService 直接呼叫該方法
    public void toast(){
        Toast.makeText(MyService.this, "Method called from MyService", Toast.LENGTH_SHORT).show();
    }
}
