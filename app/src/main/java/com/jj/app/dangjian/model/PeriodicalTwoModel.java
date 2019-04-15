package com.jj.app.dangjian.model;

import java.util.List;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 3:51 PM
 * @Version 1.0
 * @描述: 二级刊期
 */
public class PeriodicalTwoModel {
    /**
     * MSG :
     * DATA : [{"ID":"ce1fd76213bf4538846ade808125459c","NAME":"日报","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(3).jpg"},{"ID":"8597b601dda7429abd273dffa204cb47","NAME":"早报","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(2).jpg"},{"ID":"2a162714a5194b23b62c8428bacf0ec8","NAME":"晚报","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/1(2).jpg"},{"ID":"1d5ac1ee30474ddb9d2924cccec7a0ac","NAME":"都市报","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(2).jpg"}]
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
         * ID : ce1fd76213bf4538846ade808125459c
         * NAME : 日报
         * PICURL : http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(3).jpg
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
