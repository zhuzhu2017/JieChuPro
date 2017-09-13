package com.jiechu.jiechupro.model;

import java.io.Serializable;

/**
 * 工作票列表条目数据Bean
 * Created by allen on 2017/9/13.
 */

public class PiaoListBean implements Serializable {
    private String xh;    //序号
    private String ph; //票号
    private String gq;  //工区
    private String zydd;    //作业地点
    private String zynr;    //作业内容
    private String ddlx;    //地点类型
    private String fpr; //发票人
    private String fprq;    //发票日期
    private String yxq; //有效期
    private String zt;  //状态
    private String ldr; //领导人
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getGq() {
        return gq;
    }

    public void setGq(String gq) {
        this.gq = gq;
    }

    public String getZydd() {
        return zydd;
    }

    public void setZydd(String zydd) {
        this.zydd = zydd;
    }

    public String getZynr() {
        return zynr;
    }

    public void setZynr(String zynr) {
        this.zynr = zynr;
    }

    public String getDdlx() {
        return ddlx;
    }

    public void setDdlx(String ddlx) {
        this.ddlx = ddlx;
    }

    public String getFpr() {
        return fpr;
    }

    public void setFpr(String fpr) {
        this.fpr = fpr;
    }

    public String getFprq() {
        return fprq;
    }

    public void setFprq(String fprq) {
        this.fprq = fprq;
    }

    public String getYxq() {
        return yxq;
    }

    public void setYxq(String yxq) {
        this.yxq = yxq;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getLdr() {
        return ldr;
    }

    public void setLdr(String ldr) {
        this.ldr = ldr;
    }
}
