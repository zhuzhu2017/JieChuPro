package com.jiechu.jiechupro.utils;

import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * author:gaojingwei
 * date:2016/12/27
 * des: Fresco图片加载工具类
 */

public class FrescoUtils {

    /**
     * 设置通用图片显示
     *
     * @param url
     * @param draweeView
     */
    public static void setCommonPic(String url, SimpleDraweeView draweeView) {
        if (!TextUtils.isEmpty(url)) {
            try {
                Uri uri = Uri.parse(url);
                if (uri != null) {
                    draweeView.setImageURI(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            draweeView.setImageURI("");
        }
    }

}
