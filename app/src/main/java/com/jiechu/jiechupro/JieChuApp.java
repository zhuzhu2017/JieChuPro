package com.jiechu.jiechupro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by allen on 2017/9/12.
 */

public class JieChuApp extends Application {
    public static Context appContext;
    public static JieChuApp instance;

    public static String token = "";

    /**
     * 存放Activity
     */
    public List<WeakReference<Activity>> activityList = new LinkedList<WeakReference<Activity>>();

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        instance = this;
    }

    /**
     * 获取实例化
     */
    public static JieChuApp getInstance() {
        return instance;
    }

    /**
     * 添加到列表里，方便退出时销毁
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(new WeakReference<Activity>(activity));
    }

    /**
     * 退出系统
     */
    public void exit() {
        for (WeakReference<Activity> activity : activityList) {
            if (activity != null) {
                Activity act = activity.get();
                if (act != null) {
                    act.finish();
                }
            }
        }
        System.exit(0);
    }

}
