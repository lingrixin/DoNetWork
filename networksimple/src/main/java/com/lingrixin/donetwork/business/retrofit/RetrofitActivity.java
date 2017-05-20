package com.lingrixin.donetwork.business.retrofit;

import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;
import com.lingrixin.donetwork.utils.L;
import com.lingrixin.donetwork.utils.TempUtil;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by songy on 2017/4/3.
 */

public class RetrofitActivity extends BusinessBaseActivity {

    @Override
    protected void mPost() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_BAIDU).
                addConverterFactory(ScalarsConverterFactory.create()).build();
        AllRequest request = retrofit.create(AllRequest.class);
//        Call<String> call = request.login("Submit", "17801050463", "123456");
        Map<String, String> map = TempUtil.getBaiduMap();
        Call<String> call = request.postBaidu(map.get("q"),
                map.get("from"), map.get("to"), map.get("appid"), map.get("salt"), map.get("sign"));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, final Response<String> response) {
                setResult(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                L.i(TAG, t.getMessage());
            }
        });

    }

    @Override
    protected void mGet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_BAIDU)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        AllRequest request = retrofit.create(AllRequest.class);
        Map<String, String> map = TempUtil.getBaiduMap();
        Call<String> call = request.getBaidu(map.get("q"),
                map.get("from"), map.get("to"), map.get("appid"), map.get("salt"), map.get("sign"));
//        Call<String> call = request.getCode("13552889718", "de3fa871faf4dd5fbe62be8e37dabb2f");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, final Response<String> response) {
                setResult(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.retrofit);
    }
}
