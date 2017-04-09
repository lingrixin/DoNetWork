package com.lingrixin.donetwork.business.httpcliect;

import android.widget.TextView;

import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.base.BusinessBaseActivity;

import org.apache.http.client.HttpClient;

import butterknife.BindView;

/**
 * Created by songy on 2017/4/3.
 */

public class HttpCliectActivity extends BusinessBaseActivity {


    @Override
    protected void mPost() {
        tvRequest.setText("这是请求参数");
        tvResponse.setText("这是响应参数");
    }

    @Override
    protected void mGet() {

    }
}
