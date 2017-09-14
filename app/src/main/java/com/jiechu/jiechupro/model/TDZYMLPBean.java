package com.jiechu.jiechupro.model;

import java.io.Serializable;

/**
 * Created by allen on 2017/9/14.
 */

public class TDZYMLPBean implements Serializable {
    //工区名称
    private String gqmc;
    //工作票编号
    private String gzpbh;
    //命令编号
    private String mlbh;
    //批准时间
    private String pzsj_app;
    //命令内容
    private String mlnr;
    //要求完成时间
    private String wcsj_app;
    //发令人
    private String flr;
    //受令人
    private String slr;
    //销令时间
    private String xlsj_app;
    //销令人
    private String xlr;
    //供电调度员
    private String gdddy;

    public String getGqmc() {
        return gqmc;
    }

    public void setGqmc(String gqmc) {
        this.gqmc = gqmc;
    }

    public String getGzpbh() {
        return gzpbh;
    }

    public void setGzpbh(String gzpbh) {
        this.gzpbh = gzpbh;
    }

    public String getMlbh() {
        return mlbh;
    }

    public void setMlbh(String mlbh) {
        this.mlbh = mlbh;
    }

    public String getPzsj_app() {
        return pzsj_app;
    }

    public void setPzsj_app(String pzsj_app) {
        this.pzsj_app = pzsj_app;
    }

    public String getMlnr() {
        return mlnr;
    }

    public void setMlnr(String mlnr) {
        this.mlnr = mlnr;
    }

    public String getWcsj_app() {
        return wcsj_app;
    }

    public void setWcsj_app(String wcsj_app) {
        this.wcsj_app = wcsj_app;
    }

    public String getFlr() {
        return flr;
    }

    public void setFlr(String flr) {
        this.flr = flr;
    }

    public String getSlr() {
        return slr;
    }

    public void setSlr(String slr) {
        this.slr = slr;
    }

    public String getXlsj_app() {
        return xlsj_app;
    }

    public void setXlsj_app(String xlsj_app) {
        this.xlsj_app = xlsj_app;
    }

    public String getXlr() {
        return xlr;
    }

    public void setXlr(String xlr) {
        this.xlr = xlr;
    }

    public String getGdddy() {
        return gdddy;
    }

    public void setGdddy(String gdddy) {
        this.gdddy = gdddy;
    }
}
