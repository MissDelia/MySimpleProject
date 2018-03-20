package com.example.myapplication.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MainListAdapter;
import com.example.myapplication.db.entity.MusicInfo;
import com.example.myapplication.util.MyToast;
import com.example.myapplication.util.StatusBarUtil;
import com.example.myapplication.util.runnable.MainPageRunnable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseCompatActivity implements View.OnClickListener {

    private EditText mEtSearch;

    private Button mBtnSreach;

    private TextView mTvFlesh;

    private RecyclerView mListView;

    private MainListAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private List<MusicInfo> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.getInstance().StatusBarLightMode(this);

        initView();
        initHandler();

        postRunnable(0);
    }

    private void initView() {
        mEtSearch = findViewById(R.id.et_search);
        mBtnSreach = findViewById(R.id.btn_search);
        mTvFlesh = findViewById(R.id.tv_flesh_list);
        mListView = findViewById(R.id.music_list);

        mTvFlesh.setOnClickListener(this);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MainListAdapter(null, this);
        // 设置布局管理器
        mListView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mListView.setAdapter(mAdapter);
    }

    private Handler mHandler, mHandlerT;
    private void initHandler() {
        HandlerThread mHThread = new HandlerThread("mainThread");
        mHThread.start();
        mHandlerT = new Handler(mHThread.getLooper());

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        if (message.obj == null) {
                            MyToast.getInstance(MainActivity.this).showCommonCenter("无数据，请点击刷新");
                        } else {
                            mList = (List<MusicInfo>) message.obj;
                            mAdapter.setData(mList);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_flesh_list:
                mList = getMusicData(this);
                mTvFlesh.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                mAdapter.setData(mList);
                break;
            default:
                break;
        }
    }

    public static List<MusicInfo> getMusicData(Context context) {
        List<MusicInfo> list = new ArrayList<MusicInfo>();
        // 媒体库查询语句（写一个工具类MusicUtils）
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                MusicInfo song = new MusicInfo();
                song.setMusicName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                song.setMusicPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                list.add(song);
            }
            // 释放资源
            cursor.close();
        }

        return list;
    }

    private void postRunnable(int type) {
        mHandlerT.post(new MainPageRunnable(type, mHandler));
    }
}
