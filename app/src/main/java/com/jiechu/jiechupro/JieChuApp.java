package com.jiechu.jiechupro;

import android.app.Application;
import android.content.Context;

/**
 * Created by allen on 2017/9/12.
 */

public class JieChuApp extends Application {
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }
}
