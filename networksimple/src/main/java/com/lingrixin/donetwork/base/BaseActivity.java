package com.lingrixin.donetwork.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lingrixin.donetwork.utils.L;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by LRXx on 2017-3-30.
 * 集权性管理 统一流程
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = L.makeLogTag(getClass());

    protected abstract @LayoutRes int getLayout();
    protected abstract void setup();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
