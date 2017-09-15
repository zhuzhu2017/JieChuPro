package com.jiechu.jiechupro.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.YT46TjBean;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.YT46Api;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 运统46草拟稿及防护“三率”统计
 * Created by allen on 2017/9/12.
 */

public class YT46TJFrag extends RxFragment {

    @BindView(R.id.tv_piao_title)
    TextView tvPiaoTitle;
    @BindView(R.id.tv_piao_num)
    TextView tvPiaoNum;
    @BindView(R.id.tv_details_zzllyxm)
    TextView tvDetailsZzllyxm;
    @BindView(R.id.tv_details_rq)
    TextView tvDetailsRq;
    @BindView(R.id.tv_details_dj)
    TextView tvDetailsDj;
    @BindView(R.id.tv_details_xj)
    TextView tvDetailsXj;
    @BindView(R.id.tv_details_sgfzrqz)
    TextView tvDetailsSgfzrqz;
    @BindView(R.id.tv_details_fhdjzycs)
    TextView tvDetailsFhdjzycs;
    @BindView(R.id.tv_details_sjyglccs)
    TextView tvDetailsSjyglccs;
    @BindView(R.id.tv_details_xdcs)
    TextView tvDetailsXdcs;
    @BindView(R.id.tv_details_sjsdcs)
    TextView tvDetailsSjsdcs;
    @BindView(R.id.tv_details_bxlccs)
    TextView tvDetailsBxlccs;
    @BindView(R.id.tv_details_gjlccs)
    TextView tvDetailsGjlccs;
    @BindView(R.id.tv_details_fhdjzycs2)
    TextView tvDetailsFhdjzycs2;
    @BindView(R.id.tv_details_sjyglccs2)
    TextView tvDetailsSjyglccs2;
    @BindView(R.id.tv_details_xdcs2)
    TextView tvDetailsXdcs2;
    @BindView(R.id.tv_details_sjsdcs2)
    TextView tvDetailsSjsdcs2;
    @BindView(R.id.tv_details_bxlccs2)
    TextView tvDetailsBxlccs2;
    @BindView(R.id.tv_details_gjlccs2)
    TextView tvDetailsGjlccs2;
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
        View view = inflater.inflate(R.layout.frag_yt_46, null);
        unbinder = ButterKnife.bind(this, view);
        //初始化视图
        initView();
        return view;
    }

    private void initView() {
        //标题
        tvPiaoTitle.setText("“运统46”草拟稿及防护“三率”统计表");
        //获取数据
        initData();
    }

    private void initData() {
        YT46Api api = new YT46Api(listener, this);
        api.setGzpid(id);
        api.setGqmc(gqmc);
        api.setGzpbh(gzpbh);
        api.setGzplb(gzplb);
        HttpManager.getInstance().fragmentConnToServer(api);
    }

    HttpOnNextListener<JSONObject> listener = new HttpOnNextListener<JSONObject>() {
        @Override
        public void onNext(JSONObject object) {
            if (object != null) {
                Log.d("结果", object.toString());
                try {
                    YT46TjBean bean = new YT46TjBean();
                    bean.setGqmc(!object.has("gqmc") ? gqmc : (object.getString("gqmc") + "").replace("null", ""));
                    bean.setGzpbh(!object.has("gzpbh") ? gzpbh : (object.getString("gzpbh") + "").replace("null", ""));
                    bean.setZzlly(!object.has("zzlly") ? "" : (object.getString("zzlly") + "").replace("null", ""));
                    bean.setFprq_app(!object.has("rq_app") ? "" : (object.getString("rq_app") + "").replace("null", ""));
                    bean.setDjnr(!object.has("djnr") ? "" : (object.getString("djnr") + "").replace("null", ""));
                    bean.setXjnr(!object.has("xjnr") ? "" : (object.getString("xjnr") + "").replace("null", ""));
                    bean.setFhbdjzycs1(!object.has("fhbdjzycs1") ? "" : (object.getString("fhbdjzycs1") + "").replace("null", ""));
                    bean.setSjyglccs1(!object.has("sjyglccs1") ? "" : (object.getString("sjyglccs1") + "").replace("null", ""));
                    bean.setTqxdcs1(!object.has("tqxdcs1") ? "" : (object.getString("tqxdcs1") + "").replace("null", ""));
                    bean.setTgbxlccs1(!object.has("tgbxlccs1") ? "" : (object.getString("tgbxlccs1") + "").replace("null", ""));
                    bean.setTgzyddgjlccs1(!object.has("tgzyddgjlccs1") ? "" : (object.getString("tgzyddgjlccs1") + "").replace("null", ""));
                    bean.setFhbdjzycs2(!object.has("fhbdjzycs2") ? "" : (object.getString("fhbdjzycs2") + "").replace("null", ""));
                    bean.setSjyglccs2(!object.has("sjyglccs2") ? "" : (object.getString("sjyglccs2") + "").replace("null", ""));
                    bean.setTqxdcs2(!object.has("tqxdcs2") ? "" : (object.getString("tqxdcs2") + "").replace("null", ""));
                    bean.setSjsdzycs2(!object.has("sjsdzycs2") ? "" : (object.getString("sjsdzycs2") + "").replace("null", ""));
                    //设置数据
                    setData(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void setData(YT46TjBean bean) {
        //工区名称
        tvGqTitle.setText(bean.getGqmc());
        //工作票编号
        tvPiaoNum.setText("第" + bean.getGzpbh() + "号");
        //驻站联络员姓名
        tvDetailsZzllyxm.setText(bean.getZzlly());
        //日期
        tvDetailsRq.setText(bean.getFprq_app());
        //登记
        tvDetailsDj.setText(bean.getDjnr());
        //销记
        tvDetailsXj.setText(bean.getXjnr());
        //防护薄登记作业次数
        tvDetailsFhdjzycs.setText(bean.getFhbdjzycs1());
        //实际预告列车次数
        tvDetailsSjyglccs.setText(bean.getSjyglccs1());
        //提前10分钟下道次数
        tvDetailsXdcs.setText(bean.getTqxdcs1());
        //通过本线（邻线）列车次数
        tvDetailsBxlccs.setText(bean.getTgbxlccs1());
        //通过作业地点关键列车次数
        tvDetailsGjlccs.setText(bean.getTgzyddgjlccs1());
        //防护薄登记作业次数
        tvDetailsFhdjzycs2.setText(bean.getFhbdjzycs2());
        //实际预告列车次数id
        tvDetailsSjyglccs2.setText(bean.getSjyglccs2());
        //提前10分钟下道次数
        tvDetailsXdcs2.setText(bean.getTqxdcs2());
        //实际上道作业次数
        tvDetailsSjsdcs2.setText(bean.getSjsdzycs2());
        //通过本线（邻线）列车次数
        tvDetailsBxlccs2.setText(bean.getTgbxlccs1());
        //通过作业地点关键列车次数
        tvDetailsGjlccs2.setText(bean.getTgzyddgjlccs1());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
