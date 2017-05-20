package com.lingrixin.donetwork.business.httpcliect;

import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;
import com.lingrixin.donetwork.utils.TempUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songy on 2017/4/3.
 */

public class HttpCliectActivity extends BusinessBaseActivity {

    @Override
    protected void mPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpPost post = new HttpPost(Constant.LOGIN);//创建HttpPost对象
                try {
                    post.setEntity(new UrlEncodedFormEntity(TempUtil.parserList(TempUtil.getMap()), HTTP.UTF_8));
                    HttpResponse httpResponse = new DefaultHttpClient().execute(post);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        final String result = EntityUtils.toString(entity, "utf-8");
                        setResult(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void mGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpGet httpget = new HttpGet(Constant.GET_ALL_URL);
                try {
                    HttpResponse httpResponse = new DefaultHttpClient().execute(httpget);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        final String result = EntityUtils.toString(entity, "utf-8");
                        setResult(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.http_client);
    }
}
