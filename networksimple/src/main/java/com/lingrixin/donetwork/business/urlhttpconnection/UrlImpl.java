package com.lingrixin.donetwork.business.urlhttpconnection;

import com.lingrixin.donetwork.net.LogNet;
import com.lingrixin.donetwork.net.NF;
import com.lingrixin.donetwork.net.NetCall;

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
import java.util.Iterator;
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
                    }
                    if (reader != null) {
                        reader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    connection.disconnect();
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
    public void post(final String mUrl, final Map par, final NetCall nc) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mUrl);
                    String p = (String) par.get("1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoOutput(true);
                    String data = parser(par);
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
                            if ("SUCCESS".equals(desc)) {
                                nc.success(result);
                            } else {
                                nc.failed(desc);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if (reader != null) {
                        reader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String parser(Map<String, Object> map) {
        StringBuilder b = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry element = (Map.Entry) it.next();
            b.append(element.getKey());
            b.append("=");
            b.append(element.getValue());
            b.append("&");
        }
        if (b.length() > 0) {
            b.deleteCharAt(b.length() - 1);
        }
        return b.toString();
    }
}
