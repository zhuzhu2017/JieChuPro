package com.jiechu.jiechupro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.MainFilterBean;

import java.util.List;

/**
 * 工区数据Adapter
 * Created by allen on 2017/9/12.
 */

public class WSAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MainFilterBean.LinesBean.PlantsBean.WorkstationBean> workstationList;

    public WSAdapter(Context context, List<MainFilterBean.LinesBean.PlantsBean.WorkstationBean> workstationList) {
        this.context = context;
        this.workstationList = workstationList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ws_filter_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onWsItemClickedListener != null) {
                    onWsItemClickedListener.onItemClicked(view, (Integer) view.getTag());
                }
            }
        });
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            MainFilterBean.LinesBean.PlantsBean.WorkstationBean workstationBean = workstationList.get(position);
            ((ItemViewHolder) holder).tv_ws_item.setText(workstationBean.getWname());
            boolean wtSelected = workstationBean.isWTSelected();
            if (wtSelected) {
                ((ItemViewHolder) holder).tv_ws_item.setBackgroundResource(R.drawable.shape_oval_solid_green);
                ((ItemViewHolder) holder).tv_ws_item.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                ((ItemViewHolder) holder).tv_ws_item.setBackgroundResource(R.drawable.shape_oval_stroke_green);
                ((ItemViewHolder) holder).tv_ws_item.setTextColor(context.getResources().getColor(R.color.common_green));
            }
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return workstationList == null ? 0 : workstationList.size();
    }

    public void updataList(List<MainFilterBean.LinesBean.PlantsBean.WorkstationBean> workstationList) {
        this.workstationList = workstationList;
        notifyDataSetChanged();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ws_item;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_ws_item = itemView.findViewById(R.id.tv_ws_item);
        }
    }

    public void setIOnWSItemClickedListener(IOnWSItemClickedListener onWsItemClickedListener) {
        this.onWsItemClickedListener = onWsItemClickedListener;
    }

    private IOnWSItemClickedListener onWsItemClickedListener;

    public interface IOnWSItemClickedListener {
        void onItemClicked(View view, int position);
    }

}
