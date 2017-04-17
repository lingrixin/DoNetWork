package com.lingrixin.donetwork.business.okhttp;

import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by songy on 2017/4/3.
 */

public class OkhttpActivity extends BusinessBaseActivity {

    @Override
    protected void mPost() {
        String url = Constant.LOGIN;
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("action", "Submit")
                .add("mobile", "17801050463")
                .add("password", "123456")
                .build();
        final Request request = new Request.Builder().url(url).post(formBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String message = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvRequest.setText(request.urlString());
                            tvResponse.setText(message);
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void mGet() {
        String url = Constant.GET_ALL_URL;
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String message = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvRequest.setText(request.urlString());
                            tvResponse.setText(message);
                        }
                    });
                }
            }
        });
    }
}
