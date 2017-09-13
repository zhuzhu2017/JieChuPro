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
 * 车间数据Adapter
 * Created by allen on 2017/9/12.
 */

public class PlantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MainFilterBean.LinesBean.PlantsBean> plants;

    public PlantsAdapter(Context context, List<MainFilterBean.LinesBean.PlantsBean> plants) {
        this.context = context;
        this.plants = plants;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plant_filter_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPlantsItemClickedListener != null) {
                    onPlantsItemClickedListener.onItemClicked(view, (Integer) view.getTag());
                }
            }
        });
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            MainFilterBean.LinesBean.PlantsBean plantsBean = plants.get(position);
            ((ItemViewHolder) holder).tv_plant_item.setText(plantsBean.getPname());
            boolean lineSelected = plantsBean.isPlantsSelected();
            if (lineSelected) {
                ((ItemViewHolder) holder).tv_plant_item.setBackgroundResource(R.drawable.shape_oval_solid_yellow);
                ((ItemViewHolder) holder).tv_plant_item.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                ((ItemViewHolder) holder).tv_plant_item.setBackgroundResource(R.drawable.shape_oval_stroke_yellow);
                ((ItemViewHolder) holder).tv_plant_item.setTextColor(context.getResources().getColor(R.color.common_yellow));
            }
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return plants == null ? 0 : plants.size();
    }

    public void updataList(List<MainFilterBean.LinesBean.PlantsBean> plants) {
        this.plants = plants;
        notifyDataSetChanged();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_plant_item;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_plant_item = itemView.findViewById(R.id.tv_plant_item);
        }
    }

    public void setIOnPlantsItemClickedListener(IOnPlantsItemClickedListener onPlantsItemClickedListener) {
        this.onPlantsItemClickedListener = onPlantsItemClickedListener;
    }

    private IOnPlantsItemClickedListener onPlantsItemClickedListener;

    public interface IOnPlantsItemClickedListener {
        void onItemClicked(View view, int position);
    }

}
