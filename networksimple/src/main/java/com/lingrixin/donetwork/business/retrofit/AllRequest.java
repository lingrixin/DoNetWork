package com.lingrixin.donetwork.business.retrofit;

import com.lingrixin.donetwork.utils.Constant;
import com.lingrixin.donetwork.utils.TempUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/4/12.
 */

public interface AllRequest {

    @POST("Users/Login")
    Call<String> login(@Query("action") String action, @Query("mobile") String mobile, @Query("password") String password);

    @GET("/mobile/get")
    Call<String> getCode(@Query("phone") String phone, @Query("key") String key);

    @GET("api/trans/vip/translate")
    Call<String> getBaidu(
            @Query("q") String q,
            @Query("from") String from,
            @Query("to") String to,
            @Query("appid") String appid,
            @Query("salt") String salt,
            @Query("sign") String sign
    );

    @POST("api/trans/vip/translate")
    Call<String> postBaidu(
            @Query("q") String q,
            @Query("from") String from,
            @Query("to") String to,
            @Query("appid") String appid,
            @Query("salt") String salt,
            @Query("sign") String sign
    );
}
