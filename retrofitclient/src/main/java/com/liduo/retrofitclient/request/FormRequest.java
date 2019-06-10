package com.liduo.retrofitclient.request;

import com.liduo.retrofitclient.response.ResponseHandler;
import com.liduo.retrofitclient.response.ResponseCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by leed on 2018/6/1.
 * 表单请求
 * 可以上传表单参数，也可以添加上传文件
 */

public class FormRequest extends BaseRequest<FormRequest> {

    private Map<String, File> fileMap;

    public FormRequest() {
        super(BaseRequest.METHOD_POST);
        fileMap = new HashMap<>();
    }

    public FormRequest(String method) {
        super(method);
        fileMap = new HashMap<>();
    }

    @Override
    public void call(ResponseCallback callback) {
        //没有文件参数
        if (fileMap == null || fileMap.size() == 0) {
            FormBody.Builder builder = new FormBody.Builder();
            if (paramMap != null && paramMap.size() > 0) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    builder.add(entry.getKey(), entry.getValue());
                }
            }

            if (method.equals(BaseRequest.METHOD_POST)) {
                ResponseHandler.getInstance().post(apiName, headerMap, builder.build(), callback);
            }
            if (method.equals(BaseRequest.METHOD_PUT)) {
                ResponseHandler.getInstance().put(apiName, headerMap, builder.build(), callback);
            }

        } else {
            //包含文件参数
            MultipartBody.Builder builder = new MultipartBody.Builder();
            if (paramMap != null && paramMap.size() > 0) {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    builder.addFormDataPart(entry.getKey(), entry.getValue());
                }
            }
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), entry.getValue());
                builder.addFormDataPart(entry.getKey(), entry.getValue().getName(), fileBody);
            }
            if (method.equals(BaseRequest.METHOD_POST)) {
                ResponseHandler.getInstance().post(apiName, headerMap, builder.build(), callback);
            }
            if (method.equals(BaseRequest.METHOD_PUT)) {
                ResponseHandler.getInstance().put(apiName, headerMap, builder.build(), callback);
            }
        }
    }

    /**
     * add a file
     *
     * @param key
     * @param file
     */
    public FormRequest addFile(String key, File file) {
        fileMap.put(key, file);
        return this;
    }

    /**
     * add a file map
     *
     * @param fileMap
     */
    public FormRequest addFile(Map<String, File> fileMap) {
        this.fileMap.putAll(fileMap);
        return this;
    }
}
