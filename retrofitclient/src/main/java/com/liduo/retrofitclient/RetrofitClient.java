package com.liduo.retrofitclient;

import com.google.gson.GsonBuilder;
import com.liduo.retrofitclient.interceptor.MyInterceptor;
import com.liduo.retrofitclient.request.BaseRequest;
import com.liduo.retrofitclient.request.FormRequest;
import com.liduo.retrofitclient.request.GetRequest;
import com.liduo.retrofitclient.request.JsonRequest;
import com.liduo.retrofitclient.request.PutRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leed on 2017/5/15.
 * 网络请求工具类，只需要在Application中调用init初始化，即可使用
 */

public class RetrofitClient {

    private static RetrofitClient mInstance;
    private static Retrofit mRetrofit;
    public final static String TAG = "RetrofitClient";
    public static boolean DEBUG = true;


    private RetrofitClient() {

    }

    /**
     * 初始化，实例化 RetrofitClient
     *
     * @param baseUrl 服务器根路径
     */
    public static void init(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS);
        init(baseUrl, builder);
    }

    /**
     * 初始化，实例化 RetrofitClient
     *
     * @param baseUrl           服务器根路径
     * @param httpClientBuilder 定制的OkHttpClient构造器
     */
    public static void init(String baseUrl, OkHttpClient.Builder httpClientBuilder) {

        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {

                    mRetrofit = new Retrofit.Builder().baseUrl(baseUrl) // 服务器根路径
                            .client(httpClientBuilder.addInterceptor(new MyInterceptor()).build()) // 添加okhttpclient
                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())) // JSON解析
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build(); // 集成RxJava
                    mInstance = new RetrofitClient();
                }
            }
        }

    }

    /**
     * 获取ApiService
     *
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> service) {
        if (mRetrofit == null) {
            throw new NullPointerException("RetrofitClient has not yet be initialized!");
        }
        return mRetrofit.create(service);
    }


    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("RetrofitClient has not yet be initialized!");
        }
        return mInstance;
    }


    public GetRequest get() {
        return new GetRequest();
    }

    public PutRequest put() {
        return new PutRequest();
    }

    public FormRequest postForm() {
        return new FormRequest();
    }

    public FormRequest putForm() {
        return new FormRequest(BaseRequest.METHOD_PUT);
    }

    public JsonRequest postJson() {
        return new JsonRequest();
    }

    public JsonRequest putJson() {
        return new JsonRequest(BaseRequest.METHOD_PUT);
    }


}
