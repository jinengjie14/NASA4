package com.nasa4.note.utils;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
	public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
	
	public static Response post(String url, FormBody.Builder formBody) throws Exception {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()//创建Request 对象。
                .url(url)
                .post(formBody.build())//传递请求体
                .build();
		Response response = httpClient.newCall(request).execute();
        return response;
   }
}
