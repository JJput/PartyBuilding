package com.jj.app.dangjian.model;

import java.util.List;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 5:35 PM
 * @Version 1.0
 * @描述:
 */
public class NewsListModel {


    /**
     * pageNo : 1
     * pageSize : 5
     * count : 6
     * DATA : [{"url":"http://test.fjipcc.com/f/view-0808431755344be0ac24b9e58a7e9245-15f18ebda97842e0a348ed8d1c75bc2a.html","title":"建党节建党节建党节6","time":"2019年04月12日","img":"http://test.fjipcc.com/userfiles/1/_thumbs/images/cms/article/2019/04/1095052.gif"},{"url":"http://test.fjipcc.com/f/view-0808431755344be0ac24b9e58a7e9245-cf6cf4ef2dce4b1bb16de869789b21a6.html","title":"建党节建党节建党节5","time":"2019年04月12日","img":"http://test.fjipcc.com/userfiles/1/_thumbs/images/cms/article/2019/04/1087145.gif"},{"url":"http://test.fjipcc.com/f/view-0808431755344be0ac24b9e58a7e9245-e95bb410519640789eaedcf1d9e9adfe.html","title":"建党节建党节建党节4","time":"2019年04月12日","img":"http://test.fjipcc.com/userfiles/1/_thumbs/images/cms/article/2019/04/1087145.gif"},{"url":"http://test.fjipcc.com/f/view-0808431755344be0ac24b9e58a7e9245-4eb620216cd640049699da86a7da66ea.html","title":"建党节建党节建党节3","time":"2019年04月12日","img":"http://test.fjipcc.com/userfiles/1/_thumbs/images/cms/article/2019/04/1095052.gif"},{"url":"http://test.fjipcc.com/f/view-0808431755344be0ac24b9e58a7e9245-5f4ad2096e664284b11ca020e313aa3f.html","title":"建党节建党节建党节2","time":"2019年04月12日","img":"http://test.fjipcc.com/userfiles/1/_thumbs/images/cms/article/2019/04/1087145.gif"}]
     * RESULTCODE : 0
     * MSG :
     */

    private int pageNo;
    private int pageSize;
    private int count;
    private String RESULTCODE;
    private String MSG;
    private List<DATABean> DATA;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRESULTCODE() {
        return RESULTCODE;
    }

    public void setRESULTCODE(String RESULTCODE) {
        this.RESULTCODE = RESULTCODE;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        /**
         * url : http://test.fjipcc.com/f/view-0808431755344be0ac24b9e58a7e9245-15f18ebda97842e0a348ed8d1c75bc2a.html
         * title : 建党节建党节建党节6
         * time : 2019年04月12日
         * img : http://test.fjipcc.com/userfiles/1/_thumbs/images/cms/article/2019/04/1095052.gif
         */

        private String url;
        private String title;
        private String time;
        private String img;

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
