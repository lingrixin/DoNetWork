package com.lingrixin.donetwork.business.volley;

import com.android.volley.VolleyError;
import com.lingrixin.donetwork.net.NF;
import com.lingrixin.donetwork.net.NetCall;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LRXx on 2017-4-20.
 */

public class VolleyImp implements NF {
    @Override
    public void get(String mUrl, final NetCall nc) {
        HashMap<String,String> params=new HashMap<>();
        params.put("phone","13552889718");
        params.put("key","de3fa871faf4dd5fbe62be8e37dabb2f");
        VolleyRequest.getInstance().volleyGet(mUrl, params, new ResultCallback() {
            @Override
            public void onSuccess(String response) {
                nc.success(response);
            }

            @Override
            public void onFailed(VolleyError error) {
                nc.failed(error.getMessage());
            }
        });
    }

    @Override
    public void post(String mUrl, Map par, final NetCall nc) {
        VolleyRequest.getInstance().volleyPost(mUrl, (HashMap<String, String>) par,
                new ResultCallback() {

                    @Override
                    public void onSuccess(String response) {
                        nc.success(response);
                    }

                    @Override
                    public void onFailed(VolleyError error) {
                        nc.failed(error.getStackTrace().toString());
                    }
                });
    }
}
