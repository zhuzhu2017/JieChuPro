package com.jiechu.jiechupro.utils;

import android.text.TextUtils;

import com.jiechu.jiechupro.model.ZYZCYBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据处理工具类
 * Created by allen on 2017/9/18.
 */

public class DataUtil {

    /**
     * 筛选司机
     *
     * @param zyzcyArray
     * @param sj_select
     * @return
     */
    public static List<ZYZCYBean> filterSj(JSONArray zyzcyArray, String sj_select) throws JSONException {
        if (zyzcyArray == null) return null;
        //将筛选条件处理成数组
        String[] strings = changeStringToArray(sj_select);
        if (strings == null) return null;
        List<ZYZCYBean> beanList = new ArrayList<>();
        for (int i = 0; i < zyzcyArray.length(); i++) {
            JSONObject itemObject = zyzcyArray.getJSONObject(i);
            String cyid = itemObject.getString("cyid");
            for (String string : strings) {
                if (TextUtils.equals(cyid, string)) {
                    ZYZCYBean bean = new ZYZCYBean();
                    bean.setCyid(cyid);
                    bean.setAqdj(itemObject.getString("aqdj"));
                    bean.setCymc(itemObject.getString("cyxm"));
                    beanList.add(bean);
                }
            }
        }
        return beanList;
    }

    /**
     * 筛选作业组成员
     *
     * @param zyzcyArray
     * @param zyzcy_list
     * @return
     */
    public static List<ZYZCYBean> filterZyzcy(JSONArray zyzcyArray, String zyzcy_list) throws JSONException {
        if (zyzcyArray == null) return null;
        //将筛选条件处理成数组
        String[] strings = changeStringToArray(zyzcy_list);
        if (strings == null) return null;
        List<ZYZCYBean> beanList = new ArrayList<>();
        for (int i = 0; i < zyzcyArray.length(); i++) {
            JSONObject itemObject = zyzcyArray.getJSONObject(i);
            String cyid = itemObject.getString("cyid");
            for (String string : strings) {
                if (TextUtils.equals(cyid, string)) {
                    ZYZCYBean bean = new ZYZCYBean();
                    bean.setCyid(cyid);
                    bean.setAqdj(itemObject.getString("aqdj"));
                    bean.setCymc(itemObject.getString("cyxm"));
                    beanList.add(bean);
                }
            }
        }
        return beanList;
    }

    /**
     * 筛选其他作业组成员
     *
     * @param zyzcyArray
     * @param wbcy_list
     * @return
     */
    public static List<ZYZCYBean> filterWbcy(JSONArray zyzcyArray, String wbcy_list) throws JSONException {
        if (zyzcyArray == null) return null;
        //将筛选条件处理成数组
        String[] strings = changeStringToArray(wbcy_list);
        if (strings == null) return null;
        List<ZYZCYBean> beanList = new ArrayList<>();
        for (int i = 0; i < zyzcyArray.length(); i++) {
            JSONObject itemObject = zyzcyArray.getJSONObject(i);
            String cyid = itemObject.getString("cyid");
            for (String string : strings) {
                if (TextUtils.equals(cyid, string)) {
                    ZYZCYBean bean = new ZYZCYBean();
                    bean.setCyid(cyid);
                    bean.setAqdj(itemObject.getString("aqdj"));
                    bean.setCymc(itemObject.getString("cyxm"));
                    beanList.add(bean);
                }
            }
        }
        return beanList;
    }

    /**
     * 生成数组
     *
     * @param select 数组
     * @return 数组
     */
    private static String[] changeStringToArray(String select) {
        if (select == null) return null;
        return select.split(",");
    }

}
