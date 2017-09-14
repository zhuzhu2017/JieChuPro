package com.jiechu.jiechupro.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 工前预备会及收工会记录
 * Created by allen on 2017/9/12.
 */

public class GQYBHJSGHJLFrag extends Fragment {

    @BindView(R.id.tv_piao_num)
    TextView tvPiaoNum;
    @BindView(R.id.tv_details_gqyx)
    TextView tvDetailsGqyx;
    @BindView(R.id.tv_details_fgjl)
    TextView tvDetailsFgjl;
    @BindView(R.id.tv_details_rwly)
    TextView tvDetailsRwly;
    @BindView(R.id.tv_details_sgjl)
    TextView tvDetailsSgjl;
    @BindView(R.id.tv_details_qt)
    TextView tvDetailsQt;
    @BindView(R.id.tv_details_xdml)
    TextView tvDetailsXdml;
    @BindView(R.id.tv_details_xdmlh)
    TextView tvDetailsXdmlh;
    @BindView(R.id.tv_details_sgjlldr)
    TextView tvDetailsSgjlldr;
    Unbinder unbinder;
    private Activity ctx;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_gqyb, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
