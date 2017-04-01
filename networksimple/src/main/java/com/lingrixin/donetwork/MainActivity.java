package com.lingrixin.donetwork;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lingrixin.donetwork.adapter.MyAdapter;
import com.lingrixin.donetwork.adapter.SpacesItemDecoration;
import com.lingrixin.donetwork.base.BaseActivity;
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
                Toast.makeText(MainActivity.this, "我" + TempUtil.getList(MainActivity.this).get(position) , Toast.LENGTH_SHORT).show();
            }
        });
        rvButton.setAdapter(adapter);
        rvButton.addItemDecoration(new SpacesItemDecoration(2,50,false));
        rvButton.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
