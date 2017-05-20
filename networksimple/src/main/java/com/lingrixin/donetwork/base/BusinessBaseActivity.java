package com.lingrixin.donetwork.base;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lingrixin.donetwork.R;
import com.lingrixin.donetwork.utils.Constant;
import com.lingrixin.donetwork.utils.TempUtil;
import com.lingrixin.donetwork.utils.ViewHelper;

import butterknife.BindView;

/**
 * Created by songy on 2017/4/3.
 */

public abstract class BusinessBaseActivity extends BaseActivity {
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cv_post)
    CardView cvPost;
    @BindView(R.id.cv_get)
    CardView cvGet;
    @BindView(R.id.tv_request)
    protected TextView tvRequest;
    @BindView(R.id.tv_response)
    protected TextView tvResponse;

    @Override
    protected int getLayout() {
        return R.layout.activity_business;
    }

    protected abstract void mPost();

    protected abstract void mGet();

    protected abstract String setTitle();

    @Override
    protected void setup() {
        initClick();
        tvTitle.setText(setTitle());
    }

    /**
     * 初始化点击事件
     */
    private void initClick() {
        ViewHelper.c(l, flBack, cvPost, cvGet);
    }

    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fl_back:
                    finish();
                    break;
                case R.id.cv_post:
                    tvRequest.setText(Constant.BAIDU_HTTP);
                    mPost();
                    break;
                case R.id.cv_get:
                    tvRequest.setText(Constant.BAIDU_HTTP_ALL);
                    mGet();
                    break;
                default:
                    break;
            }
        }
    };

    protected void setResult(final String result) {
        tvResponse.post(new Runnable() {
            @Override
            public void run() {
                tvResponse.setText(result);
            }
        });
    }

}
