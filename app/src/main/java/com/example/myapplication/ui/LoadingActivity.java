package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.util.StatusBarUtil;

public class LoadingActivity extends BaseCompatActivity {

    private ImageView mLoadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        StatusBarUtil.getInstance().StatusBarLightMode(this);
        initHandler();
        mLoadImage = findViewById(R.id.loading_img);
        Glide.with(this)
                .load(getDrawable(R.drawable.loading_image))
                .into(mLoadImage);
        mHandler.sendEmptyMessageDelayed(0, 2*1000);
    }

    private Handler mHandler;
    private void initHandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                        startActivity(intent);
                        LoadingActivity.this.finish();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

}
