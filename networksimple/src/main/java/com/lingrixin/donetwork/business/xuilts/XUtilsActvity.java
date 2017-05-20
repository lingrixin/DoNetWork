package com.lingrixin.donetwork.business.xuilts;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;
import com.lingrixin.donetwork.utils.TempUtil;

import java.util.HashMap;

/**
 * Created by LRXx on 2017-4-21.
 */

public class XUtilsActvity extends BusinessBaseActivity {
    @Override
    protected void mPost() {
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, Constant.LOGIN, TempUtil.parserParams(TempUtil.getMap()), new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                setResult(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                setResult("请求失败");
            }
        });
    }

    @Override
    protected void mGet() {
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, Constant.GET_ALL_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        setResult(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        setResult("请求失败");
                    }
                });
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.xutils);
    }
}
