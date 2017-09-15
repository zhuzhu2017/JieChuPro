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
import com.jiechu.jiechupro.adapter.TopHoriMenuAdapter;
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
    private void setData() {
        //设置横向一级菜单
        topHoriMenuAdapter = new TopHoriMenuAdapter(ctx, parentPicList);
        topHroiMenu.setAdapter(topHoriMenuAdapter);
        //添加点击事件
        topHoriMenuAdapter.setOnItemClickListener(this);
        //先根据条件筛选数据
//        for (int i = 0; i < parentPicList.size(); i++) {
//            XCPicBean xcPicBean = parentPicList.get(i);
//            List<XCPicBean.Pictures> picturesList = xcPicBean.getPicturesList();
//            Map<XCPicBean.Pictures, Integer> picturesMap = new HashMap<>();
//            //将数据都存放到Map集合中
//            for (XCPicBean.Pictures pictureBean :
//                    picturesList) {
//                int j = 1;
//                if (picturesMap.get(pictureBean) != null) {
//                    j = picturesMap.get(pictureBean) + 1;
//                }
//                picturesMap.put(pictureBean, j);
//            }
//            //遍历HashMap,获取元素的信息和个数
//            Iterator<Map.Entry<XCPicBean.Pictures, Integer>> iterator = picturesMap.entrySet().iterator();
//            //循环
//            String textString = "";
//            while (iterator.hasNext()) {
//                Map.Entry<XCPicBean.Pictures, Integer> entry = iterator.next();
//                XCPicBean.Pictures picture = entry.getKey();
//                Integer integer = entry.getValue();
//                textString = picture.getInnerName() + "===" + integer + ";";
//                Log.d("筛选", textString);
//            }
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_layout_switch, R.id.iv_hori_left_arrow, R.id.iv_hori_right_arrow, R.id.iv_vertical_up_arrow, R.id.iv_vertical_down_arrow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_layout_switch:
                break;
            case R.id.iv_hori_left_arrow:
                break;
            case R.id.iv_hori_right_arrow:
                break;
            case R.id.iv_vertical_up_arrow:
                break;
            case R.id.iv_vertical_down_arrow:
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        //刷新菜单选中状态
        if (topHoriMenuAdapter != null) topHoriMenuAdapter.changeSelected(position);
        //TODO
    }
}
