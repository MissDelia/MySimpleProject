package com.example.myapplication.util.db;

import com.example.myapplication.util.db.dao.MusicInfoDao;
import com.example.myapplication.util.MyApp;

/**
 * Created by x5035 on 2018/3/17.
 */

public class EntityManager {

    private static EntityManager entityManager;

    public static synchronized EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }

    public MusicInfoDao getMusicInfoDao() {
        return MyApp.getInstance().getDaoSession().getMusicInfoDao();
    }

}
