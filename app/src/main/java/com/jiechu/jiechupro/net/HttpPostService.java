package com.jiechu.jiechupro.net;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by allen on 2017/9/12.
 */

public interface HttpPostService {
    /**
     * 登陆
     *
     * @return
     */
    @POST("CheckLogin")
    Observable<JSONObject> login(@Body JSONObject object);

}
