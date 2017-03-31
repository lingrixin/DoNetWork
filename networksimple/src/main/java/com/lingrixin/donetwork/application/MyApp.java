package com.lingrixin.donetwork.application;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by LRXx on 2017-3-30.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
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
