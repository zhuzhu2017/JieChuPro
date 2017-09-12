package com.jiechu.jiechupro.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.utils.CommonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.lv_line)
    RecyclerView lvLine;
    @BindView(R.id.lv_plant)
    RecyclerView lvPlant;
    @BindView(R.id.lv_workstation)
    RecyclerView lvWorkstation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lvLine.setLayoutManager(layoutManager);
        lvPlant.setLayoutManager(layoutManager);
        lvWorkstation.setLayoutManager(layoutManager);
        //初始化数据
        initData();
    }

    /**
     * 获取筛选数据
     */
    private void initData() {
        String main = CommonUtil.readAssetsString(this, "main");
        try {
            JSONObject object = new JSONObject(main);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {

    }
}
