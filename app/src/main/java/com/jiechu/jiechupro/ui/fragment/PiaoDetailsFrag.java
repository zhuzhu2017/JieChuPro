package com.jiechu.jiechupro.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.PiaoDetailsBean;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.PiaoDetailsApi;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 工作票详情
 * Created by allen on 2017/9/12.
 */

public class PiaoDetailsFrag extends RxFragment {

    @BindView(R.id.tv_piao_title)
    TextView tvPiaoTitle;
    @BindView(R.id.tv_piao_num)
    TextView tvPiaoNum;
    @BindView(R.id.tv_details_fpr)
    TextView tvDetailsFpr;
    @BindView(R.id.tv_details_fpsj)
    TextView tvDetailsFpsj;
    @BindView(R.id.tv_details_aqdj)
    TextView tvDetailsAqdj;
    @BindView(R.id.tv_details_fsfw)
    TextView tvDetailsFsfw;
    @BindView(R.id.tv_details_zyfw)
    TextView tvDetailsZyfw;
    @BindView(R.id.tv_details_zynr)
    TextView tvDetailsZynr;
    @BindView(R.id.tv_details_gzyxq)
    TextView tvDetailsGzyxq;
    @BindView(R.id.tv_details_gzldr)
    TextView tvDetailsGzldr;
    @BindView(R.id.tv_details_sj)
    TextView tvDetailsSj;
    @BindView(R.id.tv_details_zyzcyxm)
    TextView tvDetailsZyzcyxm;
    @BindView(R.id.tv_details_qtzyzcyxm)
    TextView tvDetailsQtzyzcyxm;
    @BindView(R.id.tv_details_xtdsb)
    TextView tvDetailsXtdsb;
    @BindView(R.id.tv_details_jdxwz)
    TextView tvDetailsJdxwz;
    @BindView(R.id.tv_details_zyqfhcs)
    TextView tvDetailsZyqfhcs;
    @BindView(R.id.tv_details_qtaqcs)
    TextView tvDetailsQtaqcs;
    @BindView(R.id.tv_details_bgzyzcyjl)
    TextView tvDetailsBgzyzcyjl;
    @BindView(R.id.tv_details_gzpjssj)
    TextView tvDetailsGzpjssj;
    @BindView(R.id.tv_details_ldrqz)
    TextView tvDetailsLdrqz;
    @BindView(R.id.tv_details_fprqz)
    TextView tvDetailsFprqz;

    Unbinder unbinder;
    @BindView(R.id.tv_gq_title)
    TextView tvGqTitle;
    private String id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            id = arguments.getString("id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_piao_details, null);
        unbinder = ButterKnife.bind(this, view);
        //初始化视图
        initView();
        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //设置标题
        tvPiaoTitle.setText("接触网第一种工作票");
        //获取数据
        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        PiaoDetailsApi piaoDetailsApi = new PiaoDetailsApi(piaoDetailsListener, this);
        piaoDetailsApi.setKeyValue(id);
        HttpManager.getInstance().fragmentConnToServer(piaoDetailsApi);
    }

    /**
     * 回调监听
     */
    HttpOnNextListener<JSONObject> piaoDetailsListener = new HttpOnNextListener<JSONObject>() {
        @Override
        public void onNext(JSONObject object) {
            if (object != null) {
                //设置数据
                try {
                    PiaoDetailsBean detailsBean = new PiaoDetailsBean();
                    detailsBean.setGqmc((object.getString("gqmc") + "").replace("null", ""));
                    detailsBean.setGzpbh((object.getString("gzpbh") + "").replace("null", ""));
                    detailsBean.setFpr((object.getString("fpr") + "").replace("null", ""));
                    detailsBean.setFprq_app((object.getString("fprq_app") + "").replace("null", ""));
                    detailsBean.setGzldr_aqdj((object.getString("gzldr_aqdj") + "").replace("null", ""));
                    detailsBean.setFsfw((object.getString("fsfw") + "").replace("null", ""));
                    detailsBean.setZydd((object.getString("zydd") + "").replace("null", ""));
                    detailsBean.setZynr((object.getString("zynr") + "").replace("null", ""));
                    detailsBean.setGzpyxqqd_app((object.getString("gzpyxqqd_app") + "").replace("null", ""));
                    detailsBean.setGzpyxqzd_app((object.getString("gzpyxqzd_app") + "").replace("null", ""));
                    detailsBean.setSj_select((object.getString("sj_select") + "").replace("null", ""));
                    detailsBean.setZyzcy_list((object.getString("zyzcy_list") + "").replace("null", ""));
                    detailsBean.setWbcy_list((object.getString("wbcy_list") + "").replace("null", ""));
                    detailsBean.setXtdsb((object.getString("xtdsb") + "").replace("null", ""));
                    detailsBean.setZsdxwz((object.getString("zsdxwz") + "").replace("null", ""));
                    detailsBean.setZyqfhcs((object.getString("zyqfhcs") + "").replace("null", ""));
                    detailsBean.setQtaqcs((object.getString("qtaqcs") + "").replace("null", ""));
                    detailsBean.setGzpldrqz((object.getString("gzpldrqz") + "").replace("null", ""));
                    detailsBean.setFprqz((object.getString("fprqz") + "").replace("null", ""));
                    //设置数据
                    setData(detailsBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 设置数据
     *
     * @param detailsBean
     */
    private void setData(PiaoDetailsBean detailsBean) {
        //工区设置
        tvGqTitle.setText(detailsBean.getGqmc());
        //工作票编号
        tvPiaoNum.setText(detailsBean.getGzpbh());
        //发票人
        tvDetailsFpr.setText(detailsBean.getFpr());
        //发票时间
        tvDetailsFpsj.setText(detailsBean.getFprq_app());
        //安全等级
        tvDetailsAqdj.setText(detailsBean.getGzldr_aqdj());
        //封锁范围
        tvDetailsFsfw.setText(detailsBean.getFsfw());
        //作业范围
        tvDetailsZyfw.setText(detailsBean.getZydd());
        //作业内容
        tvDetailsZynr.setText(detailsBean.getZynr());
        //工作票有效期
        tvDetailsGzyxq.setText(detailsBean.getGzpyxqqd_app() + "至" + detailsBean.getGzpyxqzd_app());
        //工作领导人
        tvDetailsGzldr.setText(detailsBean.getGzldr());
        //司机
        tvDetailsSj.setText(detailsBean.getSj_select());
        //作业组成员姓名及安全等级
        tvDetailsZyzcyxm.setText(detailsBean.getZyzcy_list());
        //其他作业组成员姓名及安全等级
        tvDetailsQtzyzcyxm.setText(detailsBean.getWbcy_list());
        //需要停电的设备
        tvDetailsXtdsb.setText(detailsBean.getXtdsb());
        //装设接地线的位置
        tvDetailsJdxwz.setText(detailsBean.getZsdxwz());
        //作业区防护措施
        tvDetailsZyqfhcs.setText(detailsBean.getZyqfhcs());
        //其他安全措施
        tvDetailsQtaqcs.setText(detailsBean.getQtaqcs());
        //工作领导人签字
        tvDetailsLdrqz.setText(detailsBean.getGzpldrqz());
        //发票人签字
        tvDetailsFprqz.setText(detailsBean.getFprqz());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
