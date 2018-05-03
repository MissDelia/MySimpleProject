package com.example.myapplication.util.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapplication.util.MyToast;

public abstract class BaseViewActivity<V extends BaseView, P extends  BasePresenter<V>>  extends AppCompatActivity {

    private P presenter;
    private V view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }

        if (presenter == null) {
            presenter = createPresenter();
        }
        if (view == null) {
            view = createView();
        }
        if (presenter != null && view != null) {
            presenter.bindView(view);
        }
    }

    /**
     * 提示内容
     *
     * @param msg
     */
    public void showCommon(String msg){
        MyToast.getInstance(this).showCommon(msg, Gravity.CENTER);
    }

    public void showCommonBottom(String msg){
        MyToast.getInstance(this).showCommon(msg);
    }

    /**
     * 跳转UI页面
     * @param c 下一个UI类
     * @param extras 传递参数
     */
    public void goIntent(Class<?> c,Bundle extras){
        Intent intent = new Intent(this, c);
        if (extras != null) intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null && view != null) {
            presenter.detachView();
        }
    }

    public P getPresenter() {
        return presenter;
    }

    public abstract P createPresenter();
    public abstract V createView();

}
