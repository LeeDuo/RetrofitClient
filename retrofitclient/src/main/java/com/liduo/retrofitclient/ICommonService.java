package com.liduo.retrofitclient;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by leed on 2018/5/24.
 */

public interface ICommonService {

	@GET
	Observable<ResponseBody> get(@Url String apiName, @HeaderMap Map<String, String> headerMap, @QueryMap Map<String, Object> queryMap, @QueryMap Map<String, String> paramMap);

	@POST
	Observable<ResponseBody> post(@Url String apiName, @HeaderMap Map<String, String> headerMap, @Body RequestBody body);

	/**
	 * put请求没有body
	 * @param apiName
	 * @param headerMap
	 * @param paramMap
	 * @return
	 */
	@PUT
	Observable<ResponseBody> put(@Url String apiName, @HeaderMap Map<String, String> headerMap, @QueryMap Map<String, Object> paramMap);

	/**
	 * put请求包含body
	 * @param apiName
	 * @param headerMap
	 * @param body
	 * @return
	 */
	@PUT
	Observable<ResponseBody> put(@Url String apiName, @HeaderMap Map<String, String> headerMap, @Body RequestBody body);

}
