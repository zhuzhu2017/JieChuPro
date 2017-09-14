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
 * 停电作业分工单
 * Created by allen on 2017/9/12.
 */

public class TDZYFGDFrag extends Fragment {

    @BindView(R.id.tv_piao_title)
    TextView tvPiaoTitle;
    @BindView(R.id.tv_piao_num)
    TextView tvPiaoNum;
    @BindView(R.id.tv_details_gzdd)
    TextView tvDetailsGzdd;
    @BindView(R.id.tv_details_gzldr)
    TextView tvDetailsGzldr;
    @BindView(R.id.tv_details_zynr)
    TextView tvDetailsZynr;
    @BindView(R.id.tv_details_yldd)
    TextView tvDetailsYldd;
    @BindView(R.id.tv_details_ylry)
    TextView tvDetailsYlry;
    @BindView(R.id.tv_details_zzfhdd)
    TextView tvDetailsZzfhdd;
    @BindView(R.id.tv_details_zzlly)
    TextView tvDetailsZzlly;
    @BindView(R.id.tv_details_fhy1)
    TextView tvDetailsFhy1;
    @BindView(R.id.tv_details_fhy2)
    TextView tvDetailsFhy2;
    @BindView(R.id.tv_details_fhy3)
    TextView tvDetailsFhy3;
    @BindView(R.id.tv_details_jdgh1)
    TextView tvDetailsJdgh1;
    @BindView(R.id.tv_details_czr1)
    TextView tvDetailsCzr1;
    @BindView(R.id.tv_details_jhr1)
    TextView tvDetailsJhr1;
    @BindView(R.id.tv_details_jdgh2)
    TextView tvDetailsJdgh2;
    @BindView(R.id.tv_details_czr2)
    TextView tvDetailsCzr2;
    @BindView(R.id.tv_details_jhr2)
    TextView tvDetailsJhr2;
    @BindView(R.id.tv_details_jdgh3)
    TextView tvDetailsJdgh3;
    @BindView(R.id.tv_details_czr3)
    TextView tvDetailsCzr3;
    @BindView(R.id.tv_details_jhr3)
    TextView tvDetailsJhr3;
    @BindView(R.id.tv_details_jdgh4)
    TextView tvDetailsJdgh4;
    @BindView(R.id.tv_details_czr4)
    TextView tvDetailsCzr4;
    @BindView(R.id.tv_details_jhr4)
    TextView tvDetailsJhr4;
    @BindView(R.id.tv_details_jdgh5)
    TextView tvDetailsJdgh5;
    @BindView(R.id.tv_details_czr5)
    TextView tvDetailsCzr5;
    @BindView(R.id.tv_details_jhr5)
    TextView tvDetailsJhr5;
    @BindView(R.id.tv_details_jdgh6)
    TextView tvDetailsJdgh6;
    @BindView(R.id.tv_details_czr6)
    TextView tvDetailsCzr6;
    @BindView(R.id.tv_details_jhr6)
    TextView tvDetailsJhr6;
    @BindView(R.id.tv_details_dyzyfwjnr)
    TextView tvDetailsDyzyfwjnr;
    @BindView(R.id.tv_details_dygkzyr)
    TextView tvDetailsDygkzyr;
    @BindView(R.id.tv_details_dyfzry)
    TextView tvDetailsDyfzry;
    @BindView(R.id.tv_details_dyjhr)
    TextView tvDetailsDyjhr;
    @BindView(R.id.tv_details_dyclr)
    TextView tvDetailsDyclr;
    @BindView(R.id.tv_details_dyjlr)
    TextView tvDetailsDyjlr;
    @BindView(R.id.tv_details_dezyfwjnr)
    TextView tvDetailsDezyfwjnr;
    @BindView(R.id.tv_details_degkzyr)
    TextView tvDetailsDegkzyr;
    @BindView(R.id.tv_details_defzry)
    TextView tvDetailsDefzry;
    @BindView(R.id.tv_details_dejhr)
    TextView tvDetailsDejhr;
    @BindView(R.id.tv_details_declr)
    TextView tvDetailsDeclr;
    @BindView(R.id.tv_details_dejlr)
    TextView tvDetailsDejlr;
    @BindView(R.id.tv_details_lxfs)
    TextView tvDetailsLxfs;
    @BindView(R.id.tv_details_bz)
    TextView tvDetailsBz;
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
        View view = inflater.inflate(R.layout.frag_tdzyf, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
