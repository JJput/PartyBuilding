package com.jj.app.dangjian.okhttp;


import com.jj.app.dangjian.model.CategoryModel;
import com.jj.app.dangjian.model.NewsListModel;
import com.jj.app.dangjian.model.PeriodicalListingModel;
import com.jj.app.dangjian.model.PeriodicalLookModel;
import com.jj.app.dangjian.model.PeriodicalOneModel;
import com.jj.app.dangjian.model.PeriodicalTwoModel;
import com.jj.app.dangjian.okhttp.listener.DisposeDataHandle;
import com.jj.app.dangjian.okhttp.listener.DisposeDataListener;
import com.jj.app.dangjian.okhttp.request.CommonRequest;
import com.jj.app.dangjian.okhttp.request.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @作者: JJ
 * @创建时间: 2018/4/2 18:58
 * @Version 1.0
 * @描述: 存放应用中所以http请求
 */
public class RequestCenter {

    public static void postRequest(String url, JSONObject jsonObject, DisposeDataListener listener, Class<?> clazz) throws JSONException {
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url, jsonObject), new DisposeDataHandle(listener, clazz));
    }

    public static void getRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }


    /**
     * 栏目获取
     *
     * @param listener
     */
    public static void requsetAllCategory(String GUID, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("GUID", GUID);
        RequestCenter.getRequest(StaticHttp.getAllCategory(), params, listener, CategoryModel.class);
    }

    /**
     * 文章列表获取
     * @param GUID
     * @param categoryId 通过栏目获取
     * @param pageNo 第几页
     * @param pageSize 每页数量
     * @param listener
     */
    public static void requsetFindArticle(String GUID,String categoryId,String pageNo,String pageSize, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("GUID", GUID);
        params.put("categoryId", categoryId);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        RequestCenter.getRequest(StaticHttp.getFindArticle(), params, listener, NewsListModel.class);
    }

    /**
     * 请求期刊一级列表
     *
     * @param listener
     */
    public static void requsetPeriodicalOneList(String GUID, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("GUID", GUID);
        RequestCenter.getRequest(StaticHttp.getPeriodicalOne(), params, listener, PeriodicalOneModel.class);
    }


    /**
     * 请求期刊二级列表
     *
     * @param listener
     */
    public static void requsetPeriodicalTwoList(String GUID, String id, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("ID", id);
        params.put("GUID", GUID);
        RequestCenter.getRequest(StaticHttp.getPeriodicalTwo(), params, listener, PeriodicalTwoModel.class);
    }

    /**
     * 请求期刊清单
     *
     * @param listener
     */
    public static void requsetPeriodicalListing(String GUID, String pid, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("PID", pid);
        params.put("GUID", GUID);
        RequestCenter.getRequest(StaticHttp.getPeriodicalListing(), params, listener, PeriodicalListingModel.class);
    }

    /**
     * 请求期刊清单
     *
     * @param listener
     */
    public static void requsetPeriodicalLook(String GUID, String pid, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("PID", pid);
        params.put("GUID", GUID);
        RequestCenter.getRequest(StaticHttp.getPeriodicalLook(), params, listener, PeriodicalLookModel.class);
    }


}
