package com.lingrixin.donetwork.business.okhttp;

import com.lingrixin.donetwork.net.NF;
import com.lingrixin.donetwork.net.NetCall;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by LRXx on 2017-4-20.
 */

public class OkImp implements NF {
    @Override
    public void get(String mUrl, final NetCall nc) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(mUrl).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                nc.failed("失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    nc.success( response.body().string());
                }
            }
        });
    }

    @Override
    public void post(String mUrl, Map par, final NetCall nc) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = parser(new FormEncodingBuilder(),par);

        Request request = new Request.Builder().url(mUrl).post(formBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                nc.failed("失败");
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (response.isSuccessful()) {
                    nc.success( response.body().string());
                }
            }
        });
    }

    private RequestBody parser(FormEncodingBuilder b, Map map) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            b.add((String) e.getKey(), (String) e.getValue());
        }
        return b.build();
    }
}
