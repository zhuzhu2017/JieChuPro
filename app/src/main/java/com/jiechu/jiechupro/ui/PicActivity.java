package com.jiechu.jiechupro.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.utils.FrescoUtils;
import com.jiechu.jiechupro.view.photodraweeview.PhotoDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by allen on 2017/9/16.
 */

public class PicActivity extends BaseActivity {
    @BindView(R.id.big_pic_view)
    PhotoDraweeView bigPicView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
        FrescoUtils.setBigPic(this, url, bigPicView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.shrink_fade_in, R.anim.shrink_fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
