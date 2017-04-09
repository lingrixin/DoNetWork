package com.lingrixin.donetwork.business.volley;

import com.android.volley.VolleyError;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;

import java.util.HashMap;

/**
 * Created by songy on 2017/4/3.
 */

public class VolleyActivity extends BusinessBaseActivity {

    @Override
    protected void mPost() {

    }

    @Override
    protected void mGet() {
        HashMap<String,String> params=new HashMap<>();
        params.put("phone","13552889718");
        params.put("key","de3fa871faf4dd5fbe62be8e37dabb2f");
        tvRequest.setText("参数：phone"+params.get("phone")+";key:"+params.get("key")+"\n"+"路径:"+Constant.VOLLEY_GET_URL);
        VolleyRequest.getInstance().volleyGet(Constant.VOLLEY_GET_URL, params, new ResultCallback() {
            @Override
            public void onSuccess(String response) {
                tvResponse.setText(response);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }
}
