package com.jiechu.jiechupro.model;

import java.io.Serializable;

/**
 * Created by allen on 2017/9/14.
 */

public class GQYBHJSGHJLBean implements Serializable {
    //工区名称
    private String gqmc;
    //发票日期
    private String fprq_app;
    //工作票编号
    private String gzpbh;
    //工前预想
    private String bqyx;
    //分工记录
    private String fgjl;
    //任务来源
    private String rwlyjwcqk_rwly;
    //收工记录
    private String rwlyjwcqk_wcqk;
    //其他
    private String rwlyjwcqk_qt;
    //行调命令号
    private String xdmlh;
    //行调命令时间
    private String xdmlsj;
    //工作领导人
    private String gzldr;

    public String getGqmc() {
        return gqmc;
    }

    public void setGqmc(String gqmc) {
        this.gqmc = gqmc;
    }

    public String getFprq_app() {
        return fprq_app;
    }

    public void setFprq_app(String fprq_app) {
        this.fprq_app = fprq_app;
    }

    public String getGzpbh() {
        return gzpbh;
    }

    public void setGzpbh(String gzpbh) {
        this.gzpbh = gzpbh;
    }

    public String getBqyx() {
        return bqyx;
    }

    public void setBqyx(String bqyx) {
        this.bqyx = bqyx;
    }

    public String getFgjl() {
        return fgjl;
    }

    public void setFgjl(String fgjl) {
        this.fgjl = fgjl;
    }

    public String getRwlyjwcqk_rwly() {
        return rwlyjwcqk_rwly;
    }

    public void setRwlyjwcqk_rwly(String rwlyjwcqk_rwly) {
        this.rwlyjwcqk_rwly = rwlyjwcqk_rwly;
    }

    public String getRwlyjwcqk_wcqk() {
        return rwlyjwcqk_wcqk;
    }

    public void setRwlyjwcqk_wcqk(String rwlyjwcqk_wcqk) {
        this.rwlyjwcqk_wcqk = rwlyjwcqk_wcqk;
    }

    public String getRwlyjwcqk_qt() {
        return rwlyjwcqk_qt;
    }

    public void setRwlyjwcqk_qt(String rwlyjwcqk_qt) {
        this.rwlyjwcqk_qt = rwlyjwcqk_qt;
    }

    public String getXdmlh() {
        return xdmlh;
    }

    public void setXdmlh(String xdmlh) {
        this.xdmlh = xdmlh;
    }

    public String getXdmlsj() {
        return xdmlsj;
    }

    public void setXdmlsj(String xdmlsj) {
        this.xdmlsj = xdmlsj;
    }

    public String getGzldr() {
        return gzldr;
    }

    public void setGzldr(String gzldr) {
        this.gzldr = gzldr;
    }
}
