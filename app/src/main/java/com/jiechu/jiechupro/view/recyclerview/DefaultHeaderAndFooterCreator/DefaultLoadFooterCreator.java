package com.jiechu.jiechupro.view.recyclerview.DefaultHeaderAndFooterCreator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.view.progresswheel.ProgressWheel;
import com.jiechu.jiechupro.view.recyclerview.PullToLoad.LoadFooterCreator;
import com.jiechu.jiechupro.view.recyclerview.PullToLoad.PullToLoadRecyclerView;


/**
 * Created by Administrator on 2016/9/28.
 */
public class DefaultLoadFooterCreator extends LoadFooterCreator {

    private View mLoadView;
    private TextView tv;
    private ProgressWheel progressWheel;


    @Override
    public boolean onStartPull(float distance, int lastState) {
        progressWheel.setVisibility(View.VISIBLE);
        if (lastState == PullToLoadRecyclerView.STATE_DEFAULT) {
            tv.setText("上拉加载");
        } else if (lastState == PullToLoadRecyclerView.STATE_RELEASE_TO_LOAD) {
            tv.setText("上拉加载");
        }
        return true;
    }

    @Override
    public boolean onReleaseToLoad(float distance, int lastState) {
        progressWheel.setVisibility(View.VISIBLE);
        if (lastState == PullToLoadRecyclerView.STATE_DEFAULT ) {
            tv.setText("松手加载");
        } else if (lastState == PullToLoadRecyclerView.STATE_PULLING) {
            tv.setText("松手加载");
        }
        return true;
    }

    @Override
    public void onStartLoading() {
        progressWheel.setVisibility(View.VISIBLE);
        tv.setText("正在加载");
    }

    @Override
    public void onStopLoad() {
        progressWheel.setVisibility(View.INVISIBLE);
        tv.setText("加载完成");
    }

    @Override
    public View getLoadView(Context context, RecyclerView recyclerView) {
        mLoadView = LayoutInflater.from(context).inflate(R.layout.layout_ptr_ptl,recyclerView,false);
        progressWheel = (ProgressWheel) mLoadView.findViewById(R.id.iv);
        tv = (TextView) mLoadView.findViewById(R.id.tv);
        return mLoadView;
    }


}
