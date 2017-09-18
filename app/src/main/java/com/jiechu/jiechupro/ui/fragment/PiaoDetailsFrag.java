package com.jiechu.jiechupro.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.PiaoDetailsBean;
import com.jiechu.jiechupro.model.ZYZCYBean;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.PiaoDetailsApi;
import com.jiechu.jiechupro.utils.DataUtil;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

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
    private String gzplb;
    private String gzpbh;
    private String gqmc;

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
                    detailsBean.setGqmc(object.has("gqmc") ? (object.getString("gqmc") + "").replace("null", "") : gqmc);
                    detailsBean.setGzpbh(object.has("gzpbh") ? (object.getString("gzpbh") + "").replace("null", "") : gzpbh);
                    detailsBean.setFpr(object.has("fpr") ? (object.getString("fpr") + "").replace("null", "") : "");
                    detailsBean.setFprq_app(object.has("fprq_app") ? (object.getString("fprq_app") + "").replace("null", "") : "");
                    detailsBean.setGzldr_aqdj(object.has("gzldr_aqdj") ? (object.getString("gzldr_aqdj") + "").replace("null", "") : "");
                    detailsBean.setFsfw(object.has("fsfw") ? (object.getString("fsfw") + "").replace("null", "") : "");
                    detailsBean.setZydd(object.has("zydd") ? (object.getString("zydd") + "").replace("null", "") : "");
                    detailsBean.setZynr(object.has("zynr") ? (object.getString("zynr") + "").replace("null", "") : "");
                    detailsBean.setGzpyxqqd_app(object.has("gzpyxqqd_app") ? (object.getString("gzpyxqqd_app") + "").replace("null", "") : "");
                    detailsBean.setGzpyxqzd_app(object.has("gzpyxqzd_app") ? (object.getString("gzpyxqzd_app") + "").replace("null", "") : "");
                    detailsBean.setGzldr(object.has("gzldr") ? (object.getString("gzldr") + "").replace("null", "") : "");
                    //设置作业人员
                    if (object.has("zyzcy") && object.get("zyzcy") instanceof JSONArray) {
                        //全部作业组成员数组
                        JSONArray zyzcyArray = object.getJSONArray("zyzcy");
                        //获取司机信息
                        if (object.has("sj_select")) {
                            String sj_select = object.getString("sj_select");
                            List<ZYZCYBean> sjList = DataUtil.filterSj(zyzcyArray, sj_select);
                            //设置数据
                            String sjString = "";
                            if (sjList != null && sjList.size() > 0) {
                                for (int i = 0; i < sjList.size(); i++) {
                                    ZYZCYBean bean = sjList.get(i);
                                    String cymc = bean.getCymc();
                                    if (i < sjList.size() - 1) {
                                        sjString = sjString + cymc + "/";
                                    } else {
                                        sjString = sjString + cymc;
                                    }
                                }
                            }
                            detailsBean.setSj_select(sjString);
                        }
                        //获取作业组成员数据
                        if (object.has("zyzcy_list")) {
                            String zyzcy_list = object.getString("zyzcy_list");
                            List<ZYZCYBean> zyzcyList = DataUtil.filterZyzcy(zyzcyArray, zyzcy_list);
                            //设置数据
                            String zyzcyString = "";
                            if (zyzcyList != null && zyzcyList.size() > 0) {
                                for (int i = 0; i < zyzcyList.size(); i++) {
                                    ZYZCYBean bean = zyzcyList.get(i);
                                    String cymc = bean.getCymc();
                                    String aqdj = bean.getAqdj();
                                    zyzcyString = zyzcyString + cymc + "（" + aqdj + "）" + " ";
                                }
                            }
                            detailsBean.setZyzcy_list(zyzcyString);
                        }
                        //获取其他作业组成员数据
                        if (object.has("wbcy_list")) {
                            String wbcy_list = object.getString("wbcy_list");
                            List<ZYZCYBean> wbcyList = DataUtil.filterWbcy(zyzcyArray, wbcy_list);
                            //设置数据
                            String wbcyString = "";
                            if (wbcyList != null && wbcyList.size() > 0) {
                                for (int i = 0; i < wbcyList.size(); i++) {
                                    ZYZCYBean bean = wbcyList.get(i);
                                    String cymc = bean.getCymc();
                                    String aqdj = bean.getAqdj();
                                    wbcyString = wbcyString + cymc + "（" + aqdj + "）" + " ";
                                }
                            }
                            detailsBean.setWbcy_list(wbcyString);
                        }
                    }
                    detailsBean.setXtdsb(object.has("xtdsb") ? (object.getString("xtdsb") + "").replace("null", "") : "");
                    detailsBean.setZsdxwz(object.has("zsdxwz") ? (object.getString("zsdxwz") + "").replace("null", "") : "");
                    detailsBean.setZyqfhcs(object.has("zyqfhcs") ? (object.getString("zyqfhcs") + "").replace("null", "") : "");
                    detailsBean.setQtaqcs(object.has("qtaqcs") ? (object.getString("qtaqcs") + "").replace("null", "") : "");
                    detailsBean.setBgzyzcyjl(object.has("bgzyzcyjl") ? (object.getString("bgzyzcyjl") + "").replace("null", "") : "");
                    detailsBean.setGzpjssj_app(object.has("gzpjssj_app") ? (object.getString("gzpjssj_app") + "").replace("null", "") : "");
                    detailsBean.setGzpldrqz(object.has("gzpldrqz") ? (object.getString("gzpldrqz") + "").replace("null", "") : "");
                    detailsBean.setFprqz(object.has("fprqz") ? (object.getString("fprqz") + "").replace("null", "") : "");
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
        tvPiaoNum.setText("第" + detailsBean.getGzpbh() + "号");
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
        //变更作业组成员记录
        tvDetailsBgzyzcyjl.setText(detailsBean.getBgzyzcyjl());
        //工作票结束时间
        tvDetailsGzpjssj.setText(detailsBean.getGzpjssj_app());
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
