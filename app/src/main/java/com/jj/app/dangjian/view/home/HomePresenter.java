package com.jj.app.dangjian.view.home;

import com.jj.app.dangjian.model.CategoryModel;
import com.jj.app.dangjian.model.NewsListModel;
import com.jj.app.dangjian.model.PeriodicalOneModel;
import com.jj.app.dangjian.okhttp.RequestCenter;
import com.jj.app.dangjian.okhttp.listener.DisposeDataListener;
import com.jj.app.dangjian.utils.DevUtils;

import java.util.List;

import github.jjput.mvpbaselibrary.base.BasePresenter;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 11:01 AM
 * @Version 1.0
 * @描述:
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private static final String PAGE = "1";
    private static final String PAGE_SIZE = "5";

    @Override
    public void getFindAllCategory() {
        RequestCenter.requsetAllCategory(DevUtils.getDevID(), new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                List<CategoryModel.DATABean> dataBeans = ((CategoryModel) responseObj).getDATA();
                for (CategoryModel.DATABean dataBean : dataBeans) {
                    //横滚图片
                    if (dataBean.getCategoryName().equals(HomeActivity.LIST_TITLE_NAME[0])) {
                        getHeadImagesData(dataBean.getCategoryId());
                    }
                    //党建动态
                    else if (dataBean.getCategoryName().equals(HomeActivity.LIST_TITLE_NAME[1])) {
                        mView.setIdThrends(dataBean.getCategoryId());
                        getTrendsData(dataBean.getCategoryId());
                    }
                    //政策文件
                    else if (dataBean.getCategoryName().equals(HomeActivity.LIST_TITLE_NAME[2])) {
                        mView.setIdZfile(dataBean.getCategoryId());
                        getZFileData(dataBean.getCategoryId());
                    }
                    //基层党建
                    else if (dataBean.getCategoryName().equals(HomeActivity.LIST_TITLE_NAME[3])) {
                        mView.setIdGrassRoots(dataBean.getCategoryId());
                        getGrassRootsData(dataBean.getCategoryId());
                    } else {
                        mView.getDataFail("错误获取:" + dataBean.getCategoryName());
                    }
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                mView.getDataFail(HomeActivity.CONNECT_INTERNET_TIME_OUT);
            }
        });
    }

    /**
     * 横滚图片数据
     */
    @Override
    public void getHeadImagesData(String categoryId) {

        RequestCenter.requsetFindArticle(DevUtils.getDevID(), categoryId, PAGE, PAGE_SIZE, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mView.showHeadImags((NewsListModel) responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {
                mView.getDataFail(HomeActivity.LIST_TITLE_NAME[0]);
            }
        });


    }


    /**
     * 党建动态
     */
    @Override
    public void getTrendsData(String categoryId) {

        RequestCenter.requsetFindArticle(DevUtils.getDevID(), categoryId, PAGE, PAGE_SIZE, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mView.showLvTrends((NewsListModel) responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {
                mView.getDataFail(HomeActivity.LIST_TITLE_NAME[1]);
            }
        });


    }

    /**
     * 政策文件
     */
    @Override
    public void getZFileData(String categoryId) {

        RequestCenter.requsetFindArticle(DevUtils.getDevID(), categoryId, PAGE, PAGE_SIZE, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mView.showLvZFile((NewsListModel) responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {
                mView.getDataFail(HomeActivity.LIST_TITLE_NAME[2]);
            }
        });


    }


    /**
     * 基层党建
     */
    @Override
    public void getGrassRootsData(String categoryId) {

        RequestCenter.requsetFindArticle(DevUtils.getDevID(), categoryId, PAGE, PAGE_SIZE, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mView.showLvGrassRoots((NewsListModel) responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {
                mView.getDataFail(HomeActivity.LIST_TITLE_NAME[3]);
            }
        });


    }


    /**
     * 党建期刊
     */
    @Override
    public void getPeriodicalList() {
        RequestCenter.requsetPeriodicalOneList(DevUtils.getDevID(), new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mView.showPeriodicalList((PeriodicalOneModel) responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {
                mView.getDataFail(HomeActivity.LIST_TITLE_NAME[4]);
            }
        });
    }
}
