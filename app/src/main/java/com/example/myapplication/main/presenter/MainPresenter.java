package com.example.myapplication.main.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;

import com.example.myapplication.main.view.IFragmentView;
import com.example.myapplication.util.base.BasePresenter;
import com.example.myapplication.util.db.EntityManager;
import com.example.myapplication.util.db.entity.MusicInfo;

import java.util.List;

public class MainPresenter extends BasePresenter<IFragmentView> {

    private EntityManager em;

    public MainPresenter(Context context) {
        super(context);
        em = new EntityManager();
    }

    public void searchList(final String musicName) {
        new Thread() {

            @Override
            public void run() {
                if (musicName == null) {
                    List<MusicInfo> list = em.getMusicInfoDao().loadAll();
                    if (list != null && list.size() > 0) {
                        getView().onResult(list);
                    } else {
                        if ( ContextCompat.checkSelfPermission(getmContext(), Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
                            getView().onResult(null);
                            return;
                        }
                        Cursor cursor = getmContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
                        if (cursor != null) {
                            while (cursor.moveToNext()) {
                                MusicInfo info = new MusicInfo();
                                info.setMusicName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
                                info.setMusicPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                                em.getMusicInfoDao().insert(info);
                            }
                        }
                        list = em.getMusicInfoDao().loadAll();
                        getView().onResult(list);
                    }
                } else {
                    List<MusicInfo> list = em.getMusicInfoDao().queryRaw("music_name=?", musicName);
                    getView().onResult(list);
                }
            }
        }.start();
    }

}
