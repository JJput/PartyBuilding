package com.jj.app.dangjian.model;

import java.util.List;

/**
 * @作者: JJ
 * @创建时间: 2019/4/12 7:45 PM
 * @Version 1.0
 * @描述:
 */
public class PeriodicalLookModel {

    /**
     * MSG :
     * DATA : [{"NAME":"参考消息0308","FILEURL":["http://test.fjipcc.com/userfiles/1/images/press/press/2019/03/1(1).bmp","http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(1).jpg","http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(2).jpg","http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(2).jpg"]}]
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
         * NAME : 参考消息0308
         * FILEURL : ["http://test.fjipcc.com/userfiles/1/images/press/press/2019/03/1(1).bmp","http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(1).jpg","http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(2).jpg","http://test.fjipcc.com/userfiles/1/images/press/pressType/2019/03/2(2).jpg"]
         */

        private String NAME;
        private List<String> FILEURL;

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public List<String> getFILEURL() {
            return FILEURL;
        }

        public void setFILEURL(List<String> FILEURL) {
            this.FILEURL = FILEURL;
        }
    }
}
