package com.jj.app.dangjian.view.scanperiodical;

import com.jj.app.dangjian.model.PeriodicalTwoModel;
import com.jj.app.dangjian.okhttp.RequestCenter;
import com.jj.app.dangjian.okhttp.listener.DisposeDataListener;
import com.jj.app.dangjian.utils.DevUtils;

import github.jjput.mvpbaselibrary.base.BasePresenter;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 3:21 PM
 * @Version 1.0
 * @描述:
 */
public class ScanPeriodicalPresenter extends BasePresenter<ScanPeriodicalContract.View> implements ScanPeriodicalContract.Presenter {
    @Override
    public void getPeriodicalList(String id) {
        RequestCenter.requsetPeriodicalTwoList(DevUtils.getDevID(), id, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mView.setPeriodicalDataSuccess((PeriodicalTwoModel) responseObj);
            }

            @Override
            public void onFailure(Object reasonObj) {
                mView.periodicalDataFail(reasonObj.toString());
            }
        });
    }
}
