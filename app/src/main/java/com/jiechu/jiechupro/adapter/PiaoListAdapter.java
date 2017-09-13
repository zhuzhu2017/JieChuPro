package com.jiechu.jiechupro.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.PiaoListBean;

import java.util.List;

/**
 * Created by allen on 2017/9/13.
 */

public class PiaoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity ctx;
    private List<PiaoListBean> piaoList;

    public PiaoListAdapter(Activity ctx, List<PiaoListBean> piaoList) {
        this.ctx = ctx;
        this.piaoList = piaoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.piao_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            PiaoListBean listBean = piaoList.get(position);
            ((ItemViewHolder) holder).tv_xh.setText((listBean.getXh() == null || listBean.getXh().equals("null")) ? "" : listBean.getXh());
            ((ItemViewHolder) holder).tv_ddlx.setText((listBean.getDdlx() == null || listBean.getDdlx().equals("null")) ? "" : listBean.getDdlx());
            ((ItemViewHolder) holder).tv_fpr.setText((listBean.getFpr() == null || listBean.getFpr().equals("null")) ? "" : listBean.getFpr());
            ((ItemViewHolder) holder).tv_fprq.setText((listBean.getFprq() == null || listBean.getFprq().equals("null")) ? "" : listBean.getFprq());
            ((ItemViewHolder) holder).tv_gq.setText((listBean.getGq() == null || listBean.getGq().equals("null")) ? "" : listBean.getGq());
            ((ItemViewHolder) holder).tv_ldr.setText((listBean.getLdr() == null || listBean.getLdr().equals("null")) ? "" : listBean.getLdr());
            ((ItemViewHolder) holder).tv_ph.setText((listBean.getPh() == null || listBean.getPh().equals("null")) ? "" : listBean.getPh());
            ((ItemViewHolder) holder).tv_yxq.setText((listBean.getYxq() == null || listBean.getYxq().equals("null")) ? "" : listBean.getYxq());
            ((ItemViewHolder) holder).tv_zt.setText((listBean.getZt() == null || listBean.getZt().equals("null")) ? "" : listBean.getZt());
            ((ItemViewHolder) holder).tv_zydd.setText((listBean.getZydd() == null || listBean.getZydd().equals("null")) ? "" : listBean.getZydd());
            ((ItemViewHolder) holder).tv_zynr.setText((listBean.getZynr() == null || listBean.getZynr().equals("null")) ? "" : listBean.getZynr());
        }
    }

    @Override
    public int getItemCount() {
        return piaoList == null ? 0 : piaoList.size();
    }

    public void updateList(List<PiaoListBean> piaoList) {
        this.piaoList = piaoList;
        notifyDataSetChanged();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_xh;
        TextView tv_ph;
        TextView tv_gq;
        TextView tv_zydd;
        TextView tv_zynr;
        TextView tv_ddlx;
        TextView tv_fpr;
        TextView tv_fprq;
        TextView tv_yxq;
        TextView tv_zt;
        TextView tv_ldr;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_xh = itemView.findViewById(R.id.tv_xh);
            tv_ph = itemView.findViewById(R.id.tv_ph);
            tv_gq = itemView.findViewById(R.id.tv_gq);
            tv_zydd = itemView.findViewById(R.id.tv_zydd);
            tv_zynr = itemView.findViewById(R.id.tv_zynr);
            tv_ddlx = itemView.findViewById(R.id.tv_ddlx);
            tv_fpr = itemView.findViewById(R.id.tv_fpr);
            tv_fprq = itemView.findViewById(R.id.tv_fprq);
            tv_yxq = itemView.findViewById(R.id.tv_yxq);
            tv_zt = itemView.findViewById(R.id.tv_zt);
            tv_ldr = itemView.findViewById(R.id.tv_ldr);
        }
    }

}
