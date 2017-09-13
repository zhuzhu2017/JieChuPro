package com.jiechu.jiechupro.model;

import java.io.Serializable;

/**
 * Created by allen on 2017/9/13.
 */

public class TitleBean implements Serializable {
    private String title;
    private boolean selected;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
