package com.jiechu.jiechupro.view.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * author:gaojingwei
 * date:2016/10/24
 * des:
 */

public class NoScrollRecyclerview extends RecyclerView {
    public NoScrollRecyclerview(Context context) {
        super(context);
    }

    public NoScrollRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            return true;
//        }
//        return super.onTouchEvent(ev);
//    }

}
