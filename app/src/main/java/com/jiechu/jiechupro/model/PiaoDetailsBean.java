package com.jiechu.jiechupro.model;

import java.io.Serializable;

/**
 * 工作票详情
 * Created by allen on 2017/9/14.
 */

public class PiaoDetailsBean implements Serializable {
    //工区名称
    private String gqmc;
    //工作票编号
    private String gzpbh;
    //发票人
    private String fpr;
    //发票时间
    private String fprq_app;
    //安全等级
    private String gzldr_aqdj;
    //封锁范围
    private String fsfw;
    //作业范围（地点）
    private String zydd;
    //作业内容
    private String zynr;
    //工作有效期开始时间
    private String gzpyxqqd_app;
    //工作有效期结束时间
    private String gzpyxqzd_app;
    //工作领导人
    private String gzldr;
    //司机
    private String sj_select;
    //作业组成员姓名及安全等级
    private String zyzcy_list;
    //其他作业组成员姓名及安全等级
    private String wbcy_list;
    //需要停电的设备
    private String xtdsb;
    //装设接地线的位置
    private String zsdxwz;
    //作业区防护措施
    private String zyqfhcs;
    //其他安全措施
    private String qtaqcs;
    //变更作业组成员记录
    private String bgzyzcyjl;
    //工作票结束时间
    private String gzpjssj_app;
    //工作领导人签字
    private String gzpldrqz;
    //发票人签字
    private String fprqz;

    public String getBgzyzcyjl() {
        return bgzyzcyjl;
    }

    public void setBgzyzcyjl(String bgzyzcyjl) {
        this.bgzyzcyjl = bgzyzcyjl;
    }

    public String getGzpjssj_app() {
        return gzpjssj_app;
    }

    public void setGzpjssj_app(String gzpjssj_app) {
        this.gzpjssj_app = gzpjssj_app;
    }

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

    public String getFpr() {
        return fpr;
    }

    public void setFpr(String fpr) {
        this.fpr = fpr;
    }

    public String getFprq_app() {
        return fprq_app;
    }

    public void setFprq_app(String fprq_app) {
        this.fprq_app = fprq_app;
    }

    public String getGzldr_aqdj() {
        return gzldr_aqdj;
    }

    public void setGzldr_aqdj(String gzldr_aqdj) {
        this.gzldr_aqdj = gzldr_aqdj;
    }

    public String getFsfw() {
        return fsfw;
    }

    public void setFsfw(String fsfw) {
        this.fsfw = fsfw;
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

    public String getGzpyxqqd_app() {
        return gzpyxqqd_app;
    }

    public void setGzpyxqqd_app(String gzpyxqqd_app) {
        this.gzpyxqqd_app = gzpyxqqd_app;
    }

    public String getGzpyxqzd_app() {
        return gzpyxqzd_app;
    }

    public void setGzpyxqzd_app(String gzpyxqzd_app) {
        this.gzpyxqzd_app = gzpyxqzd_app;
    }

    public String getGzldr() {
        return gzldr;
    }

    public void setGzldr(String gzldr) {
        this.gzldr = gzldr;
    }

    public String getSj_select() {
        return sj_select;
    }

    public void setSj_select(String sj_select) {
        this.sj_select = sj_select;
    }

    public String getZyzcy_list() {
        return zyzcy_list;
    }

    public void setZyzcy_list(String zyzcy_list) {
        this.zyzcy_list = zyzcy_list;
    }

    public String getWbcy_list() {
        return wbcy_list;
    }

    public void setWbcy_list(String wbcy_list) {
        this.wbcy_list = wbcy_list;
    }

    public String getXtdsb() {
        return xtdsb;
    }

    public void setXtdsb(String xtdsb) {
        this.xtdsb = xtdsb;
    }

    public String getZsdxwz() {
        return zsdxwz;
    }

    public void setZsdxwz(String zsdxwz) {
        this.zsdxwz = zsdxwz;
    }

    public String getZyqfhcs() {
        return zyqfhcs;
    }

    public void setZyqfhcs(String zyqfhcs) {
        this.zyqfhcs = zyqfhcs;
    }

    public String getQtaqcs() {
        return qtaqcs;
    }

    public void setQtaqcs(String qtaqcs) {
        this.qtaqcs = qtaqcs;
    }

    public String getGzpldrqz() {
        return gzpldrqz;
    }

    public void setGzpldrqz(String gzpldrqz) {
        this.gzpldrqz = gzpldrqz;
    }

    public String getFprqz() {
        return fprqz;
    }

    public void setFprqz(String fprqz) {
        this.fprqz = fprqz;
    }
}
