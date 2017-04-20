package com.lingrixin.donetwork.business.httpcliect;

import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.net.N;
import com.lingrixin.donetwork.net.NetCall;
import com.lingrixin.donetwork.utils.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songy on 2017/4/3.
 */

public class HttpCliectActivity extends BusinessBaseActivity {

    @Override
    protected void mPost() {
        N n = new N(new ClientImp());
        Map map = new HashMap();
        map.put("action", "Submit");
        map.put("mobile", "17801050463");
        map.put("password", "123456");

        n.mPost(Constant.LOGIN, map, new NetCall() {
            @Override
            public void success(final String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvRequest.setText(Constant.LOGIN);
                        tvResponse.setText(result);
                    }
                });
            }

            @Override
            public void failed(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvRequest.setText(Constant.LOGIN);
                        tvResponse.setText(msg);
                    }
                });
            }
        });
    }

    @Override
    protected void mGet() {
        N n = new N(new ClientImp());
        n.mGet(Constant.GET_ALL_URL, new NetCall() {
            @Override
            public void success(final String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvRequest.setText(Constant.GET_ALL_URL);
                        tvResponse.setText(result);
                    }
                });
            }

            @Override
            public void failed(final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvRequest.setText(Constant.GET_ALL_URL);
                        tvResponse.setText(msg);
                    }
                });
            }
        });
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.http_client);
    }
}
