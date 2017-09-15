package com.jiechu.jiechupro.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by allen on 2017/9/15.
 */

public class XCPicBean implements Serializable {
    private String outName;   //顶部名称
    private List<Pictures> picturesList;    //图片

    public String getOutName() {
        return outName;
    }

    public void setOutName(String outName) {
        this.outName = outName;
    }

    public List<Pictures> getPicturesList() {
        return picturesList;
    }

    public void setPicturesList(List<Pictures> picturesList) {
        this.picturesList = picturesList;
    }

    /**
     * 图片对象
     */
    public static class Pictures implements Serializable {
        private String innerName;
        private String Url_CZXL;    //水平图
        private String Url_CZXL_ICON;   //水平图缩略图
        private String Url_SXL; //垂直图
        private String Url_SXL_ICON;    //垂直缩略图

        public String getInnerName() {
            return innerName;
        }

        public void setInnerName(String innerName) {
            this.innerName = innerName;
        }

        public String getUrl_CZXL() {
            return Url_CZXL;
        }

        public void setUrl_CZXL(String url_CZXL) {
            Url_CZXL = url_CZXL;
        }

        public String getUrl_CZXL_ICON() {
            return Url_CZXL_ICON;
        }

        public void setUrl_CZXL_ICON(String url_CZXL_ICON) {
            Url_CZXL_ICON = url_CZXL_ICON;
        }

        public String getUrl_SXL() {
            return Url_SXL;
        }

        public void setUrl_SXL(String url_SXL) {
            Url_SXL = url_SXL;
        }

        public String getUrl_SXL_ICON() {
            return Url_SXL_ICON;
        }

        public void setUrl_SXL_ICON(String url_SXL_ICON) {
            Url_SXL_ICON = url_SXL_ICON;
        }
    }

}
