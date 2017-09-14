package com.jiechu.jiechupro.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.model.GQYBHJSGHJLBean;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.SGHJLApi;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 工前预备会及收工会记录
 * Created by allen on 2017/9/12.
 */

public class GQYBHJSGHJLFrag extends RxFragment {

    @BindView(R.id.tv_piao_num)
    TextView tvPiaoNum;
    @BindView(R.id.tv_details_gqyx)
    TextView tvDetailsGqyx;
    @BindView(R.id.tv_details_fgjl)
    TextView tvDetailsFgjl;
    @BindView(R.id.tv_details_rwly)
    TextView tvDetailsRwly;
    @BindView(R.id.tv_details_sgjl)
    TextView tvDetailsSgjl;
    @BindView(R.id.tv_details_qt)
    TextView tvDetailsQt;
    @BindView(R.id.tv_details_xdmlh)
    TextView tvDetailsXdmlh;
    @BindView(R.id.tv_details_sgjlldr)
    TextView tvDetailsSgjlldr;
    Unbinder unbinder;
    @BindView(R.id.tv_gq_name)
    TextView tvGqName;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_details_xdmlsj)
    TextView tvDetailsXdmlsj;

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
        View view = inflater.inflate(R.layout.frag_gqyb, null);
        unbinder = ButterKnife.bind(this, view);
        //初始化视图
        initView();
        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //标题
        tvTopTitle.setText("接触网工前预备会及收工会记录（停电作业）");
        //获取数据
        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        SGHJLApi api = new SGHJLApi(listener, this);
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
                try {
                    GQYBHJSGHJLBean bean = new GQYBHJSGHJLBean();
                    bean.setGqmc((object.getString("gqmc") + "").replace("null", ""));
                    bean.setFprq_app((object.getString("fprq_app") + "").replace("null", ""));
                    bean.setGzpbh((object.getString("gzpbh") + "").replace("null", ""));
                    bean.setBqyx((object.getString("bqyx") + "").replace("null", ""));
                    bean.setFgjl((object.getString("fgjl") + "").replace("null", ""));
                    bean.setRwlyjwcqk_rwly((object.getString("rwlyjwcqk_rwly") + "").replace("null", ""));
                    bean.setRwlyjwcqk_wcqk((object.getString("rwlyjwcqk_wcqk") + "").replace("null", ""));
                    bean.setRwlyjwcqk_qt((object.getString("rwlyjwcqk_qt") + "").replace("null", ""));
                    bean.setXdmlh((object.getString("xdmlh") + "").replace("null", ""));
                    bean.setXdmlsj((object.getString("xdmlsj") + "").replace("null", ""));
                    bean.setGzldr((object.getString("gzldr") + "").replace("null", ""));
                    //设置数据
                    setData(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 设置数据
     *
     * @param bean
     */
    private void setData(GQYBHJSGHJLBean bean) {
        //发票日期
        tvGqName.setText(bean.getFprq_app());
        //工作票编号
        tvPiaoNum.setText(bean.getGzpbh());
        //工前预想
        tvDetailsGqyx.setText(bean.getBqyx());
        //分工记录
        tvDetailsFgjl.setText(bean.getFgjl());
        //任务来源
        tvDetailsRwly.setText(bean.getRwlyjwcqk_rwly());
        //收工记录
        tvDetailsSgjl.setText(bean.getRwlyjwcqk_wcqk());
        //其他
        tvDetailsQt.setText(bean.getRwlyjwcqk_qt());
        //行调命令号
        tvDetailsXdmlh.setText(bean.getXdmlh());
        //行调命令时间
        tvDetailsXdmlsj.setText(bean.getXdmlsj());
        //工作领导人
        tvDetailsSgjlldr.setText(bean.getGzldr());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
