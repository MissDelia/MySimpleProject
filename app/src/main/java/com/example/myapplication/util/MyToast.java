package com.example.myapplication.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by x5035 on 2018/3/18.
 */

public class MyToast {

    private static MyToast myToast;
    private static Toast mToast;
    private static Context mContext;
    private ToastTextView tv_content;

    public static synchronized MyToast getInstance(Context context) {
        if (myToast == null) {
            myToast = new MyToast();
        }

        mContext = context;
        if (mToast == null) {
            mToast = new Toast(mContext);
            mToast.setDuration(1);
        }

        return myToast;
    }

    public void setTime(int length) {
        if (mToast != null) {
            mToast.setDuration(length);
        }

    }

    public void showCommon(String text) {
        this.setAttribute(text, 80, 0, 20);
    }

    public void showCommon(String text, int gravity) {
        this.setAttribute(text, gravity, 0, 0);
    }

    public void showCommonCenter(String text) {
        this.setAttribute(text, 17, 0, 0);
    }

    public void showCommon(String text, int gravity, int xOffset, int yOffset) {
        this.setAttribute(text, gravity, xOffset, yOffset);
    }

    private void setAttribute(String text, int gravity, int xOffset, int yOffset) {
        if (this.tv_content == null) {
            this.tv_content = new ToastTextView(mContext);
        }

        this.tv_content.setText(text);
        mToast.setView(this.tv_content);
        mToast.setGravity(gravity, xOffset, yOffset);
        mToast.show();
    }

}
