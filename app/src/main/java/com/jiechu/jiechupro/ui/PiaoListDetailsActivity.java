package com.jiechu.jiechupro.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.ui.fragment.SwitchFrag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工作票列表详情
 * Created by allen on 2017/9/12.
 */

public class PiaoListDetailsActivity extends BaseActivity implements SwitchFrag.IOnTitleSelectedListener {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.drawer_content)
    FrameLayout drawerContent;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private Activity ctx;

    private String id;  //主键id

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        ButterKnife.bind(this);
        ctx = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
        }
        //初始化视图
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //设置标题
        tvHeaderTitle.setText("工作票");
        //设置侧滑布局
        SwitchFrag switchFrag = new SwitchFrag();
        getSupportFragmentManager().beginTransaction().add(R.id.drawer_content, switchFrag).commit();
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onTitleSelected(View view, int position) {
        Log.d("点击", "点击了" + position);
    }
}
