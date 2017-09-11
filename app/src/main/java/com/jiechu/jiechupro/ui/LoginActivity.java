package com.jiechu.jiechupro.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.utils.DisplayUtil;

/**
 * Created by allen on 2017/9/7.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        int screenWidth = DisplayUtil.getScreenWidth(this);
        int screenHeight = DisplayUtil.getScreenHeight(this);
        Log.d("屏幕尺寸", "宽度===：" + screenWidth + "==高度==：" + screenHeight);
    }
}
