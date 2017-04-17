package com.lingrixin.donetwork.business.retrofit;

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
}
