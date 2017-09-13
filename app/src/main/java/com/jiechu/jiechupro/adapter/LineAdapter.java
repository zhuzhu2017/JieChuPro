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
 * Created by allen on 2017/9/12.
 */

public class LineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MainFilterBean.LinesBean> lines;

    public LineAdapter(Context context, List<MainFilterBean.LinesBean> lines) {
        this.context = context;
        this.lines = lines;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_filter_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onLinesItemClickedListener != null) {
                    onLinesItemClickedListener.onItemClicked(view, (Integer) view.getTag());
                }
            }
        });
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            MainFilterBean.LinesBean linesBean = lines.get(position);
            ((ItemViewHolder) holder).tv_lines_item.setText(linesBean.getLname());
            boolean lineSelected = linesBean.isLineSelected();
            if (lineSelected) {
                ((ItemViewHolder) holder).tv_lines_item.setBackgroundResource(R.drawable.shape_oval_solid_blue);
                ((ItemViewHolder) holder).tv_lines_item.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                ((ItemViewHolder) holder).tv_lines_item.setBackgroundResource(R.drawable.shape_oval_stroke_blue);
                ((ItemViewHolder) holder).tv_lines_item.setTextColor(context.getResources().getColor(R.color.common_blue));
            }
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return lines == null ? 0 : lines.size();
    }

    public void updataList(List<MainFilterBean.LinesBean> lines) {
        this.lines = lines;
        notifyDataSetChanged();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_lines_item;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_lines_item = itemView.findViewById(R.id.tv_lines_item);
        }
    }

    public void setIOnLinesItemClickedListener(IOnLinesItemClickedListener onLinesItemClickedListener) {
        this.onLinesItemClickedListener = onLinesItemClickedListener;
    }

    private IOnLinesItemClickedListener onLinesItemClickedListener;

    public interface IOnLinesItemClickedListener {
        void onItemClicked(View view, int position);
    }

}
