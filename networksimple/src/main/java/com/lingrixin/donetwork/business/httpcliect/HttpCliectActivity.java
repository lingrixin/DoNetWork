package com.lingrixin.donetwork.business.httpcliect;

import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;

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

import java.util.ArrayList;
import java.util.List;

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

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("action", "Submit"));
                params.add(new BasicNameValuePair("mobile", "17801050463"));
                params.add(new BasicNameValuePair("password", "123456"));
                try {
                    post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    HttpResponse httpResponse = new DefaultHttpClient().execute(post);
                    final String result= EntityUtils.toString(httpResponse.getEntity());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvRequest.setText(post.getURI().toString());
                            tvResponse.setText(result);
                        }
                    });
                }catch (Exception e) {
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
                final HttpGet httpget= new HttpGet(Constant.GET_ALL_URL);
                try {
                    HttpResponse httpResponse =  new DefaultHttpClient().execute(httpget);
                      if (httpResponse.getStatusLine().getStatusCode() == 200) {
                          HttpEntity entity = httpResponse.getEntity();
                          final String result = EntityUtils.toString(entity, "utf-8");
                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  tvRequest.setText(Constant.GET_ALL_URL);
                                  tvResponse.setText(result);
                              }
                          });

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
