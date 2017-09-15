package com.jiechu.jiechupro.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.XCPicBean;

import java.util.List;

/**
 * Created by allen on 2017/9/15.
 */

public class TopHoriMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity ctx;
    private List<XCPicBean> parentPicList;
    private int mSelect = 0;

    public TopHoriMenuAdapter(Activity ctx, List<XCPicBean> parentPicList) {
        this.ctx = ctx;
        this.parentPicList = parentPicList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_top_hori_menu, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(view, (Integer) view.getTag());
                }
            }
        });
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tvMenu.setText(parentPicList.get(position).getOutName());
            if (mSelect == position) {
                ((ItemViewHolder) holder).bottom_line.setVisibility(View.VISIBLE);
            } else {
                ((ItemViewHolder) holder).bottom_line.setVisibility(View.INVISIBLE);
            }
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return parentPicList == null ? 0 : parentPicList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvMenu;
        View bottom_line;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvMenu = itemView.findViewById(R.id.tv_horizon_index);
            bottom_line = itemView.findViewById(R.id.bottom_line);
        }
    }

    /**
     * 切换菜单
     *
     * @param positon
     */
    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

}
