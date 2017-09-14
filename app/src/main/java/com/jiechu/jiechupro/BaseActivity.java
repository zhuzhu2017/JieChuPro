package com.jiechu.jiechupro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by allen on 2017/9/11.
 */

public class BaseActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JieChuApp.getInstance().addActivity(this);
    }

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

}
