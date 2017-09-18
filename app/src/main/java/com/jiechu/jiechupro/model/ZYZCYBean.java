package com.jiechu.jiechupro.model;

import java.io.Serializable;

/**
 * 作业组成员
 * Created by allen on 2017/9/18.
 */

public class ZYZCYBean implements Serializable {
    //成员id
    private String cyid;
    //成员名称
    private String cymc;
    //安全等级
    private String aqdj;

    public String getCyid() {
        return cyid;
    }

    public void setCyid(String cyid) {
        this.cyid = cyid;
    }

    public String getCymc() {
        return cymc;
    }

    public void setCymc(String cymc) {
        this.cymc = cymc;
    }

    public String getAqdj() {
        return aqdj;
    }

    public void setAqdj(String aqdj) {
        this.aqdj = aqdj;
    }
}
