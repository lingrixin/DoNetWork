package com.lingrixin.donetwork.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lingrixin.donetwork.utils.L;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by LRXx on 2017-3-30.
 * 集权性管理 统一流程
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = L.makeLogTag(getClass());

    protected abstract
    @LayoutRes
    int getLayout();

    protected abstract void setup();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        L.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        setup();
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        L.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        L.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.i(TAG, "onDestroy");
    }

    protected void startActivity(Class<? extends BaseActivity> cls) {
        startActivity(new Intent(BaseActivity.this, cls));
    }

}
