package com.example.myapplication.util;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.db.dao.DaoMaster;
import com.example.myapplication.db.dao.DaoSession;

/**
 * Created by x5035 on 2018/3/17.
 */

public class MyApp extends Application {

    private static MyApp context;

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static synchronized MyApp getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
//		if (false&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
//			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//					.detectAll().penaltyDialog().build());
//			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//					.detectAll().penaltyDeath().build());
//		}
        super.onCreate();
        context = this;

        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "sport-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public Context getContext() {
        return context;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

}
