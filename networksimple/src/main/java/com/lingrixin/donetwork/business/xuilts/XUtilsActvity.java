package com.lingrixin.donetwork.business.xuilts;

import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.net.N;
import com.lingrixin.donetwork.net.NetCall;
import com.lingrixin.donetwork.utils.Constant;

import java.util.HashMap;

/**
 * Created by LRXx on 2017-4-21.
 */

public class XUtilsActvity extends BusinessBaseActivity {
    @Override
    protected void mPost() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("action", "Submit");
        hashMap.put("mobile", "17801050463");
        hashMap.put("password", "123456");
        N n = new N(new XUtilsImp());
        n.mPost(Constant.LOGIN, hashMap, new NetCall() {
            @Override
            public void success(String result) {
                tvRequest.setText(Constant.LOGIN);
                tvResponse.setText(result);
            }

            @Override
            public void failed(String msg) {
                tvResponse.setText(msg);
            }
        });
    }

    @Override
    protected void mGet() {
        N n = new N(new XUtilsImp());
        n.mGet(Constant.GET_ALL_URL, new NetCall() {
            @Override
            public void success(String result) {
                tvRequest.setText(Constant.GET_ALL_URL);
                tvResponse.setText(result);
            }

            @Override
            public void failed(String msg) {
                tvRequest.setText(Constant.GET_ALL_URL);
                tvResponse.setText(msg);
            }
        });
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.xutils);
    }
}
