package com.hany.mosquitoforecast;

import android.app.Application;

import com.hany.mosquitoforecast.util.LogManager;

/**
 * Created by HanyLuv on 2016-08-03.
 * 앱이 실행될때 딱 한번, 프로세스가 죽어야 다시 불려옴
 */
public class ApplicationEx extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        LogManager.e("ApplicationEx 입니다.");
    }

}
