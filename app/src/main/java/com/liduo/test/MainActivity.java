package com.liduo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liduo.retrofitclient.RetrofitClient;
import com.liduo.retrofitclient.response.ResponseCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化RetrofitClient
        RetrofitClient.init("http://www.baidu.com");
        RetrofitClient.getInstance().get().setApiName("").call(new ResponseCallback<String>() {
            @Override
            public void onResponse(String response) {

            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }
}
