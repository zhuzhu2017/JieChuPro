package com.jiechu.jiechupro.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.TDZYMLPBean;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.TDZYMLPApi;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 停电作业命令票
 * Created by allen on 2017/9/12.
 */

public class TDZYMLPFrag extends RxFragment {

    @BindView(R.id.tv_piao_title)
    TextView tvPiaoTitle;
    @BindView(R.id.tv_piao_num)
    TextView tvPiaoNum;
    @BindView(R.id.tv_details_mlbh)
    TextView tvDetailsMlbh;
    @BindView(R.id.tv_details_pzsj)
    TextView tvDetailsPzsj;
    @BindView(R.id.tv_details_mlnr)
    TextView tvDetailsMlnr;
    @BindView(R.id.tv_details_yqwcsj)
    TextView tvDetailsYqwcsj;
    @BindView(R.id.tv_details_flr)
    TextView tvDetailsFlr;
    @BindView(R.id.tv_details_slr)
    TextView tvDetailsSlr;
    @BindView(R.id.tv_details_xlsj)
    TextView tvDetailsXlsj;
    @BindView(R.id.tv_details_xlr)
    TextView tvDetailsXlr;
    @BindView(R.id.tv_details_gdddy)
    TextView tvDetailsGdddy;
    Unbinder unbinder;
    @BindView(R.id.tv_gq_title)
    TextView tvGqTitle;
    private String id;
    private String gzplb;
    private String gzpbh = "";
    private String gqmc = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            id = arguments.getString("id");
            gzplb = arguments.getString("gzplb");
            gzpbh = arguments.getString("gzpbh");
            gqmc = arguments.getString("gqmc");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_tdzym, null);
        unbinder = ButterKnife.bind(this, view);
        //初始化视图
        initView();
        return view;
    }

    private void initView() {
        //标题
        tvPiaoTitle.setText("停电作业命令票");
        //获取数据
        initData();
    }

    private void initData() {
        TDZYMLPApi api = new TDZYMLPApi(listener, this);
        api.setKeyValue(id);
        HttpManager.getInstance().fragmentConnToServer(api);
    }

    /**
     * 回调监听
     */
    HttpOnNextListener<JSONObject> listener = new HttpOnNextListener<JSONObject>() {
        @Override
        public void onNext(JSONObject object) {
            if (object != null) {
                Log.d("结果", object.toString());
                try {
                    TDZYMLPBean bean = new TDZYMLPBean();
                    bean.setGqmc(object.has("gqmc") ? (object.getString("gqmc") + "").replace("null", "") : gqmc);
                    bean.setGzpbh(object.has("gzpbh") ? (object.getString("gzpbh") + "").replace("null", "") : gzpbh);
                    bean.setMlbh(object.has("mlbh") ? (object.getString("mlbh") + "").replace("null", "") : "");
                    bean.setPzsj_app(object.has("pzsj_app") ? (object.getString("pzsj_app") + "").replace("null", "") : "");
                    bean.setMlnr(object.has("mlnr") ? (object.getString("mlnr") + "").replace("null", "") : "");
                    bean.setWcsj_app(object.has("wcsj_app") ? (object.getString("wcsj_app") + "").replace("null", "") : "");
                    bean.setFlr(object.has("flr") ? (object.getString("flr") + "").replace("null", "") : "");
                    bean.setSlr(object.has("slr") ? (object.getString("slr") + "").replace("null", "") : "");
                    bean.setXlsj_app(object.has("xlsj_app") ? (object.getString("xlsj_app") + "").replace("null", "") : "");
                    bean.setXlr(object.has("xlr") ? (object.getString("xlr") + "").replace("null", "") : "");
                    bean.setGdddy(object.has("gdddy") ? (object.getString("gdddy") + "").replace("null", "") : "");
                    //设置数据
                    setData(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void setData(TDZYMLPBean bean) {
        //工区名称
        tvGqTitle.setText(bean.getGqmc());
        //工作票编号
        tvPiaoNum.setText("第" + bean.getGzpbh() + "号");
        //命令编号
        tvDetailsMlbh.setText(bean.getMlbh());
        //批准时间
        tvDetailsPzsj.setText(bean.getPzsj_app());
        //命令内容
        tvDetailsMlnr.setText(bean.getMlnr());
        //要求完成时间
        tvDetailsYqwcsj.setText(bean.getWcsj_app());
        //发令人
        tvDetailsFlr.setText(bean.getFlr());
        //受令人
        tvDetailsSlr.setText(bean.getSlr());
        //销令时间
        tvDetailsXlsj.setText(bean.getXlsj_app());
        //销令人
        tvDetailsXlr.setText(bean.getXlr());
        //供电调度员
        tvDetailsGdddy.setText(bean.getGdddy());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
