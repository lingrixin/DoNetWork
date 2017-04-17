package com.lingrixin.donetwork.business.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lingrixin.donetwork.application.MyApp;

import org.apache.http.protocol.HTTP;

import java.net.URLEncoder;
import java.util.HashMap;

public class VolleyRequest {

    private static VolleyRequest mInstance;
    private RequestQueue mRequestQueue;

    private VolleyRequest() {
        init(MyApp.context);
    }

    public static VolleyRequest getInstance() {
        if (mInstance == null) {
            synchronized (VolleyRequest.class) {
                if (mInstance == null) {
                    mInstance = new VolleyRequest();
                }
            }
        }
        return mInstance;
    }

    private synchronized void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void volleyPost(String url, final HashMap<String, String> hashMap,
            final ResultCallback resultCallback) {
        volleyPost(url, hashMap, resultCallback,false, null);
    }

    public void volleyPost(String url, final HashMap<String, String> hashMap,
                           final ResultCallback resultCallback, boolean isCache, String tag) {

		volleyRequsest(Method.POST, url, hashMap, resultCallback,isCache, tag);
    }

    public void volleyGet(String url, final HashMap<String, String> hashMap,
            final ResultCallback resultCallback) {
        volleyGet(url, hashMap, resultCallback, null);
    }

    @SuppressWarnings("deprecation")
	public void volleyGet(String url, final HashMap<String, String> hashMap,
                          final ResultCallback resultCallback, String tag) {

        StringBuilder sb = new StringBuilder(url);
        if (hashMap != null) {
            sb.append("?");
            for (String key : hashMap.keySet()) {
                sb.append(key);
                sb.append("=");
                sb.append(URLEncoder.encode(hashMap.get(key)));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1);
        }
        volleyRequsest(Method.GET, sb.toString(), null, resultCallback, true,
                tag);
    }

    private void volleyRequsest(int method, String url,
                                final HashMap<String, String> hashMap,
                                final ResultCallback resultCallback, boolean isCache, String tag) {
        StringRequest mStringRequest = new StringRequest(method, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        resultCallback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultCallback.onFailed(error);
                    }
                }) {
            
            protected final String TYPE_UTF8_CHARSET = "charset=UTF-8";  
            
            // 重写parseNetworkResponse方法改变返回头参数解决乱码问题  
            // 主要是看服务器编码，如果服务器编码不是UTF-8的话那么就需要自己转换，反之则不需要  
            @Override  
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                try {  
                    String type = response.headers.get(HTTP.CONTENT_TYPE);
                    if (type == null) {  
                        type = TYPE_UTF8_CHARSET;  
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    } else if (!type.contains("UTF-8")) {  
                        type += ";" + TYPE_UTF8_CHARSET;  
                        response.headers.put(HTTP.CONTENT_TYPE, type);
                    }  
                } catch (Exception e) {  
                }  
                return super.parseNetworkResponse(response);  
            }  

            // 携带参数
            @Override
            protected HashMap<String, String> getParams()
                    throws AuthFailureError {
//                HashMap<String , String> map = new HashMap<String, String>();
//                for (String key : hashMap.keySet()) {
//                   map.put(key,URLEncoder.encode(hashMap.get(key)));
//                }
                return hashMap;
            }
            
         // Volley请求类提供了一个 getHeaders（）的方法，重载这个方法可以自定义HTTP 的头信息。（也可不实现）
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "charset=UTF-8");
//                return headers;
//            }

        };
        if (!TextUtils.isEmpty(tag)) {
            mStringRequest.setTag(tag);
        }
        mStringRequest.setShouldCache(isCache);
        mRequestQueue.add(mStringRequest);

    }

}
