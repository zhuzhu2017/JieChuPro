package com.jiechu.jiechupro.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.adapter.PiaoListAdapter;
import com.jiechu.jiechupro.model.PiaoListBean;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.PiaoListApi;
import com.jiechu.jiechupro.view.recyclerview.AutoLoad.AutoLoadRecyclerView;
import com.jiechu.jiechupro.view.recyclerview.HeaderAndFooter.OnItemClickListener;
import com.jiechu.jiechupro.view.recyclerview.LayoutManager.WZMLinearLayoutManager;
import com.jiechu.jiechupro.view.recyclerview.PullToLoad.OnLoadListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工作票列表页面
 * Created by allen on 2017/9/11.
 */

public class PiaoListActivity extends BaseActivity {

    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.ll_refresh)
    LinearLayout llRefresh;
    @BindView(R.id.lv_piao)
    AutoLoadRecyclerView lvPiao;

    private Activity ctx;
    private String searchWord;  //搜索词
    private int page = 1;   //当前页
    private List<PiaoListBean> piaoList = new ArrayList<>();    //工作票集合
    private boolean isLoadMore; //是否上啦来加载
    private PiaoListAdapter adapter;    //数据填充器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        ctx = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            searchWord = bundle.getString("search_word");
        }
        if (piaoList.size() > 0)
            piaoList.clear();
        isLoadMore = false;
        //初始化视图
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //设置标题
        tvHeaderTitle.setText("工作票列表");
        //设置列表布局管理器
        WZMLinearLayoutManager layoutManager = new WZMLinearLayoutManager();
        lvPiao.setLayoutManager(layoutManager);
        //设置下拉刷新不可用
        lvPiao.setRefreshEnable(false);
        //初始化数据
        initData();
        //设置监听
        initListener();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        PiaoListApi piaoApi = new PiaoListApi(piaoListListener, this);
        piaoApi.setRows("20");
        if (isLoadMore) {
            page++;
        } else {
            page = 1;
        }
        piaoApi.setPage(String.valueOf(page));
        piaoApi.setKeyword(searchWord == null ? "" : searchWord);
        HttpManager.getInstance().connToServer(piaoApi);
    }

    /**
     * 设置监听
     */
    private void initListener() {
        lvPiao.setOnLoadListener(new OnLoadListener() {
            @Override
            public void onStartLoading(int skip) {
                isLoadMore = true;
                initData();
            }
        });
    }

    /**
     * 监听回调
     */
    HttpOnNextListener piaoListListener = new HttpOnNextListener<JSONObject>() {

        @Override
        public void onNext(JSONObject object) {
            if (object != null) {
                try {
                    JSONArray rows = object.getJSONArray("rows");
                    if (!isLoadMore) {
                        if (rows != null && rows.length() > 0) {
                            lvPiao.setVisibility(View.VISIBLE);
                            for (int i = 0; i < rows.length(); i++) {
                                JSONObject itemObject = rows.getJSONObject(i);
                                if (itemObject != null) {
                                    PiaoListBean listBean = new PiaoListBean();
                                    int i1 = i + 1;
                                    if (i1 < 10) {
                                        listBean.setXh("00" + i1);
                                    } else if (i1 >= 10 && i1 < 100) {
                                        listBean.setXh("0" + i1);
                                    } else {
                                        listBean.setXh("" + i1);
                                    }
                                    listBean.setDdlx(itemObject.getString("ddlx") + "");
                                    listBean.setFpr(itemObject.getString("fpr") + "");
                                    listBean.setFprq(itemObject.getString("fprq_app") + "");
                                    listBean.setGq(itemObject.getString("gqmc") + "");
                                    listBean.setLdr(itemObject.getString("gzldr") + "");
                                    listBean.setPh(itemObject.getString("gzpbh") + "");
                                    listBean.setYxq(itemObject.getString("gzpyxq_list") + "");
                                    listBean.setZt(itemObject.getString("zt_desc") + "");
                                    listBean.setZydd(itemObject.getString("zydd") + "");
                                    listBean.setZynr(itemObject.getString("zynr") + "");
                                    listBean.setId(itemObject.has("gzpid") ? (itemObject.getString("gzpid") + "") :
                                            itemObject.getString("id") + "");
                                    listBean.setGzplb(itemObject.has("gzplb") ? itemObject.getString("gzplb") : "1");
                                    piaoList.add(listBean);
                                }
                            }
                        } else {
                            lvPiao.setVisibility(View.GONE);
                        }
                    } else {
                        if (rows != null) {
                            int orderNum = piaoList.size();
                            if (rows.length() > 0) {
                                for (int i = 0; i < rows.length(); i++) {
                                    JSONObject itemObject = rows.getJSONObject(i);
                                    if (itemObject != null) {
                                        PiaoListBean listBean = new PiaoListBean();
                                        int i1 = orderNum + i + 1;
                                        if (i1 < 10) {
                                            listBean.setXh("00" + i);
                                        } else if (i1 >= 10 && i1 < 100) {
                                            listBean.setXh("0" + i);
                                        } else {
                                            listBean.setXh("" + i1);
                                        }
                                        listBean.setDdlx(itemObject.getString("ddlx") + "");
                                        listBean.setFpr(itemObject.getString("fpr") + "");
                                        listBean.setFprq(itemObject.getString("fprq_app") + "");
                                        listBean.setGq(itemObject.getString("gqmc") + "");
                                        listBean.setLdr(itemObject.getString("gzldr") + "");
                                        listBean.setPh(itemObject.getString("gzpbh") + "");
                                        listBean.setYxq(itemObject.getString("gzpyxq_list") + "");
                                        listBean.setZt(itemObject.getString("zt_desc") + "");
                                        listBean.setZydd(itemObject.getString("zydd") + "");
                                        listBean.setZynr(itemObject.getString("zynr") + "");
                                        listBean.setId(itemObject.has("gzpid") ? (itemObject.getString("gzpid") + "") :
                                                itemObject.getString("id") + "");
                                        listBean.setGzplb(itemObject.has("gzplb") ? itemObject.getString("gzplb") : "");
                                        piaoList.add(listBean);
                                    }
                                }
                                lvPiao.completeLoad();
                            } else {
                                lvPiao.setNoMore(true);
                            }
                        }
                    }
                    //设置列表数据
                    setListData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 设置列表数据
     */
    private void setListData() {
        if (adapter == null) {
            adapter = new PiaoListAdapter(ctx, piaoList);
            lvPiao.setAdapter(adapter);
        } else {
            adapter.updateList(piaoList);
        }
        lvPiao.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", piaoList.get(position).getId());
                bundle.putString("gzplb", piaoList.get(position).getGzplb());
                bundle.putString("gzpbh", piaoList.get(position).getPh());
                bundle.putString("gqmc", piaoList.get(position).getGq());
                openActivity(PiaoListDetailsActivity.class, bundle);
            }
        });
    }

    @OnClick({R.id.iv_header_back, R.id.iv_search, R.id.ll_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.ll_refresh:
            case R.id.iv_search:
                String word = etSearch.getText().toString();
                if (TextUtils.isEmpty(word)) {
                    searchWord = "";
                } else {
                    searchWord = word;
                }
                isLoadMore = false;
                initData();
                break;
        }
    }
}
