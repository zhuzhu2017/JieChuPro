package com.jiechu.jiechupro.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.TitleBean;

import java.util.List;

/**
 * 标题切换
 * Created by allen on 2017/9/13.
 */

public class SwitchAdapter extends BaseAdapter {

    private Activity ctx;
    private List<TitleBean> titleList;

    public SwitchAdapter(Activity ctx, List<TitleBean> titleList) {
        this.ctx = ctx;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return titleList == null ? 0 : titleList.size();
    }

    @Override
    public Object getItem(int i) {
        return titleList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemViewHolder holder = null;
        if (view == null) {
            holder = new ItemViewHolder();
            view = LayoutInflater.from(ctx).inflate(R.layout.switch_item, null);
            //绑定控件
            holder.divider = view.findViewById(R.id.divider);
            holder.selected_view = view.findViewById(R.id.selected_view);
            holder.tv_switch_title = view.findViewById(R.id.tv_switch_title);
            //背包
            view.setTag(holder);
        } else {
            holder = (ItemViewHolder) view.getTag();
        }
        //设置数据
        TitleBean titleBean = titleList.get(i);
        holder.tv_switch_title.setText(titleBean.getTitle());
        boolean selected = titleBean.isSelected();
        if (selected) {
            holder.selected_view.setVisibility(View.VISIBLE);
        } else {
            holder.selected_view.setVisibility(View.INVISIBLE);
        }
        if (i == titleList.size() - 1) {
            holder.divider.setVisibility(View.GONE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }
        return view;
    }

    public void updateList(List<TitleBean> titleList) {
        this.titleList = titleList;
        notifyDataSetChanged();
    }

    private class ItemViewHolder {
        TextView tv_switch_title;
        View selected_view;
        View divider;
    }

}
