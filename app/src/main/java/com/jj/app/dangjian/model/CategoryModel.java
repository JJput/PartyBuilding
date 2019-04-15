package com.jj.app.dangjian.model;

import java.util.List;

/**
 * @作者: JJ
 * @创建时间: 2019/4/12 3:54 PM
 * @Version 1.0
 * @描述:
 */
public class CategoryModel {


    /**
     * MSG :
     * DATA : [{"categoryId":"0808431755344be0ac24b9e58a7e9245","categoryName":"横滚轮播图片"},{"categoryId":"f0356b57129741f68b49fe7913fab1f7","categoryName":"党建动态"},{"categoryId":"ea9695905fec497abe399f7ebbd96ba2","categoryName":"政策文件"},{"categoryId":"6041c981297642078ef1c2ca8d833081","categoryName":"基层党建"}]
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
         * categoryId : 0808431755344be0ac24b9e58a7e9245
         * categoryName : 横滚轮播图片
         */

        private String categoryId;
        private String categoryName;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }
}
