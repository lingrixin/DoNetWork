package com.lingrixin.donetwork.business.volley;

import com.android.volley.VolleyError;
import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;
import com.lingrixin.donetwork.utils.TempUtil;

import java.util.HashMap;

/**
 * Created by songy on 2017/4/3.
 */

public class VolleyActivity extends BusinessBaseActivity {

    @Override
    protected void mPost() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("action", "Submit");
        hashMap.put("mobile", "17801050463");
        hashMap.put("password", "123456");
        VolleyRequest.getInstance().volleyPost(Constant.GET_URL, hashMap,
                new ResultCallback() {

                    @Override
                    public void onSuccess(String response) {
                        setResult(response);
                    }

                    @Override
                    public void onFailed(VolleyError error) {
                        setResult(error.getMessage());
                    }
                });
    }

    @Override
    protected void mGet() {
        HashMap<String,String> params=new HashMap<>();
        params.put("phone","13552889718");
        params.put("key","de3fa871faf4dd5fbe62be8e37dabb2f");
        VolleyRequest.getInstance().volleyGet(Constant.GET_URL, params, new ResultCallback() {
            @Override
            public void onSuccess(String response) {
                setResult(response);
            }

            @Override
            public void onFailed(VolleyError error) {
                setResult(error.getMessage());
            }
        });
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.volley);
    }
}
