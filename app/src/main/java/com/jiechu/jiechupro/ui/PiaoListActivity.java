package com.jiechu.jiechupro.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.PiaoListApi;

import org.json.JSONObject;

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
    ListView lvPiao;

    private String searchWord;  //搜索词
    private int page = 1;   //当前页

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            searchWord = bundle.getString("search_word");
        }
        //初始化视图
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //设置标题
        tvHeaderTitle.setText("工作票列表");
        //初始化数据
        initData(false);
    }

    /**
     * 初始化数据
     */
    private void initData(boolean isLoadMore) {
        PiaoListApi piaoApi = new PiaoListApi(piaoListListener, this);
        piaoApi.setRows("15");
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
     * 监听回调
     */
    HttpOnNextListener piaoListListener = new HttpOnNextListener<JSONObject>() {

        @Override
        public void onNext(JSONObject object) {
            Log.d("工作票列表", object.toString());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.d("工作票列表错误", e.getMessage());
        }
    };

    @OnClick({R.id.iv_header_back, R.id.iv_search, R.id.ll_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.iv_search:
                break;
            case R.id.ll_refresh:
                break;
        }
    }
}
