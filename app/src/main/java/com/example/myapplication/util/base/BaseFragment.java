package com.example.myapplication.util.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;

import com.example.myapplication.util.MyToast;

public abstract class BaseFragment<V extends BaseView, P extends  BasePresenter<V>> extends Fragment {

    private P presenter;
    private V view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    /**
     * 提示内容
     *
     * @param msg
     */
    public void showCommon(String msg){
        MyToast.getInstance(getActivity()).showCommon(msg, Gravity.CENTER);
    }

    public void showCommonBottom(String msg){
        MyToast.getInstance(getActivity()).showCommon(msg);
    }

}
