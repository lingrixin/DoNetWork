package com.lingrixin.donetwork.business.httpcliect;

import com.lingrixin.donetwork.net.NF;
import com.lingrixin.donetwork.net.NetCall;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by LRXx on 2017-4-20.
 */

public class ClientImp implements NF {
    @Override
    public void get(final String mUrl, final NetCall nc) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpGet httpget= new HttpGet(mUrl);
                try {
                    HttpResponse httpResponse =  new DefaultHttpClient().execute(httpget);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        final String result = EntityUtils.toString(entity, "utf-8");
                        try {
                            JSONObject j = new JSONObject(result);
                            String code = j.getString("resultcode");
                            String msg = j.getString("reason");
                            if ("200".equals(code)) {
                                nc.success(result);
                            } else {
                                nc.failed(msg);
                            }
                        } catch (JSONException e) {
                            nc.failed("解析错误");
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void post(final String mUrl, final Map par, final NetCall nc) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpPost post = new HttpPost(mUrl);//创建HttpPost对象

                try {
                    post.setEntity(new UrlEncodedFormEntity(parser(par), HTTP.UTF_8));
                    HttpResponse httpResponse = new DefaultHttpClient().execute(post);
                    final String result= EntityUtils.toString(httpResponse.getEntity());
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String desc = jsonObject.getString("desc");
                        if ("SUCCESS".equals(desc)) {
                            nc.success(result);
                        } else {
                            nc.failed(desc);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private List<NameValuePair> parser(Map map){
        List<NameValuePair> list = new ArrayList<>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> el = (Map.Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(el.getKey(),el.getValue()));
        }
        return list;
    }
}
