package com.liduo.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liduo.retrofitclient.RetrofitClient;
import com.liduo.retrofitclient.response.ResponseCallback;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitClient.init("http://www.baidu.com");

        RetrofitClient.getInstance().get()// get
                .setApiName("/testapi")// 接口路径，如果与baseUrl不同，这里可以传全路径比如 http://www.xxx.com/testapi
                .addHeader("headerKey", "headerValue") // 请求头
                .addQuery("queryKey1", 100)// get参数，值可以为基本数据类型，这里用object接收的
                .addQuery("queryKey2", "hello world")
                .call(new ResponseCallback<String>() { //发起请求并回调到主线程，可以传入泛型
                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(String errMsg) {

                    }
                });

        RetrofitClient.getInstance().postForm() //post表单
                .setApiName("testApi")
                .addHeader("headerKey", "headerValue")
                .addParam("param1", "param1Value") //表单参数
                .addFile("fileName", new File("xxxx/xxx/xx")) // 文件附件
                .call(new ResponseCallback<String>() {
                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(String errMsg) {

                    }
                });

        RetrofitClient.getInstance().postJson() //上传json格式
                .setApiName("testApi")
                .addHeader("headerKey", "headerValue")
                .setJson(new User()) //设置json数据，这里有三个重载方法，setJson(Object object),setJson(String jsonString),setJson(JSONObject jsonObject)
                .call(new ResponseCallback<String>() {
                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(String errMsg) {

                    }
                });
    }
}
