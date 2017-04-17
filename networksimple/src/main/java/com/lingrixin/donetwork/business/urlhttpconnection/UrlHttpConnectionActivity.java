package com.lingrixin.donetwork.business.urlhttpconnection;

import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by songy on 2017/4/3.
 */

public class UrlHttpConnectionActivity extends BusinessBaseActivity {

    @Override
    protected void mPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(Constant.LOGIN);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoOutput(true);
                    String data="action=Submit&mobile=17801050463&password=123456";
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(data.getBytes());
                    connection.connect();
                    InputStream inputStream=null;
                    BufferedReader reader=null;
                    if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                        inputStream=connection.getInputStream();
                        reader=new BufferedReader(new InputStreamReader(inputStream));
                        final String result=reader.readLine();
                        //子线程不能更新UI线程的内容，要更新需要开启一个Ui线程，这里Toast要在Ui线程
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               tvResponse.setText(result);
                            }
                        });
                    }
                    reader.close();
                    inputStream.close();
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
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
                try {
                    final URL url = new URL(Constant.GET_ALL_URL);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.connect();
                    InputStream inputStream=null;
                    BufferedReader reader=null;
                    if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                        inputStream=connection.getInputStream();
                        reader=new BufferedReader(new InputStreamReader(inputStream));
                        final String result=reader.readLine();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvRequest.setText("host:"+url.getHost()+"\n"+"path:"+url.getPath()+"\n"+"request_path:"+url.toString());
                                tvResponse.setText(result);
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
