package com.lingrixin.donetwork.business.xuilts;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lingrixin.donetwork.net.NF;
import com.lingrixin.donetwork.net.NetCall;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by LRXx on 2017-4-21.
 */

public class XUtilsImp implements NF {
    @Override
    public void get(String mUrl, final NetCall nc) {
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, mUrl,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        nc.success(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        nc.failed(s);
                    }
                });
    }

    @Override
    public void post(String mUrl, Map par, final NetCall nc) {
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, mUrl, parser(par), new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                nc.success(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                nc.failed(s);
            }
        });
    }

    private RequestParams parser(Map map) {
        RequestParams params = new RequestParams();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,String> e = (Map.Entry<String,String>) it.next();
            params.addBodyParameter(e.getKey(), e.getValue());
        }
        return params;
    }
}
