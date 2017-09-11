package com.jiechu.jiechupro.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by allen on 2017/9/11.
 */

public class DisplayUtil {
    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return 返回屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        if (context == null) return 0;
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度
     *
     * @param context 上下文
     * @return 返回屏幕高度
     */
    public static int getScreenHeight(Context context) {
        if (context == null) return 0;
        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

}
