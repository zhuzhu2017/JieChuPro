package com.jiechu.jiechupro.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiechu.jiechupro.R;

/**
 * 作业现场照片
 * Created by allen on 2017/9/12.
 */

public class XCPicFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_xc_pic, null);
        return view;
    }
}
