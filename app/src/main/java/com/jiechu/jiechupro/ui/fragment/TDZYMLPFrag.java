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
 * 停电作业命令票
 * Created by allen on 2017/9/12.
 */

public class TDZYMLPFrag extends Fragment {

    @BindView(R.id.tv_piao_title)
    TextView tvPiaoTitle;
    @BindView(R.id.tv_piao_num)
    TextView tvPiaoNum;
    @BindView(R.id.tv_details_mlbh)
    TextView tvDetailsMlbh;
    @BindView(R.id.tv_details_pzsj)
    TextView tvDetailsPzsj;
    @BindView(R.id.tv_details_mlnr)
    TextView tvDetailsMlnr;
    @BindView(R.id.tv_details_yqwcsj)
    TextView tvDetailsYqwcsj;
    @BindView(R.id.tv_details_flr)
    TextView tvDetailsFlr;
    @BindView(R.id.tv_details_slr)
    TextView tvDetailsSlr;
    @BindView(R.id.tv_details_xlsj)
    TextView tvDetailsXlsj;
    @BindView(R.id.tv_details_xlr)
    TextView tvDetailsXlr;
    @BindView(R.id.tv_details_gdddy)
    TextView tvDetailsGdddy;
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
        View view = inflater.inflate(R.layout.frag_tdzym, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
