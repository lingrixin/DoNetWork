package com.lingrixin.donetwork.business.urlhttpconnection;

import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;
import com.lingrixin.donetwork.utils.Constant;
import com.lingrixin.donetwork.utils.TempUtil;

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
import java.util.HashMap;
import java.util.Map;

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
                    URL url = new URL(Constant.LOGIN);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoOutput(true);
                    String data = TempUtil.parser(TempUtil.getMap());
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(data.getBytes());
                    connection.connect();
                    InputStream inputStream = null;
                    BufferedReader reader = null;
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        final String result = reader.readLine();
                        setResult(result);
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

    @Override
    protected void mGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final URL url = new URL(Constant.GET_ALL_URL);
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
                        setResult(result);
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
    protected String setTitle() {
        return getResources().getString(R.string.http_url_connection);
    }
}
