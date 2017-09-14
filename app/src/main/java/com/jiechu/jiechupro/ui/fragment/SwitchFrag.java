package com.jiechu.jiechupro.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.adapter.SwitchAdapter;
import com.jiechu.jiechupro.model.TitleBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 菜单切换
 * Created by allen on 2017/9/13.
 */

public class SwitchFrag extends Fragment {
    @BindView(R.id.lv_switch)
    ListView lvSwitch;
    Unbinder unbinder;

    private Activity ctx;

    private String[] titles = {"第一种工作票", "工前预备会及收工会记录", "停电作业命令票",
            "运统46草拟稿及防护“三率”统计", "作业现场照片浏览"};
    private List<TitleBean> titleList = new ArrayList<>();
    private SwitchAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_switch, null);
        unbinder = ButterKnife.bind(this, view);
        //初始化数据
        initData();
        return view;
    }

    /**
     * 初始化数据
     */
    private int oldPos;

    private void initData() {
        titleList.clear();
        for (int i = 0; i < titles.length; i++) {
            TitleBean titleBean = new TitleBean();
            if (i == 0) {
                titleBean.setSelected(true);
            } else {
                titleBean.setSelected(false);
            }
            titleBean.setTitle(titles[i]);
            titleList.add(titleBean);
        }
        //设置数据
        adapter = new SwitchAdapter(ctx, titleList);
        lvSwitch.setAdapter(adapter);
        //添加条目点击事件
        lvSwitch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < titleList.size(); j++) {
                    titleList.get(j).setSelected(false);
                }
                titleList.get(i).setSelected(true);
                adapter.updateList(titleList);
                if (i != oldPos) {
                    if (onTitleSelectedListener != null) {
                        onTitleSelectedListener.onTitleSelected(view, i);
                    }
                }
                oldPos = i;
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onTitleSelectedListener = (IOnTitleSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement IOnTitleSelectedListener");
        }
    }

    private IOnTitleSelectedListener onTitleSelectedListener;

    public interface IOnTitleSelectedListener {
        void onTitleSelected(View view, int position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
