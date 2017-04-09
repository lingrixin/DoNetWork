package com.lingrixin.donetwork.business.urlhttpconnection;

import android.support.annotation.UiThread;
import android.util.Log;

import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;
import com.lingrixin.donetwork.utils.L;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by songy on 2017/4/3.
 */

public class UrlHttpConnectionActivity extends BusinessBaseActivity {

    @Override
    protected void mPost() {

    }

    @Override
    protected void mGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(Constant.VOLLEY_GET_URL);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Charset","UTF-8");
//                    connection.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
//                    connection.setRequestProperty("Cookie","AppName="+ URLEncoder.encode("你好","UTF-8"));
//                    connection.setR
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    connection.setRequestProperty("phone","13552889718");
                    connection.setRequestProperty("key","de3fa871faf4dd5fbe62be8e37dabb2f");

                    HashMap map = (HashMap) connection.getHeaderFields();
                    Set<String> set = map.keySet();
                    Iterator it=set.iterator();
                    while (it.hasNext()){
                        String key= (String) it.next();
                        String value= (String) map.get(key);
                        L.i(TAG,"key:"+key+"value:"+value);
                    }

                    if(connection.getResponseCode() == 200){
                        L.i(TAG,"Response:"+connection.getResponseMessage());
                        InputStream is =connection.getInputStream();
                        final ByteArrayOutputStream os = new ByteArrayOutputStream();
                        int len = 0;
                        byte buffer[]=new byte[1024];
                        while ((len=is.read(buffer)) !=-1) {
                            os.write(buffer, 0, len);
                        }
                        is.close();
                        os.close();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResponse.setText(new String(os.toByteArray()));
                            }
                        });
                    }else {
                        L.i(TAG,"else");
                    }
                } catch (Exception e) {
                    L.i(TAG,"ERROR"+e.getMessage());
                } finally {


                }
            }
        }).start();
    }
}
