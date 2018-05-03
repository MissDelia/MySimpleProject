package com.example.myapplication.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.main.presenter.MainPresenter;
import com.example.myapplication.main.view.IFragmentView;
import com.example.myapplication.util.MyApp;
import com.example.myapplication.util.base.BaseFragment;
import com.example.myapplication.util.db.entity.MusicInfo;

import java.util.List;

public class MineInfoFragment extends BaseFragment<IFragmentView, MainPresenter> implements IFragmentView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_info, null);
        return view;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(MyApp.getInstance());
    }

    @Override
    public IFragmentView createView() {
        return this;
    }

    @Override
    public void onResult(List<MusicInfo> list) {

    }
}
