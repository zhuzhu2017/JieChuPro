package com.jiechu.jiechupro.view.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by lkz on 2016/11/12.
 */

public class CustomRecycleView extends RecyclerView {
    public CustomRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecycleView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
