package com.jiechu.jiechupro.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by allen on 2017/9/11.
 */

public class CommonUtil {

    /**
     * 读取assets文件夹下面的文件内容
     *
     * @param context  上下文
     * @param fileName 读取文件名
     * @return 返回的文件内容
     */
    public static String readAssetsString(Context context, String fileName) {
        if (context == null) return null;
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getApplicationContext().getAssets().open(fileName + ".txt");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Finally stick the string into the text view.
            return new String(buffer, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
