package com.example.myapplication.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by x5035 on 2018/3/17.
 */
@Entity
public class MusicInfo {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "music_name")
    @NotNull
    private String musicName;

    @Property(nameInDb = "musicPath")
    @NotNull
    private String musicPath;

    @Generated(hash = 564204627)
    public MusicInfo(Long id, @NotNull String musicName,
            @NotNull String musicPath) {
        this.id = id;
        this.musicName = musicName;
        this.musicPath = musicPath;
    }

    @Generated(hash = 1735505054)
    public MusicInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMusicName() {
        return this.musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicPath() {
        return this.musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

}
