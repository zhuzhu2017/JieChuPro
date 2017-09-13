package com.jiechu.jiechupro.net.api;

import com.jiechu.jiechupro.Constants;
import com.jiechu.jiechupro.net.BaseApi;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.HttpPostService;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 工作票列表Api
 * Created by allen on 2017/9/13.
 */

public class PiaoListApi extends BaseApi {

    private String rows;
    private String sidx;
    private String page;
    private String sord;
    private String keyword;

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public PiaoListApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setCanCancelProgress(true);
        setBaseUrl(Constants.BASE_URL);
        setConnTimeout(10);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        JSONObject object = new JSONObject();
        try {
            object.put("rows", getRows());
            object.put("sidx", "F_CreatorTime desc");
            object.put("page", getPage());
            object.put("sord", "asc");
            object.put("keyword", getKeyword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return httpPostService.piaoList(object);
    }

    @Override
    public Object call(Object o) {
        return o;
    }
}
