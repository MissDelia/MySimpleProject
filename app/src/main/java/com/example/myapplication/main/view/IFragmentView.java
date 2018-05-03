package com.example.myapplication.main.view;

import com.example.myapplication.util.base.BaseView;
import com.example.myapplication.util.db.entity.MusicInfo;

import java.util.List;

public interface IFragmentView extends BaseView {

    void onResult(List<MusicInfo> list);

}
