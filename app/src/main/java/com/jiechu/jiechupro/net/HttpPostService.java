package com.jiechu.jiechupro.net;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
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

    @POST("CheckLogin")
    Call<JSONObject> loginTest(@Body JSONObject object);

    /**
     * 工作票统计
     *
     * @param object
     * @return
     */
    @POST("GetTop8GridJson")
    Observable<JSONObject> tongJi(@Body JSONObject object);

    /**
     * 获取第一种工作票列表
     *
     * @param object
     * @return
     */
    @POST("GetdyzgzpGridJsontosearch")
    Observable<JSONObject> piaoList(@Body JSONObject object);
    /**
     * 获取第一种工作票详情
     *
     * @param object
     * @return
     */
    @POST("GetDYZFormJson")
    Observable<JSONObject> getPiaoDetails(@Body JSONObject object);

    /**
     * 运统“46”
     *
     * @param object
     * @return
     */
    @POST("YT46GetFormJson")
    Observable<JSONObject> getYT46Data(@Body JSONObject object);

    /**
     * 第一种工作票-工前预备及收工会记录
     *
     * @param object
     * @return
     */
    @POST("SGHJLGetFormJson")
    Observable<JSONObject> getSGHJLData(@Body JSONObject object);

    /**
     * 第一种工作票-停电作业命令票
     *
     * @param object
     * @return
     */
    @POST("TDZYGetFormJson")
    Observable<JSONObject> getTDZYPData(@Body JSONObject object);

    /**
     * 现场照片
     *
     * @return
     */
    @POST("xczpGetFormJson")
    Observable<JSONObject> getXCPicData(@Query("gzpid") String gzpid, @Query("mid") String mid);


    @POST("xczpGetFormJson")
    Call<JSONObject> getXCZPData(@Query("gzpid") String gzpid, @Query("mid") String mid);

}
