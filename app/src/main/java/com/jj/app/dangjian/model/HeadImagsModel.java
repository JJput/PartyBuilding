package com.jj.app.dangjian.model;

import java.util.List;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 5:35 PM
 * @Version 1.0
 * @描述:
 */
public class HeadImagsModel {


    /**
     * MSG :
     * DATA : [{"url":"xxxxx","title":"标题1","img":"xxx/1.png"},{"url":"xxxxx","title":"标题2","img":"xxx/1.png"}]
     * RESULTCODE : 0
     */

    private String MSG;
    private String RESULTCODE;
    private List<DATABean> DATA;

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getRESULTCODE() {
        return RESULTCODE;
    }

    public void setRESULTCODE(String RESULTCODE) {
        this.RESULTCODE = RESULTCODE;
    }

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * url : xxxxx
         * title : 标题1
         * img : xxx/1.png
         */

        private String url;
        private String title;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }
}
