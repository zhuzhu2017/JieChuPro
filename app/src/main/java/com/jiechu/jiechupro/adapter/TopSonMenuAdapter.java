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

public class TopSonMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity ctx;
    private List<XCPicBean.Pictures> picturesList;

    public TopSonMenuAdapter(Activity ctx, List<XCPicBean.Pictures> picturesList) {
        this.ctx = ctx;
        this.picturesList = picturesList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_son_menu, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            XCPicBean.Pictures pictures = picturesList.get(position);
            ((ItemViewHolder) holder).tv_menu_item.setText(pictures.getInnerName());
            boolean selected = pictures.isSelected();
            if (selected) {
                ((ItemViewHolder) holder).tv_menu_item.setBackgroundResource(R.drawable.shape_oval_solid_blue);
                ((ItemViewHolder) holder).tv_menu_item.setTextColor(ctx.getResources().getColor(R.color.white));
            } else {
                ((ItemViewHolder) holder).tv_menu_item.setBackgroundResource(R.drawable.shape_oval_stroke_blue);
                ((ItemViewHolder) holder).tv_menu_item.setTextColor(ctx.getResources().getColor(R.color.common_blue));
            }
        }
    }

    @Override
    public int getItemCount() {
        return picturesList == null ? 0 : picturesList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_menu_item;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_menu_item = itemView.findViewById(R.id.tv_menu_item);
        }
    }

    public void upDateList(List<XCPicBean.Pictures> picturesList) {
        this.picturesList = picturesList;
        notifyDataSetChanged();
    }

}
