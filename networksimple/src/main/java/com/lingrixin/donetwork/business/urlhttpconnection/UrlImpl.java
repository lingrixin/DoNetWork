package com.lingrixin.donetwork.business.urlhttpconnection;

import com.lingrixin.donetwork.net.LogNet;
import com.lingrixin.donetwork.net.NF;
import com.lingrixin.donetwork.net.NetCall;
import com.lingrixin.donetwork.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * Created by LRXx on 2017-4-19.
 */

public class UrlImpl implements NF {
    @Override
    public void get(final String mUrl, final NetCall nc) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final URL url = new URL(mUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.connect();
                    InputStream inputStream = null;
                    BufferedReader reader = null;
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        final String result = reader.readLine();
                        LogNet.l(mUrl, "", result);
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
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                tvRequest.setText("host:"+url.getHost()+"\n"+"path:"+url.getPath()+"\n"+"request_path:"+url.toString());
//                                tvResponse.setText(result);
//                            }
//                        });
                    }
                    if (reader != null) {
                        reader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (connection != null) {
                        connection.disconnect();
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

    @Override
    public void post(final String mUrl, Map par, final NetCall nc) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoOutput(true);
                    String data = "action=Submit&mobile=17801050463&password=123456";
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(data.getBytes());
                    connection.connect();
                    InputStream inputStream = null;
                    BufferedReader reader = null;
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        final String result = reader.readLine();
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            String desc = jsonObject.getString("desc");
                            if("SUCCESS".equals(desc)){
                                nc.success(result);
                            }else {
                                nc.failed(desc);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
}
