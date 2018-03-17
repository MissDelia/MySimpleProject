package com.example.myapplication.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.util.StatusBarUtil;

public class MainActivity extends BaseCompatActivity {

    private EditText mEtSearch;

    private Button mBtnSreach;

    private TextView mTvFlesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.getInstance().StatusBarLightMode(this);

        initView();
        initHandler();
    }

    private void initView() {
        mEtSearch = findViewById(R.id.et_search);
        mBtnSreach = findViewById(R.id.btn_search);
        mTvFlesh = findViewById(R.id.tv_flesh_list);
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

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private Runnable postRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

}
