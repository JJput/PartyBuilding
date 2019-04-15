package com.jj.app.dangjian.view.home;

import com.jj.app.dangjian.model.NewsListModel;
import com.jj.app.dangjian.model.PeriodicalOneModel;

import github.jjput.mvpbaselibrary.base.IPresenter;
import github.jjput.mvpbaselibrary.base.IView;

/**
 * @作者: JJ
 * @创建时间: 2019/4/10 9:32 PM
 * @Version 1.0
 * @描述:
 */
public interface HomeContract {

    interface View extends IView {

        /**
         * 显示期刊列表
         *
         * @param periodicalOneModel
         */
        void showPeriodicalList(PeriodicalOneModel periodicalOneModel);

        /**
         * 显示党建动态
         *
         * @param newsListModel
         */
        void showLvTrends(NewsListModel newsListModel);

        /**
         * 显示基础党建
         *
         * @param newsListModel
         */
        void showLvGrassRoots(NewsListModel newsListModel);

        /**
         * 显示政策文件
         *
         * @param newsListModel
         */
        void showLvZFile(NewsListModel newsListModel);

        /**
         * 显示头部横滚图片
         *
         * @param newsListModel
         */
        void showHeadImags(NewsListModel newsListModel);

        /**
         * 获取数据失败
         *
         * @param name 什么数据
         */
        void getDataFail(String name);

        void setIdThrends(String idThrends);

        void setIdGrassRoots(String idGrassRoots);

        void setIdZfile(String idZfile);
    }

    interface Presenter extends IPresenter {

        void getFindAllCategory();

        /**
         * 获取期刊1级列表
         */
        void getPeriodicalList();

        /**
         * 获取党建动态数据
         */
        void getTrendsData(String categoryId);

        /**
         * 获取基础党建数据
         */
        void getGrassRootsData(String categoryId);

        /**
         * 获取政策文件数据
         */
        void getZFileData(String categoryId);

        /**
         * 获取首页横滚图片数据
         */
        void getHeadImagesData(String categoryId);
    }
}
