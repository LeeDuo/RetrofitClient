package com.liduo.retrofitclient.response;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by leed on 2018/5/25.
 * 请求回调
 */

public abstract class ResponseCallback<T>{

	public Type mType;

	public ResponseCallback(){
		mType = getSuperclassTypeParameter(getClass());
	}

	static Type getSuperclassTypeParameter(Class<?> subclass){
		Type superclass = subclass.getGenericSuperclass();
		if(superclass instanceof Class){
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameterized = (ParameterizedType)superclass;
		return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
	}

	public abstract void onResponse(T response);

	public abstract void onError(String errMsg);

}
