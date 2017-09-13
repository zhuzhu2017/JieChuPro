package com.jiechu.jiechupro.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.TongJiApi;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 统计页面
 * Created by allen on 2017/9/11.
 */

public class TongJiActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_piao01_total_num)
    TextView tvPiao01TotalNum;
    @BindView(R.id.tv_piao01_finished)
    TextView tvPiao01Finished;
    @BindView(R.id.tv_piao01_unfinished)
    TextView tvPiao01Unfinished;
    @BindView(R.id.tv_piao01_zuofei)
    TextView tvPiao01Zuofei;
    @BindView(R.id.ll_piao01)
    LinearLayout llPiao01;
    @BindView(R.id.tv_piao02_total_num)
    TextView tvPiao02TotalNum;
    @BindView(R.id.tv_piao02_finished)
    TextView tvPiao02Finished;
    @BindView(R.id.tv_piao02_unfinished)
    TextView tvPiao02Unfinished;
    @BindView(R.id.tv_piao02_zuofei)
    TextView tvPiao02Zuofei;
    @BindView(R.id.tv_piao03_total_num)
    TextView tvPiao03TotalNum;
    @BindView(R.id.tv_piao03_finished)
    TextView tvPiao03Finished;
    @BindView(R.id.tv_piao03_unfinished)
    TextView tvPiao03Unfinished;
    @BindView(R.id.tv_piao03_zuofei)
    TextView tvPiao03Zuofei;
    @BindView(R.id.ll_piao02)
    LinearLayout llPiao02;
    @BindView(R.id.ll_piao03)
    LinearLayout llPiao03;

    private Activity ctx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongji);
        ButterKnife.bind(this);
        ctx = this;
        //初始化视图
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //设置标题
        tvHeaderTitle.setText("工作票统计");
        //初始化数据
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        TongJiApi tongJiApi = new TongJiApi(tongJiListener, this);
        tongJiApi.setW_sc("100");
        HttpManager.getInstance().connToServer(tongJiApi);
    }

    //网络请求监听
    HttpOnNextListener tongJiListener = new HttpOnNextListener<JSONObject>() {

        @Override
        public void onNext(JSONObject object) {
            if (object != null) {
                try {
                    //第一种工作票
                    tvPiao01TotalNum.setText(object.getInt("gzp1_allcount") + "");
                    tvPiao01Unfinished.setText(object.getInt("gzp1_befinishedcount") + "");
                    tvPiao01Finished.setText(object.getInt("gzp1_finishedcount") + "");
                    tvPiao01Zuofei.setText(object.getInt("gzp1_delcount") + "");
                    //第二种工作票
                    tvPiao02TotalNum.setText(object.getInt("gzp2_allcount") + "");
                    tvPiao02Unfinished.setText(object.getInt("gzp2_befinishedcount") + "");
                    tvPiao02Finished.setText(object.getInt("gzp2_finishedcount") + "");
                    tvPiao02Zuofei.setText(object.getInt("gzp2_delcount") + "");
                    //第三种工作票
                    tvPiao03TotalNum.setText(object.getInt("gzp3_allcount") + "");
                    tvPiao03Unfinished.setText(object.getInt("gzp3_befinishedcount") + "");
                    tvPiao03Finished.setText(object.getInt("gzp3_finishedcount") + "");
                    tvPiao03Zuofei.setText(object.getInt("gzp3_delcount") + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @OnClick({R.id.iv_header_back, R.id.ll_piao01, R.id.ll_piao02, R.id.ll_piao03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.ll_piao01:
                openActivity(PiaoListActivity.class);
                break;
            case R.id.ll_piao02:
            case R.id.ll_piao03:
//                Toast.makeText(ctx, "暂无数据", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
