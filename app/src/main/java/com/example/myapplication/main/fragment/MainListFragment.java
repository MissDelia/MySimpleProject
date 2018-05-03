package com.example.myapplication.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.main.adapter.MainListAdapter;
import com.example.myapplication.main.presenter.MainPresenter;
import com.example.myapplication.main.view.IFragmentView;
import com.example.myapplication.util.MyApp;
import com.example.myapplication.util.MyToast;
import com.example.myapplication.util.base.BaseFragment;
import com.example.myapplication.util.db.entity.MusicInfo;

import java.util.List;

public class MainListFragment extends BaseFragment<IFragmentView, MainPresenter> implements IFragmentView {

    private RecyclerView mRecyclerView;

    private MainListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, null);
        mRecyclerView = view.findViewById(R.id.music_list);
        mAdapter = new MainListAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        getPresenter().searchList(null);
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
        if (list != null && list.size() > 0) {
            mAdapter.setData(list);
        } else {
            showCommon("暂无数据或无权限");
        }
    }
}
