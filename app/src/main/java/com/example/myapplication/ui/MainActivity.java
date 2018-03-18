package com.example.myapplication.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.util.MyToast;
import com.example.myapplication.util.StatusBarUtil;
import com.example.myapplication.util.runnable.MainPageRunnable;

public class MainActivity extends BaseCompatActivity {

    private EditText mEtSearch;

    private Button mBtnSreach;

    private TextView mTvFlesh;

    private ListView mListView;

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

                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void postRunnable(int type) {
        mHandlerT.post(new MainPageRunnable(type, mHandler));
    }

}
