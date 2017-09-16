package com.jiechu.jiechupro.utils;

import android.net.Uri;

/**
 * author:gaojingwei
 * date:2017/1/5
 * des:
 */

public interface FrescoBitmapCallback<T> {
    void onSuccess(Uri uri, T result);

    void onFailure(Uri uri, Throwable throwable);

    void onCancel(Uri uri);
}
