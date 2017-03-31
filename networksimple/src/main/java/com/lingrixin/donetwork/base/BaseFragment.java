package com.lingrixin.donetwork.base;

import android.support.v4.app.Fragment;

import com.lingrixin.donetwork.utils.L;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by LRXx on 2017-3-30.
 * 集权性管理 统一流程
 */

public class BaseFragment extends Fragment {

    protected final String TAG = L.makeLogTag(getClass());

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }
}
