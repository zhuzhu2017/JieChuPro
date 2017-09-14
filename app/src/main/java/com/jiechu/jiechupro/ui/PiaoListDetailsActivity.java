package com.jiechu.jiechupro.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.ui.fragment.GQYBHJSGHJLFrag;
import com.jiechu.jiechupro.ui.fragment.PiaoDetailsFrag;
import com.jiechu.jiechupro.ui.fragment.SwitchFrag;
import com.jiechu.jiechupro.ui.fragment.TDZYMLPFrag;
import com.jiechu.jiechupro.ui.fragment.XCPicFrag;
import com.jiechu.jiechupro.ui.fragment.YT46TJFrag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工作票列表详情
 * Created by allen on 2017/9/12.
 */

public class PiaoListDetailsActivity extends BaseActivity implements SwitchFrag.IOnTitleSelectedListener {
    @BindView(R.id.drawer_content)
    FrameLayout drawerContent;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tv_menu)
    TextView tvMenu;
    @BindView(R.id.tv_details_header_title)
    TextView tvDetailsHeaderTitle;
    @BindView(R.id.tv_back)
    TextView tvBack;

    private Activity ctx;

    private String id;  //主键id
    private PiaoDetailsFrag piaoDetailsFrag;    //第一种工作票
    //    private TDZYFGDFrag tdzyfgdFrag;    //停电作业分工单
    private GQYBHJSGHJLFrag gqybhjsghjlFrag;    //工前预备会及收工会记录
    private TDZYMLPFrag tdzymlpFrag;    //停电作业命令票
    private YT46TJFrag yt46TJFrag;  //运统46草拟稿及防护“三率”统计
    private XCPicFrag xcPicFrag;    //作业现场照片浏览
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        ButterKnife.bind(this);
        ctx = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("id");
        }
        //初始化视图
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //设置侧滑布局
        SwitchFrag switchFrag = new SwitchFrag();
        getSupportFragmentManager().beginTransaction().add(R.id.drawer_content, switchFrag).commit();
        //初始化Fragment显示
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (piaoDetailsFrag == null) {
            piaoDetailsFrag = new PiaoDetailsFrag();
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            piaoDetailsFrag.setArguments(bundle);
            fragmentList.add(piaoDetailsFrag);
            fragmentTransaction.add(R.id.fragment_container, piaoDetailsFrag);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onTitleSelected(View view, int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        try {
            switch (position) {
                case 0: //第一种工作票
                    if (piaoDetailsFrag == null) {
                        piaoDetailsFrag = new PiaoDetailsFrag();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        piaoDetailsFrag.setArguments(bundle);
                        fragmentList.add(piaoDetailsFrag);
                        fragmentTransaction.add(R.id.fragment_container, piaoDetailsFrag);
                    }
                    for (Fragment ele : fragmentList) {
                        fragmentTransaction.hide(ele);
                    }
                    fragmentTransaction.show(piaoDetailsFrag);
                    break;
//                case 1: //停电作业分工单
//                    if (tdzyfgdFrag == null) {
//                        tdzyfgdFrag = new TDZYFGDFrag();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("id", id);
//                        tdzyfgdFrag.setArguments(bundle);
//                        fragmentList.add(tdzyfgdFrag);
//                        fragmentTransaction.add(R.id.fragment_container, tdzyfgdFrag);
//                    }
//                    for (Fragment ele : fragmentList) {
//                        fragmentTransaction.hide(ele);
//                    }
//                    fragmentTransaction.show(tdzyfgdFrag);
//                    break;
                case 1: //工前预备会及收工会记录
                    if (gqybhjsghjlFrag == null) {
                        gqybhjsghjlFrag = new GQYBHJSGHJLFrag();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        gqybhjsghjlFrag.setArguments(bundle);
                        fragmentList.add(gqybhjsghjlFrag);
                        fragmentTransaction.add(R.id.fragment_container, gqybhjsghjlFrag);
                    }
                    for (Fragment ele : fragmentList) {
                        fragmentTransaction.hide(ele);
                    }
                    fragmentTransaction.show(gqybhjsghjlFrag);
                    break;
                case 2: //停电作业命令票
                    if (tdzymlpFrag == null) {
                        tdzymlpFrag = new TDZYMLPFrag();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        tdzymlpFrag.setArguments(bundle);
                        fragmentList.add(tdzymlpFrag);
                        fragmentTransaction.add(R.id.fragment_container, tdzymlpFrag);
                    }
                    for (Fragment ele : fragmentList) {
                        fragmentTransaction.hide(ele);
                    }
                    fragmentTransaction.show(tdzymlpFrag);
                    break;
                case 3: // "运统46草拟稿及防护“三率”统计", "作业现场照片浏览"};
                    if (yt46TJFrag == null) {
                        yt46TJFrag = new YT46TJFrag();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        yt46TJFrag.setArguments(bundle);
                        fragmentList.add(yt46TJFrag);
                        fragmentTransaction.add(R.id.fragment_container, yt46TJFrag);
                    }
                    for (Fragment ele : fragmentList) {
                        fragmentTransaction.hide(ele);
                    }
                    fragmentTransaction.show(yt46TJFrag);
                    break;
                case 4: //作业现场照片浏览
                    if (xcPicFrag == null) {
                        xcPicFrag = new XCPicFrag();
                        Bundle bundle = new Bundle();
                        bundle.putString("id", id);
                        xcPicFrag.setArguments(bundle);
                        fragmentList.add(xcPicFrag);
                        fragmentTransaction.add(R.id.fragment_container, xcPicFrag);
                    }
                    for (Fragment ele : fragmentList) {
                        fragmentTransaction.hide(ele);
                    }
                    fragmentTransaction.show(xcPicFrag);
                    break;
                default:
                    break;
            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv_menu, R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_menu:  //打开侧滑菜单
                drawerLayout.openDrawer(drawerContent);
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
