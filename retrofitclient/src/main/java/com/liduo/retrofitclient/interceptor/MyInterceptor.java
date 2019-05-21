package com.liduo.retrofitclient.interceptor;

import android.util.Log;

import com.liduo.retrofitclient.BuildConfig;
import com.liduo.retrofitclient.RetrofitClient;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by leed on 2018/4/9.
 */

public class MyInterceptor implements Interceptor {

	private static final Charset UTF8 = Charset.forName("UTF-8");
	private final String TAG = RetrofitClient.TAG;

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		long start = System.currentTimeMillis();
		if (RetrofitClient.DEBUG) {
			Log.d(TAG, "----------Start----------------");
			Log.d(TAG, "| " + request.method() + " --> " + request.url());
			Log.d(TAG, "| headers:\n{\n" + request.headers().toString() + "}");
		}

		RequestBody requestBody = request.body();
		if(requestBody != null){
			Buffer buffer = new Buffer();
			requestBody.writeTo(buffer);
			Charset charset = UTF8;
			MediaType contentType = requestBody.contentType();
			if(contentType != null){
				charset = contentType.charset(UTF8);
			}

			if (RetrofitClient.DEBUG) {
				Log.d(TAG, "| requestBody:" + buffer.readString(charset));
			}

		}

		Response response = chain.proceed(chain.request());
		ResponseBody body = response.body();
		MediaType contentType = body.contentType();
		String bodyString = body.string();
		long end = System.currentTimeMillis();
		long time = end - start;
		if (RetrofitClient.DEBUG) {
			Log.d(TAG, "| response:" + bodyString);
			Log.d(TAG, "----------End:" + time + " ms----------");
		}

		return response.newBuilder().body(ResponseBody.create(contentType, bodyString)).build();
	}

}
