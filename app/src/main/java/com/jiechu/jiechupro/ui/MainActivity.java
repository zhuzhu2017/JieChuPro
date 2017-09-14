package com.jiechu.jiechupro.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.JieChuApp;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.adapter.LineAdapter;
import com.jiechu.jiechupro.adapter.PlantsAdapter;
import com.jiechu.jiechupro.adapter.WSAdapter;
import com.jiechu.jiechupro.model.MainFilterBean;
import com.jiechu.jiechupro.utils.CommonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.lv_line)
    RecyclerView lvLine;
    @BindView(R.id.lv_plant)
    RecyclerView lvPlant;
    @BindView(R.id.lv_workstation)
    RecyclerView lvWorkstation;

    private Context context;
    private LineAdapter lineAdapter;    //线路Adapter
    private PlantsAdapter plantsAdapter;    //车间Adapter
    private WSAdapter wsAdapter;    //工区Adapter
    private MainFilterBean filterBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        ButterKnife.bind(this);
        //设置布局管理器
        LinearLayoutManager lvLineLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager lvPlantLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager lvWorkstationLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lvLine.setLayoutManager(lvLineLayoutManager);
        lvPlant.setLayoutManager(lvPlantLayoutManager);
        lvWorkstation.setLayoutManager(lvWorkstationLayoutManager);
        tvHeaderTitle.setText("首页");
        //初始化数据
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置数据
        setFilterData();
    }

    /**
     * 获取筛选数据
     */
    private void initData() {
        String main = CommonUtil.readAssetsString(this, "main");
        try {
            JSONObject object = new JSONObject(main);
            Gson gson = new Gson();
            filterBean = gson.fromJson(object.toString(), MainFilterBean.class);
            //设置数据
            setFilterData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置筛选数据
     */
    private void setFilterData() {
        if (filterBean == null) return;
        final List<MainFilterBean.LinesBean> lines = filterBean.getLines();
        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).setLineSelected(false);
        }
        lvPlant.setVisibility(View.GONE);
        lvWorkstation.setVisibility(View.GONE);
        lineAdapter = new LineAdapter(context, lines);
        lvLine.setAdapter(lineAdapter);
        //条目点击的监听
        lineAdapter.setIOnLinesItemClickedListener(new LineAdapter.IOnLinesItemClickedListener() {
            @Override
            public void onItemClicked(View view, int position) {
                //线路选中状态设置
                MainFilterBean.LinesBean linesBean = lines.get(position);
                for (int i = 0; i < lines.size(); i++) {
                    lines.get(i).setLineSelected(false);
                }
                boolean lineSelected = linesBean.isLineSelected();
                if (lineSelected) {
                    linesBean.setLineSelected(false);
                } else {
                    linesBean.setLineSelected(true);
                }
                lineAdapter.updataList(lines);
                //选中线路后设置对应工作区车间的数据
                final List<MainFilterBean.LinesBean.PlantsBean> plants = linesBean.getPlants();
                if (plants != null && plants.size() > 0) {
                    lvPlant.setVisibility(View.VISIBLE);
                    //初始化都不选中
                    for (int i = 0; i < plants.size(); i++) {
                        plants.get(i).setPlantsSelected(false);
                    }
                    plantsAdapter = new PlantsAdapter(context, plants);
                    lvPlant.setAdapter(plantsAdapter);
                    //添加条目点击的监听
                    plantsAdapter.setIOnPlantsItemClickedListener(new PlantsAdapter.IOnPlantsItemClickedListener() {
                        @Override
                        public void onItemClicked(View view, int position) {
                            //车间选中状态设置
                            MainFilterBean.LinesBean.PlantsBean plantsBean = plants.get(position);
                            for (int i = 0; i < plants.size(); i++) {
                                plants.get(i).setPlantsSelected(false);
                            }
                            boolean plantsSelected = plantsBean.isPlantsSelected();
                            if (plantsSelected) {
                                plantsBean.setPlantsSelected(false);
                            } else {
                                plantsBean.setPlantsSelected(true);
                            }
                            plantsAdapter.updataList(plants);
                            //选中车间后对应的工作区数据
                            final List<MainFilterBean.LinesBean.PlantsBean.WorkstationBean> workstationList = plantsBean.getWorkstation();
                            if (workstationList != null && workstationList.size() > 0) {
                                lvWorkstation.setVisibility(View.VISIBLE);
                                //初始化都不选中
                                for (int i = 0; i < workstationList.size(); i++) {
                                    workstationList.get(i).setWTSelected(false);
                                }
                                wsAdapter = new WSAdapter(context, workstationList);
                                lvWorkstation.setAdapter(wsAdapter);
                                //添加条目选中的监听
                                wsAdapter.setIOnWSItemClickedListener(new WSAdapter.IOnWSItemClickedListener() {
                                    @Override
                                    public void onItemClicked(View view, int position) {
                                        //设置选中状态
                                        MainFilterBean.LinesBean.PlantsBean.WorkstationBean workstationBean = workstationList.get(position);
                                        for (int i = 0; i < workstationList.size(); i++) {
                                            workstationList.get(i).setWTSelected(false);
                                        }
                                        boolean wtSelected = workstationBean.isWTSelected();
                                        if (wtSelected) {
                                            workstationBean.setWTSelected(false);
                                        } else {
                                            workstationBean.setWTSelected(true);
                                        }
                                        wsAdapter.updataList(workstationList);
                                        String wname = workstationBean.getWname();
                                        if (TextUtils.equals(wname, "洛阳网工区")) {
                                            //选中条目直接跳转
//                                            Bundle bundle = new Bundle();
//                                            bundle.putString("workstation", workstationBean.getWname());
                                            openActivity(TongJiActivity.class);
                                        } else {
                                            Toast.makeText(MainActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                lvWorkstation.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    lvPlant.setVisibility(View.GONE);
                    lvWorkstation.setVisibility(View.GONE);
                    Toast.makeText(context, "暂无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(context, "再点一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            JieChuApp.getInstance().exit();
        }
    }

    long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(context, "再点一次退出应用", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                JieChuApp.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
