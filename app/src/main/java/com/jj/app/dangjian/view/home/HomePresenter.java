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

            }
        });
    }

    /**
     * 横滚图片数据
     */
    @Override
    public void getHeadImagesData(String categoryId) {
        //        HeadImagsModel headImagsModel = new HeadImagsModel();
        //        HeadImagsModel.DATABean data = new HeadImagsModel.DATABean();
        //        data.setTitle("龙母");
        //        data.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554992551106&di=7b9953af5d2a3a77fcfa7790ac28dfc6&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170731%2Fb4af692e1c384db8b6ef24264ec00078.jpeg");
        //
        //        HeadImagsModel.DATABean data2 = new HeadImagsModel.DATABean();
        //        data2.setTitle("小恶魔");
        //        data2.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554992644304&di=3dcc7285a9a81e2b72ca1dbd40002beb&imgtype=0&src=http%3A%2F%2Fsinastorage.com%2Fdata.ent.sina.com.cn%2Ftv%2Fstills%2F11780%2F7e80a24f59a53c6aa560a93fd7ccd326.jpg");
        //
        //        HeadImagsModel.DATABean data3 = new HeadImagsModel.DATABean();
        //        data3.setTitle("埃里克的积分卡拉几十块风的季节奥斯卡剪短发了时代峻峰离开家");
        //        data3.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554992735901&di=c329f500a7d15367e7e86f720498d567&imgtype=0&src=http%3A%2F%2Fimg1.cache.netease.com%2Fcnews%2F2016%2F4%2F26%2F201604261156329def1.gif");

        //        List<HeadImagsModel.DATABean> dataBeanList = new ArrayList<>();
        //        dataBeanList.add(data);
        //        dataBeanList.add(data2);
        //        dataBeanList.add(data3);
        //        headImagsModel.setDATA(dataBeanList);
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
        //        List<NewsListModel.DATABean> mList = new ArrayList<>();
        //        for (int i = 0; i < 5; i++) {
        //            NewsListModel.DATABean dataBean = new NewsListModel.DATABean();
        //            dataBean.setTitle("标题" + i);
        //            mList.add(dataBean);
        //        }
        //        NewsListModel newsListModel = new NewsListModel();
        //        newsListModel.setDATA(mList);
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
        //        List<NewsListModel.DATABean> mList = new ArrayList<>();
        //        for (int i = 0; i < 5; i++) {
        //            NewsListModel.DATABean dataBean = new NewsListModel.DATABean();
        //            if (i == 4)
        //                dataBean.setTitle("标题1111111111111111111111111111111111111111111" + i);
        //            else
        //                dataBean.setTitle("标题" + i);
        //
        //            mList.add(dataBean);
        //        }
        //
        //        NewsListModel newsListModel = new NewsListModel();
        //        newsListModel.setDATA(mList);
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
        //        List<NewsListModel.DATABean> mList = new ArrayList<>();
        //        for (int i = 0; i < 5; i++) {
        //            NewsListModel.DATABean dataBean = new NewsListModel.DATABean();
        //            dataBean.setTitle("标题" + i);
        //            dataBean.setImg("http://n.sinaimg.cn/ent/transform/20170803/glvu-fyitamv4709962.jpg");
        //            mList.add(dataBean);
        //        }
        //        NewsListModel newsListModel = new NewsListModel();
        //        newsListModel.setDATA(mList);

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
