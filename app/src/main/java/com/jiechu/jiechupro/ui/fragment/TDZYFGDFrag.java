package com.jiechu.jiechupro.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiechu.jiechupro.R;

/**
 * 停电作业分工单
 * Created by allen on 2017/9/12.
 */

public class TDZYFGDFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_tdzyf, null);
        return view;
    }
}
