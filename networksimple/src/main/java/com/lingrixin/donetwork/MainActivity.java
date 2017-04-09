package com.lingrixin.donetwork;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lingrixin.donetwork.adapter.MyAdapter;
import com.lingrixin.donetwork.adapter.SpacesItemDecoration;
import com.lingrixin.donetwork.base.BaseActivity;
import com.lingrixin.donetwork.business.httpcliect.HttpCliectActivity;
import com.lingrixin.donetwork.business.okhttp.OkhttpActivity;
import com.lingrixin.donetwork.business.retrofit.RetrofitActivity;
import com.lingrixin.donetwork.business.urlhttpconnection.UrlHttpConnectionActivity;
import com.lingrixin.donetwork.business.volley.VolleyActivity;
import com.lingrixin.donetwork.utils.TempUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_button)
    RecyclerView rvButton;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void setup() {
        MyAdapter adapter = new MyAdapter(TempUtil.getList(this));
        adapter.setItemClick(new MyAdapter.itemClick() {
            @Override
            public void click(View v, int position) {
                Toast.makeText(MainActivity.this, "我是第" + position + "个", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "我" + TempUtil.getList(MainActivity.this).get(position), Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        startActivity(UrlHttpConnectionActivity.class);
                        break;
                    case 1:
                        startActivity(HttpCliectActivity.class);
                        break;
                    case 2:
                        startActivity(VolleyActivity.class);
                        break;
                    case 3:
                        startActivity(OkhttpActivity.class);
                        break;
                    case 4:
                        startActivity(RetrofitActivity.class);
                        break;
                }
            }
        });
        rvButton.setAdapter(adapter);
        rvButton.addItemDecoration(new SpacesItemDecoration(2, 50, false));
        rvButton.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
