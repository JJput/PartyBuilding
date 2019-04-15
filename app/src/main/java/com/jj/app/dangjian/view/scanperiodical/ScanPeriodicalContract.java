package com.jj.app.dangjian.view.scanperiodical;

import com.jj.app.dangjian.model.PeriodicalTwoModel;

import github.jjput.mvpbaselibrary.base.IPresenter;
import github.jjput.mvpbaselibrary.base.IView;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 3:21 PM
 * @Version 1.0
 * @描述:
 */
public interface ScanPeriodicalContract {
    interface View extends IView {
        /**
         * 获取期刊2级数据成功
         */
        void setPeriodicalDataSuccess(PeriodicalTwoModel periodicalTwoModel);

        /**
         * 失败
         */
        void periodicalDataFail(String errCode);


    }

    interface Presenter extends IPresenter {

        /**
         * 获取期刊2级列表
         */
        void getPeriodicalList(String id);

    }
}
