package com.liduo.retrofitclient.request;

import com.liduo.retrofitclient.response.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leed on 2018/5/28.
 * 封装的请求的基类
 */

public abstract class BaseRequest<T extends BaseRequest>{

	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";
	public static final String METHOD_PUT = "PUT";
	public static final String METHOD_DELETE = "DELETE";

	//请求头
	protected Map<String, String> headerMap;
	//tag
	protected Map<String, Object> tagMap;
	//参数
	protected Map<String, String> paramMap;
	//query位置参数
	protected Map<String, Object> queryMap;
	//请求 method
	protected String method;
	//业务API地址
	protected String apiName;

	public BaseRequest(){
		headerMap = new HashMap<>();
		tagMap = new HashMap<>();
		paramMap = new HashMap<>();
		queryMap = new HashMap<>();
	}

	public BaseRequest(String method){
		this();
		this.method = method;
	}

	/**
	 * add a request header
	 * @param key
	 * @param value
	 */
	public T addHeader(String key, String value){
		headerMap.put(key, value);
		return (T)this;
	}


	/**
	 * add a request header map
	 * @param headerMap
	 */
	public T addHeader(Map<String, String> headerMap){
		this.headerMap.putAll(headerMap);
		return (T)this;
	}

	/**
	 * add a param
	 * @param key
	 * @param value
	 */
	public T addParam(String key, String value){
		paramMap.put(key, value);
		return (T)this;
	}

	/**
	 * add a param map
	 * @param paramMap
	 */
	public T addParam(Map<String, String> paramMap){
		this.paramMap.putAll(paramMap);
		return (T)this;
	}

	/**
	 * add query param
	 * @param key
	 * @param value
	 * @return
	 */
	public T addQuery(String key, Object value){
		queryMap.put(key, value);
		return (T)this;
	}

	/**
	 * add a query map
	 * @param queryMap
	 * @return
	 */
	public T addQuery(Map<String, Object> queryMap){
		this.queryMap.putAll(queryMap);
		return (T)this;
	}

	/**
	 * add a tag
	 * @param key
	 * @param value
	 */
	public T addTag(String key, Object value){
		tagMap.put(key, value);
		return (T)this;
	}

	/**
	 * add a tag map
	 * @param tagMap
	 */
	public T addTag(Map<String, Object> tagMap){
		this.tagMap.putAll(tagMap);
		return (T)this;
	}

	public T setMethod(String method){
		this.method = method;
		return (T)this;
	}

	public String getMethod(){
		return this.method;
	}

	public Map<String, String> getHeaderMap(){
		return headerMap;
	}

	public Map<String, Object> getTagMap(){
		return tagMap;
	}

	public Map<String, String> getParamMap(){
		return paramMap;
	}

	public String getApiName(){
		return apiName;
	}

	public T setApiName(String apiName){
		this.apiName = apiName;
		return (T)this;
	}

	public abstract void call(ResponseCallback callback);
}
