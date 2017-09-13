package com.jiechu.jiechupro.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.R;

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
    private String searchWord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            searchWord = bundle.getString("search_word");
        }
    }

    @OnClick({R.id.iv_header_back, R.id.iv_search, R.id.ll_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                break;
            case R.id.iv_search:
                break;
            case R.id.ll_refresh:
                break;
        }
    }
}
