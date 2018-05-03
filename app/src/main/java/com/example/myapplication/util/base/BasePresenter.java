package com.example.myapplication.util.base;

import android.content.Context;

public abstract class BasePresenter<V extends BaseView> {

    private V baseView;

    private Context mContext;

    public BasePresenter(Context context) {
        this.mContext = context;
    }

    public void bindView(V baseView) {
        this.baseView = baseView;
    }

    public void detachView() {
        baseView = null;
    }

    public V getView() {
        return baseView;
    }

    public Context getmContext() {
        return mContext;
    }
}
