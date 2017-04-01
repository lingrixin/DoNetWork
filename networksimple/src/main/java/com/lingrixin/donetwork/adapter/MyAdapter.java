package com.lingrixin.donetwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lingrixin.donetwork.R;

import java.util.List;

/**
 * Created by LRXx on 2017-4-1.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    public MyAdapter(List<String> list) {
        this.list = list;
    }

    private List<String> list;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_button, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String s = list.get(position);
        TextView tv = holder.tv_bu;
        tv.setText(s);
        if(itemClick!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.click(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_bu;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_bu = (TextView) itemView.findViewById(R.id.tv_bu);
        }
    }

    private itemClick itemClick;

    public void setItemClick(MyAdapter.itemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface itemClick {
        void click(View v, int position);
    }
}
