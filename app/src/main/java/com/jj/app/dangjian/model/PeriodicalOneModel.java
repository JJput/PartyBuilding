package com.jj.app.dangjian.model;

import java.util.List;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 12:08 PM
 * @Version 1.0
 * @描述: 一级刊期
 */
public class PeriodicalOneModel {
    /**
     * MSG :
     * DATA : [{"ID":"17d7a4ae274f4e6ba858975018e09807","NAME":"报纸期刊","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/%E6%8A%A5%E7%BA%B8.jpg"},{"ID":"a98292c57e9f4c0caa469bdf9c00f67e","NAME":"党建频道","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/%E5%85%9A%E5%BB%BA.jpg"},{"ID":"8ffdaa1fe32e487fa8bb66726f1f6533","NAME":"关注热点","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/4.jpg"},{"ID":"1efc71d6de1e4a8dbbc40d3047d6e8e9","NAME":"热点杂志","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/1.jpg"},{"ID":"796661e9503046ea891fedc161f5d5f0","NAME":"学習经典","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/%E5%AD%A6%E7%BF%92%E7%BB%8F%E5%85%B8.png"},{"ID":"6a3fc99e6ef34ca492ba075da03aad2d","NAME":"两学一做","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/%E4%B8%A4%E5%AD%A6%E4%B8%80%E5%81%9A.png"}]
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
         * ID : 17d7a4ae274f4e6ba858975018e09807
         * NAME : 报纸期刊
         * PICURL : http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/%E6%8A%A5%E7%BA%B8.jpg
         */

        private String ID;
        private String NAME;
        private String PICURL;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getPICURL() {
            return PICURL;
        }

        public void setPICURL(String PICURL) {
            this.PICURL = PICURL;
        }
    }
}
