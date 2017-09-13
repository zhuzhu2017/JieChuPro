package com.jiechu.jiechupro.view.recyclerview.DefaultHeaderAndFooterCreator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.view.progresswheel.ProgressWheel;
import com.jiechu.jiechupro.view.recyclerview.PullToRefresh.PullToRefreshRecyclerView;
import com.jiechu.jiechupro.view.recyclerview.PullToRefresh.RefreshHeaderCreator;


/**
 * Created by Administrator on 2016/9/28.
 */
public class DefaultRefreshHeaderCreator extends RefreshHeaderCreator {

    private View mRefreshView;
    private TextView tv;
    private ProgressWheel progressWheel;


    @Override
    public boolean onStartPull(float distance, int lastState) {
        progressWheel.setVisibility(View.VISIBLE);
        if (lastState == PullToRefreshRecyclerView.STATE_DEFAULT) {
            tv.setText("下拉刷新");
        } else if (lastState == PullToRefreshRecyclerView.STATE_RELEASE_TO_REFRESH) {
            tv.setText("下拉刷新");
        }
        return true;
    }

    @Override
    public void onStopRefresh() {
        progressWheel.setVisibility(View.INVISIBLE);
        tv.setText("刷新完成");
    }


    @Override
    public boolean onReleaseToRefresh(float distance, int lastState) {
        progressWheel.setVisibility(View.VISIBLE);
        if (lastState == PullToRefreshRecyclerView.STATE_DEFAULT) {
            tv.setText("释放刷新");
        } else if (lastState == PullToRefreshRecyclerView.STATE_PULLING) {
            tv.setText("释放刷新");
        }
        return true;
    }

    @Override
    public void onStartRefreshing() {
        progressWheel.setVisibility(View.VISIBLE);
        tv.setText("正在刷新");
    }

    @Override
    public View getRefreshView(Context context, RecyclerView recyclerView) {
        mRefreshView = LayoutInflater.from(context).inflate(R.layout.layout_swipe_header, recyclerView, false);
        progressWheel = (ProgressWheel) mRefreshView.findViewById(R.id.progressbar);
        tv = (TextView) mRefreshView.findViewById(R.id.tvRefresh);
        return mRefreshView;
    }


}
