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
 * 运统46草拟稿及防护“三率”统计
 * Created by allen on 2017/9/12.
 */

public class YT46TJFrag extends Fragment {

    @BindView(R.id.tv_piao_title)
    TextView tvPiaoTitle;
    @BindView(R.id.tv_piao_num)
    TextView tvPiaoNum;
    @BindView(R.id.tv_details_zzllyxm)
    TextView tvDetailsZzllyxm;
    @BindView(R.id.tv_details_rq)
    TextView tvDetailsRq;
    @BindView(R.id.tv_details_dj)
    TextView tvDetailsDj;
    @BindView(R.id.tv_details_xj)
    TextView tvDetailsXj;
    @BindView(R.id.tv_details_sgfzrqz)
    TextView tvDetailsSgfzrqz;
    @BindView(R.id.tv_details_fhdjzycs)
    TextView tvDetailsFhdjzycs;
    @BindView(R.id.tv_details_sjyglccs)
    TextView tvDetailsSjyglccs;
    @BindView(R.id.tv_details_xdcs)
    TextView tvDetailsXdcs;
    @BindView(R.id.tv_details_sjsdcs)
    TextView tvDetailsSjsdcs;
    @BindView(R.id.tv_details_bxlccs)
    TextView tvDetailsBxlccs;
    @BindView(R.id.tv_details_gjlccs)
    TextView tvDetailsGjlccs;
    @BindView(R.id.tv_details_fhdjzycs2)
    TextView tvDetailsFhdjzycs2;
    @BindView(R.id.tv_details_sjyglccs2)
    TextView tvDetailsSjyglccs2;
    @BindView(R.id.tv_details_xdcs2)
    TextView tvDetailsXdcs2;
    @BindView(R.id.tv_details_sjsdcs2)
    TextView tvDetailsSjsdcs2;
    @BindView(R.id.tv_details_bxlccs2)
    TextView tvDetailsBxlccs2;
    @BindView(R.id.tv_details_gjlccs2)
    TextView tvDetailsGjlccs2;
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
        View view = inflater.inflate(R.layout.frag_yt_46, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
