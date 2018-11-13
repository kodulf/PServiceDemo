package bjpkten.pservicedemo.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import bjpkten.pservicedemo.service.MyIntentService;
import bjpkten.pservicedemo.R;

import static bjpkten.pservicedemo.service.MyIntentService.DOWNLOAD_STATUS;

public class IntentServiceActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        Intent intent = new Intent(this,MyIntentService.class);
        startService(intent);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent!=null) {
                    int success = intent.getIntExtra(DOWNLOAD_STATUS, MyIntentService.FAIL);
                    if(success==MyIntentService.SUCCESS) {
                        Toast.makeText(getApplicationContext(), "下载成功了", Toast.LENGTH_LONG).show();
                    }else if(success ==MyIntentService.FAIL){
                        Toast.makeText(getApplicationContext(), "下载失败了", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "下载错误了", Toast.LENGTH_LONG).show();
                    }

                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntentService.ACTION_DOWNLOAD_BROADCAST);
        intentFilter.addCategory("android.intent.category.DEFAULT");
        registerReceiver(broadcastReceiver,intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver!=null){
            unregisterReceiver(broadcastReceiver);
        }
    }
}
