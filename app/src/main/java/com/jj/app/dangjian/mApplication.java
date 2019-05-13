package com.jj.app.dangjian;

import android.app.Application;

import com.jj.app.dangjian.utils.StaticClass;

import cn.bmob.v3.Bmob;

/**
 * @作者: JJ
 * @创建时间: 2019/4/15 1:36 PM
 * @Version 1.0
 * @描述:
 */
public class mApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, StaticClass.BMOD_APP_ID);
//        Bmob.initialize(this,"12784168944a56ae41c4575686b7b332");
    }
}
