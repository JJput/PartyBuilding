package com.jj.app.dangjian.okhttp;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 10:22 AM
 * @Version 1.0
 * @描述:
 */
public class StaticHttp {

    private static final String SERVER_ADDRES = "http://dj.fjtjtx.com/";

    private static final String PERIODICAL_ONE = "MOM/getPressType";
    private static final String PERIODICAL_TWO = "MOM/getPressChildType";
    private static final String PERIODICAL_LOOK = "MOM/getPeriodical";
    private static final String PERIODICAL_LISTING = "MOM/getPeriodicalDetails";
    private static final String ALL_CATEGORY = "MOM/findAllCategory";
    private static final String FIND_ARTICLE = "MOM/findArticle";


    public static String getPeriodicalOne() {
        return SERVER_ADDRES + PERIODICAL_ONE;
    }

    public static String getPeriodicalTwo() {
        return SERVER_ADDRES + PERIODICAL_TWO;
    }

    public static String getPeriodicalListing() {
        return SERVER_ADDRES + PERIODICAL_LISTING;
    }

    public static String getAllCategory() {
        return SERVER_ADDRES + ALL_CATEGORY;
    }

    public static String getFindArticle() {
        return SERVER_ADDRES + FIND_ARTICLE;
    }

    public static String getPeriodicalLook() {
        return SERVER_ADDRES + PERIODICAL_LOOK;
    }
}
