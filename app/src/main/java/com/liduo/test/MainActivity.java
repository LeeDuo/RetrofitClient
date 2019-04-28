package com.liduo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liduo.retrofitclient.RetrofitClient;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitClient.getInstance();

    }
}
