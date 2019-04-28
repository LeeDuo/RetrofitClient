package com.liduo.retrofitclient.response;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.liduo.retrofitclient.ICommonService;
import com.liduo.retrofitclient.RetrofitClient;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Author: liduo
 * Date: 2019/4/25
 * Description:
 */
public class ResponseHandler {

    private final static String TAG = "ResponseHandler";
    private static ResponseHandler mInstance;
    private ICommonService mService;

    private ResponseHandler() {
        mService = RetrofitClient.getService(ICommonService.class);
    }

    public static ResponseHandler getInstance() {
        if (mInstance == null) {
            return mInstance = new ResponseHandler();
        }
        return mInstance;
    }

    /**
     * 处理响应结果
     *
     * @param observable
     * @param callback
     */
    private void handleResult(Observable observable, final ResponseCallback callback) {
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    final String string = responseBody.string();
                    if (callback != null) {
                        if (callback.mType == String.class) {
                            callback.onResponse(string);
                        } else {
                            Object o = JSON.parseObject(string, callback.mType);
                            callback.onResponse(o);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (com.google.gson.JsonParseException e) {
                    e.printStackTrace();
                    Log.e(TAG, "数据解析错误");
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (throwable != null) {
                    Exception e = (Exception) throwable;
                    String errMsg = "";
                    if (e instanceof ConnectException) {
                        errMsg = "无法连接服务器,请检查网络";
                    } else if (e instanceof UnknownHostException) {
                        errMsg = "无法解析服务器地址";
                    } else if (e instanceof SocketTimeoutException) {
                        errMsg = "连接服务器超时";
                    }
                    Log.e(TAG, errMsg);
                    if (callback != null) {
                        callback.onError(errMsg);
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * get
     *
     * @param apiName
     * @param queryMap
     * @param callback
     */
    public void get(String apiName, Map<String, String> headerMap, Map<String, Object> queryMap, Map<String, String> paramMap, ResponseCallback callback) {
        handleResult(mService.get(apiName, headerMap, queryMap, paramMap), callback);
    }


    /**
     * post
     *
     * @param apiName
     * @param headerMap
     * @param body
     * @param callback
     */
    public void post(String apiName, Map<String, String> headerMap, RequestBody body, ResponseCallback callback) {
        handleResult(mService.post(apiName, headerMap, body), callback);
    }

    /**
     * put
     *
     * @param apiName
     * @param headerMap
     * @param body
     * @param callback
     */
    public void put(String apiName, Map<String, String> headerMap, RequestBody body, ResponseCallback callback) {
        handleResult(mService.put(apiName, headerMap, body), callback);
    }

    /**
     * put
     *
     * @param apiName
     * @param headerMap
     * @param callback
     */
    public void put(String apiName, Map<String, String> headerMap, Map<String, Object> paramMap, ResponseCallback callback) {
        handleResult(mService.put(apiName, headerMap, paramMap), callback);
    }
}
