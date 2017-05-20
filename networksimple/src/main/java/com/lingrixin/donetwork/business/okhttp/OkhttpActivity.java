package com.lingrixin.donetwork.business.okhttp;

import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;
import com.lingrixin.donetwork.utils.TempUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by songy on 2017/4/3.
 */

public class OkhttpActivity extends BusinessBaseActivity {

    @Override
    protected void mPost() {
        tvRequest.setText(Constant.BAIDU_HTTP);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = TempUtil.parserBody(new FormEncodingBuilder(), TempUtil.getBaiduMap());
        final Request request = new Request.Builder().url(Constant.BAIDU_HTTP).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                setResult("请求失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                setResult(response.body().string());
            }
        });
    }

    @Override
    protected void mGet() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(Constant.BAIDU_HTTP+"?"+TempUtil.parser(TempUtil.getBaiduMap())).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                setResult("请求失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    setResult(response.body().string());
                } else {
                    onFailure(null, null);
                }
            }
        });
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.okhttp);
    }
}
