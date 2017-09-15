package com.jiechu.jiechupro.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.XCPicApi;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作业现场照片
 * Created by allen on 2017/9/12.
 */

public class XCPicFrag extends RxFragment {

    @BindView(R.id.top_hroi_menu)
    RecyclerView topHroiMenu;
    @BindView(R.id.top_second_menu)
    RecyclerView topSecondMenu;
    @BindView(R.id.vp_pic_container)
    ViewPager vpPicContainer;
    @BindView(R.id.tv_pic_sount)
    TextView tvPicSount;
    @BindView(R.id.lv_pics)
    RecyclerView lvPics;

    private Activity ctx;
    Unbinder unbinder;
    private String id;

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
        topHroiMenu.setLayoutManager(topMenuLayoutManager);
        topSecondMenu.setLayoutManager(secondMenuLayoutManager);
        lvPics.setLayoutManager(picsLayoutManager);
        //获取数据
        initData();
    }

    private void initData() {
        XCPicApi api = new XCPicApi(listener, this);
        api.setGzpid(id);
        api.setMid("BUSINESS_DYZGZP");
        HttpManager.getInstance().fragmentConnToServer(api);
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(10, TimeUnit.SECONDS);
//        builder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request()
//                        .newBuilder()
//                        .addHeader("Content-Type", "application/json; charset=utf-8")
//                        .addHeader("Cookie", JieChuApp.token)
//                        .build();
//                Log.d("图片请求", request.toString());
//                return chain.proceed(request);
//            }
//        });
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://" + Constants.BASE_IP + "/Api/")
//                .client(builder.build())
//                .addConverterFactory(JsonConverterFactory.create())
//                .build();
//        retrofit.create(HttpPostService.class)
//                .getXCZPData(id, "BUSINESS_DYZGZP")
//                .enqueue(new Callback<JSONObject>() {
//                    @Override
//                    public void onResponse(Call<JSONObject> call, retrofit2.Response<JSONObject> response) {
//                        Log.d("图片", response.toString() + "====" + response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<JSONObject> call, Throwable t) {
//
//                    }
//                });

    }

    HttpOnNextListener<JSONObject> listener = new HttpOnNextListener<JSONObject>() {
        @Override
        public void onNext(JSONObject object) {
            Log.d("图片", object.toString());
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.d("图片异常", e.getMessage());
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
