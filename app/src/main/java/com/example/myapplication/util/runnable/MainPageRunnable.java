package com.example.myapplication.util.runnable;

import android.os.Handler;
import android.os.Message;

import com.example.myapplication.db.EntityManager;
import com.example.myapplication.db.entity.MusicInfo;

import java.util.List;

/**
 * Created by x5035 on 2018/3/18.
 */

public class MainPageRunnable implements Runnable {
    // 类型
    private int type = 0;
    // 回调
    private Handler mHanlder;

    public MainPageRunnable(int type, Handler handler) {
        this.type = type;
        this.mHanlder = handler;
    }

    @Override
    public void run() {
        switch (type) {
            case 0:
                List<MusicInfo> list = EntityManager.getInstance().getMusicInfoDao().loadAll();
                Message msg = mHanlder.obtainMessage();
                msg.what = type;
                if (list != null && list.size() > 0) {
                    msg.obj = list;
                } else {
                    msg.obj = null;
                }
                mHanlder.sendMessage(msg);
                break;
            default:
                break;
        }
    }
}
