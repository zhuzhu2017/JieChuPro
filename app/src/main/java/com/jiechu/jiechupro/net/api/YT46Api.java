package com.jiechu.jiechupro.net.api;

import android.util.Log;

import com.jiechu.jiechupro.net.BaseApi;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.HttpPostService;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by allen on 2017/9/14.
 */

public class YT46Api extends BaseApi {

    private String gzpid;
    private String gzplb;
    private String gzpbh;
    private String gqmc;

    public String getGzpid() {
        return gzpid;
    }

    public void setGzpid(String gzpid) {
        this.gzpid = gzpid;
    }

    public String getGzplb() {
        return gzplb;
    }

    public void setGzplb(String gzplb) {
        this.gzplb = gzplb;
    }

    public String getGzpbh() {
        return gzpbh;
    }

    public void setGzpbh(String gzpbh) {
        this.gzpbh = gzpbh;
    }

    public String getGqmc() {
        return gqmc;
    }

    public void setGqmc(String gqmc) {
        this.gqmc = gqmc;
    }

    public YT46Api(HttpOnNextListener listener, RxFragment rxFragment) {
        super(listener, rxFragment);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        JSONObject object = new JSONObject();
        try {
            object.put("gzpid", getGzpid());
            object.put("gzplb", getGzplb());
            object.put("gzpbh", getGzpbh());
            object.put("gqmc", getGqmc());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("参数", object.toString());
        return httpPostService.getYT46Data(object);
    }

    @Override
    public Object call(Object o) {
        return o;
    }
}
