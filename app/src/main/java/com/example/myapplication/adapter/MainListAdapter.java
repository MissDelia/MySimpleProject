package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.db.entity.MusicInfo;

import java.util.List;

/**
 * Created by x5035 on 2018/3/20.
 */

public class MainListAdapter extends RecyclerView.Adapter {

    private List<MusicInfo> mList;

    private Context mContext;

    public MainListAdapter(List<MusicInfo> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    public void setData(List<MusicInfo> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_mainlist_layout, null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).mTv.setText(mList.get(position).getMusicName());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.music_name);
        }
    }

}
