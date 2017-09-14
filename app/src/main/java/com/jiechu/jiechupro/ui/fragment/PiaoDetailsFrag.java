package com.jiechu.jiechupro.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 工作票详情
 * Created by allen on 2017/9/12.
 */

public class PiaoDetailsFrag extends Fragment {

    @BindView(R.id.tv_piao_title)
    TextView tvPiaoTitle;
    @BindView(R.id.tv_piao_num)
    TextView tvPiaoNum;
    @BindView(R.id.tv_details_fpr)
    TextView tvDetailsFpr;
    @BindView(R.id.tv_details_fpsj)
    TextView tvDetailsFpsj;
    @BindView(R.id.tv_details_aqdj)
    TextView tvDetailsAqdj;
    @BindView(R.id.tv_details_fsfw)
    TextView tvDetailsFsfw;
    @BindView(R.id.tv_details_zyfw)
    TextView tvDetailsZyfw;
    @BindView(R.id.tv_details_zynr)
    TextView tvDetailsZynr;
    @BindView(R.id.tv_details_gzyxq)
    TextView tvDetailsGzyxq;
    @BindView(R.id.tv_details_gzldr)
    TextView tvDetailsGzldr;
    @BindView(R.id.tv_details_sj)
    TextView tvDetailsSj;
    @BindView(R.id.tv_details_zyzcyxm)
    TextView tvDetailsZyzcyxm;
    @BindView(R.id.tv_details_qtzyzcyxm)
    TextView tvDetailsQtzyzcyxm;
    @BindView(R.id.tv_details_xtdsb)
    TextView tvDetailsXtdsb;
    @BindView(R.id.tv_details_jdxwz)
    TextView tvDetailsJdxwz;
    @BindView(R.id.tv_details_zyqfhcs)
    TextView tvDetailsZyqfhcs;
    @BindView(R.id.tv_details_qtaqcs)
    TextView tvDetailsQtaqcs;
    @BindView(R.id.tv_details_bgzyzcyjl)
    TextView tvDetailsBgzyzcyjl;
    @BindView(R.id.tv_details_gzpjssj)
    TextView tvDetailsGzpjssj;
    @BindView(R.id.tv_details_ldrqz)
    TextView tvDetailsLdrqz;
    @BindView(R.id.tv_details_fprqz)
    TextView tvDetailsFprqz;

    Unbinder unbinder;
    private Activity ctx;
    private String id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = getActivity();
        Bundle arguments = getArguments();
        if (arguments != null) {
            id = arguments.getString("id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_piao_details, null);
        unbinder = ButterKnife.bind(this, view);
        //初始化视图
        initView();
        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //设置标题
        tvPiaoTitle.setText("接触网第一种工作票");
        //获取数据
        Log.d("工作票id", id + "===");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
