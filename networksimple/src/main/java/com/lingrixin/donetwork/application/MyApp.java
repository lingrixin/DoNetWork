package com.lingrixin.donetwork.application;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by LRXx on 2017-3-30.
 */

public class MyApp extends Application {
    public static Context context=null;



    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
//        if (BuildConfig.DEBUG) {
//            MobclickAgent.enableEncrypt(false);
//            MobclickAgent.setDebugMode(true);
//        } else {
//            MobclickAgent.enableEncrypt(true);
//            MobclickAgent.setDebugMode(false);
//        }
        MobclickAgent.enableEncrypt(true);
    }
}
