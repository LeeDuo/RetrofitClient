package com.liduo.retrofitclient.request;

import com.liduo.retrofitclient.response.ResponseHandler;
import com.liduo.retrofitclient.response.ResponseCallback;

/**
 * Created by leed on 2018/6/24.
 */

public class PutRequest extends BaseRequest<PutRequest>{

	public PutRequest(){
		super(BaseRequest.METHOD_PUT);
	}

	@Override
	public void call(ResponseCallback callback){
		try{
			ResponseHandler.getInstance().put(apiName, headerMap, queryMap, callback);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
