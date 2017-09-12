package com.jiechu.jiechupro;

import android.app.Application;
import android.content.Context;

/**
 * Created by allen on 2017/9/12.
 */

public class JieChuApp extends Application {
    public static Context appContext;
    public static final String BASE_IP = "1.192.90.115:9923";
    public static final String BASE_URL = "http://" + BASE_IP + "/Api/";

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }
}
