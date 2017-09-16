package com.jiechu.jiechupro.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.adapter.HoriIconPicAdapter;
import com.jiechu.jiechupro.adapter.HoriPagerAdapter;
import com.jiechu.jiechupro.adapter.TopHoriMenuAdapter;
import com.jiechu.jiechupro.adapter.TopSonMenuAdapter;
import com.jiechu.jiechupro.model.XCPicBean;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.XCPicApi;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作业现场照片
 * Created by allen on 2017/9/12.
 */

public class XCPicFrag extends RxFragment implements TopHoriMenuAdapter.OnRecyclerViewItemClickListener {

    @BindView(R.id.top_hroi_menu)
    RecyclerView topHroiMenu;
    @BindView(R.id.tv_layout_switch)
    TextView tvLayoutSwitch;
    @BindView(R.id.top_second_menu)
    RecyclerView topSecondMenu;
    @BindView(R.id.vp_hori_pic_container)
    ViewPager vpHoriPicContainer;
    @BindView(R.id.iv_hori_left_arrow)
    ImageView ivHoriLeftArrow;
    @BindView(R.id.tv_hori_pic_count)
    TextView tvHoriPicCount;
    @BindView(R.id.lv_hori_icon_pics)
    RecyclerView lvHoriIconPics;
    @BindView(R.id.iv_hori_right_arrow)
    ImageView ivHoriRightArrow;
    @BindView(R.id.ll_hori_pic)
    LinearLayout llHoriPic;
    @BindView(R.id.vp_vertical_pic_container)
    ViewPager vpVerticalPicContainer;
    @BindView(R.id.tv_vertical_pic_count)
    TextView tvVerticalPicCount;
    @BindView(R.id.iv_vertical_up_arrow)
    ImageView ivVerticalUpArrow;
    @BindView(R.id.lv_vertical_icon_pics)
    RecyclerView lvVerticalIconPics;
    @BindView(R.id.iv_vertical_down_arrow)
    ImageView ivVerticalDownArrow;
    @BindView(R.id.ll_vertical_pic)
    LinearLayout llVerticalPic;
    private Activity ctx;
    Unbinder unbinder;
    private String id;
    //整体图片集合
    private List<XCPicBean> parentPicList = new ArrayList<>();

    private TopHoriMenuAdapter topHoriMenuAdapter;  //顶部横向菜单填充adapter
    private TopSonMenuAdapter topSonMenuAdapter;  //顶部子菜单填充adapter
    private HoriIconPicAdapter iconPicAdapter;  //横向图片索引填充adapter
    private HoriPagerAdapter horiPagerAdapter;  //横向大图View Pager

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = getActivity();
        Bundle arguments = getArguments();
        if (arguments != null) {
            id = arguments.getString("id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_xc_pic, null);
        unbinder = ButterKnife.bind(this, view);
        //初始化视图
        initView();
        return view;
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //设置布局管理器
        LinearLayoutManager topMenuLayoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager secondMenuLayoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager picsLayoutManager = new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false);
        //一级菜单横向
        topHroiMenu.setLayoutManager(topMenuLayoutManager);
        //二级菜单横向
        topSecondMenu.setLayoutManager(secondMenuLayoutManager);
        //图片缩略图默认横向
        lvHoriIconPics.setLayoutManager(picsLayoutManager);
        //获取数据
        initData();
    }

    private void initData() {
        XCPicApi api = new XCPicApi(listener, this);
        api.setGzpid(id);
        api.setMid("BUSINESS_DYZGZP");
        HttpManager.getInstance().fragmentConnToServer(api);
    }

    HttpOnNextListener<JSONObject> listener = new HttpOnNextListener<JSONObject>() {
        @Override
        public void onNext(JSONObject object) {
            if (object != null) {
                try {
                    JSONArray rows = object.getJSONArray("rows");
                    if (rows == null || rows.length() == 0) return;
                    for (int i = 0; i < rows.length(); i++) {
                        JSONObject itemObject = rows.getJSONObject(i);
                        //如果该条目连总大类名称都没有，直接跳出这次循环
                        if (!itemObject.has("Name") || itemObject.getString("Name") == null)
                            continue;
                        XCPicBean bean = new XCPicBean();
                        bean.setOutName(itemObject.getString("Name"));
                        List<XCPicBean.Pictures> picList = new ArrayList<>();
                        JSONArray pictures = itemObject.getJSONArray("Pictures");
                        for (int j = 0; j < pictures.length(); j++) {
                            JSONObject picObj = pictures.getJSONObject(j);
                            XCPicBean.Pictures pictureBean = new XCPicBean.Pictures();
                            pictureBean.setInnerName(picObj.has("Name") ? picObj.getString("Name") : "");
                            pictureBean.setUrl_CZXL(picObj.has("Url_CZXL") ? picObj.getString("Url_CZXL") : "");
                            pictureBean.setUrl_CZXL_ICON(picObj.has("Url_CZXL_ICON") ? picObj.getString("Url_CZXL_ICON") : "");
                            pictureBean.setUrl_SXL(picObj.has("Url_SXL") ? picObj.getString("Url_SXL") : "");
                            pictureBean.setUrl_SXL_ICON(picObj.has("Url_SXL_ICON") ? picObj.getString("Url_SXL_ICON") : "");
                            picList.add(pictureBean);
                        }
                        bean.setPicturesList(picList);
                        parentPicList.add(bean);
                    }
                    //设置数据
                    setData();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    };

    /**
     * 设置数据
     */
    private List<XCPicBean.Pictures> picturesList;

    private void setData() {
        //设置横向一级菜单,初始化选中第一个
        topHoriMenuAdapter = new TopHoriMenuAdapter(ctx, parentPicList);
        topHroiMenu.setAdapter(topHoriMenuAdapter);
        //添加点击事件
        topHoriMenuAdapter.setOnItemClickListener(this);
        //初始化子菜单
        if (parentPicList.size() > 0) {
            picturesList = parentPicList.get(0).getPicturesList();
        }
        //设置父菜单中数据显示
        setSonData();
    }

    private void setSonData() {
        //默认第一个选中
        if (picturesList != null && picturesList.size() > 0) {
            for (int i = 0; i < picturesList.size(); i++) {
                if (i == 0) {
                    picturesList.get(i).setSelected(true);
                } else {
                    picturesList.get(i).setSelected(false);
                }
            }
            topSonMenuAdapter = new TopSonMenuAdapter(ctx, picturesList);
            topSecondMenu.setAdapter(topSonMenuAdapter);
            //初始化横向索引
            iconPicAdapter = new HoriIconPicAdapter(ctx, picturesList);
            lvHoriIconPics.setAdapter(iconPicAdapter);
            //初始化ViewPager
            horiPagerAdapter = new HoriPagerAdapter(ctx, picturesList);
            vpHoriPicContainer.setAdapter(horiPagerAdapter);
            //初始化计数器
            tvHoriPicCount.setText("1/" + picturesList.size());
            //设置监听
            initListener();
        }
    }

    /**
     * 设置监听
     */
    private void initListener() {
        //ViewPager切换监听
        vpHoriPicContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (picturesList != null) {
                    //设置计数器显示
                    tvHoriPicCount.setText(position + 1 + "/" + picturesList.size());
                    //设置顶部二级菜单切换
                    for (int i = 0; i < picturesList.size(); i++) {
                        picturesList.get(i).setSelected(false);
                    }
                    picturesList.get(position).setSelected(true);
                    topSonMenuAdapter.upDateList(picturesList);
                    iconPicAdapter.updateList(picturesList);
                    lvHoriIconPics.smoothScrollToPosition(position);
                    topSecondMenu.smoothScrollToPosition(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //顶部二级菜单条目点击
        topSonMenuAdapter.setOnItemClickListener(new TopSonMenuAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //设置ViewPager选中
                vpHoriPicContainer.setCurrentItem(position, true);
            }
        });
        //横向图片索引条目点击
        iconPicAdapter.setIOnHoriIconClickedListener(new HoriIconPicAdapter.IOnHoriIconClickedListener() {
            @Override
            public void onItemClicked(View view, int position) {
                horiPos = position;
                //设置ViewPager选中
                vpHoriPicContainer.setCurrentItem(position, true);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int horiPos = 0;    //初始化在第一个

    @OnClick({R.id.tv_layout_switch, R.id.iv_hori_left_arrow, R.id.iv_hori_right_arrow, R.id.iv_vertical_up_arrow, R.id.iv_vertical_down_arrow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_layout_switch:
                break;
            case R.id.iv_hori_left_arrow:   //点击一次向左切换一个
                if (horiPos > 0) {
                    //设置View Pager选中状态
                    vpHoriPicContainer.setCurrentItem(horiPos - 1, true);
                    horiPos--;
                }
                break;
            case R.id.iv_hori_right_arrow:  //点击一次向右切换一个
                if (horiPos < picturesList.size() - 1) {
                    //设置View Pager选中状态
                    vpHoriPicContainer.setCurrentItem(horiPos + 1, true);
                    horiPos++;
                }
                break;
            case R.id.iv_vertical_up_arrow:
                break;
            case R.id.iv_vertical_down_arrow:
                break;
        }
    }

    private int oldPos;

    @Override
    public void onItemClick(View view, int position) {
        //刷新菜单选中状态
        if (topHoriMenuAdapter != null) topHoriMenuAdapter.changeSelected(position);
        if (oldPos != position) {
            //切换顶部父菜单时刷新整个布局数据
            if (parentPicList.get(position) != null) {
                picturesList = parentPicList.get(position).getPicturesList();
                setSonData();
            }
        }
        oldPos = position;
    }
}
