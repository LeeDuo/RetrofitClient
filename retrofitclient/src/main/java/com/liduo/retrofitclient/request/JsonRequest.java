package com.liduo.retrofitclient.request;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liduo.retrofitclient.response.ResponseHandler;
import com.liduo.retrofitclient.response.ResponseCallback;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by leed on 2018/5/28.
 * 请求body为json格式数据
 */

public class JsonRequest extends BaseRequest<JsonRequest> {

    private String jsonString;

    public JsonRequest() {
        super(BaseRequest.METHOD_POST);
    }

    public JsonRequest(String method) {
        super(method);
    }

    @Override
    public void call(ResponseCallback callback) {
        if (TextUtils.isEmpty(jsonString)) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonString);
            if (method.equals(BaseRequest.METHOD_POST)) {
                ResponseHandler.getInstance().post(apiName, headerMap, body, callback);
            }
            if (method.equals(BaseRequest.METHOD_PUT)) {
                ResponseHandler.getInstance().put(apiName, headerMap, body, callback);
            }
        }
    }

    /**
     * 设置json
     *
     * @param jsonObject alibaba fastjson JSONObject
     * @return
     */
    public JsonRequest setJson(JSONObject jsonObject) {
        this.jsonString = jsonObject.toJSONString();
        return this;
    }

    /**
     * 设置 json
     *
     * @param object
     * @return
     */
    public JsonRequest setJson(Object object) {
        this.jsonString = JSON.toJSONString(object);
        return this;
    }

    /**
     * 设置 json
     *
     * @param jsonString
     * @return
     */
    public JsonRequest setJson(String jsonString) {
        this.jsonString = jsonString;
        return this;
    }

}
