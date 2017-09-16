package com.jiechu.jiechupro.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.XCPicBean;
import com.jiechu.jiechupro.utils.FrescoUtils;

import java.util.List;

/**
 * Created by allen on 2017/9/15.
 */

public class HoriIconPicAdapter extends RecyclerView.Adapter<HoriIconPicAdapter.HoriIconViewHolder> {

    private Activity ctx;
    private List<XCPicBean.Pictures> picturesList;

    public HoriIconPicAdapter(Activity ctx, List<XCPicBean.Pictures> picturesList) {
        this.ctx = ctx;
        this.picturesList = picturesList;
    }

    @Override
    public HoriIconViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_hori_icon, parent, false);
        return new HoriIconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HoriIconViewHolder holder, int position) {
        XCPicBean.Pictures pictures = picturesList.get(position);
        FrescoUtils.setCommonPic(pictures.getUrl_CZXL_ICON(), holder.iv_hori_icon);
        boolean selected = pictures.isSelected();
        if (selected) {
            holder.view_selected.setVisibility(View.VISIBLE);
        } else {
            holder.view_selected.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return picturesList == null ? 0 : picturesList.size();
    }

    public class HoriIconViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView iv_hori_icon;
        View view_selected;

        public HoriIconViewHolder(View itemView) {
            super(itemView);
            iv_hori_icon = itemView.findViewById(R.id.iv_hori_icon);
            view_selected = itemView.findViewById(R.id.view_selected);
        }
    }

}
