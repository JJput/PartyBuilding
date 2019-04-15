package com.jj.app.dangjian.model;

import java.util.List;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 3:51 PM
 * @Version 1.0
 * @描述: 刊期清单
 */
public class PeriodicalListingModel {


    /**
     * MSG :
     * DATA : [{"ID":"f1dedb7593a14244a4854f72460ba5f0","NAME":"参考消息","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/press/2019/03/1.bmp"},{"ID":"f2391a2a46b74a3e816988637f65fe15","NAME":"环球时报","PICURL":"http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/1(2).jpg"}]
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
         * ID : f1dedb7593a14244a4854f72460ba5f0
         * NAME : 参考消息
         * PICURL : http://test.fjipcc.com/userfiles/1/images/press/press/2019/03/1.bmp
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
