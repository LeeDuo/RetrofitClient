package com.liduo.retrofitclient.request;

import com.liduo.retrofitclient.response.ResponseHandler;
import com.liduo.retrofitclient.response.ResponseCallback;

/**
 * Created by leed on 2018/6/24.
 */

public class GetRequest extends BaseRequest<GetRequest>{

	public GetRequest(){
		super(BaseRequest.METHOD_GET);
	}

	@Override
	public void call(ResponseCallback callback){
		try{
			ResponseHandler.getInstance().get(apiName, headerMap, queryMap, paramMap, callback);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
