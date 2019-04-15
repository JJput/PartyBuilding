package com.jj.app.dangjian.view.scanperiodical;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jj.app.dangjian.R;
import com.jj.app.dangjian.adapter.PeriodicalGridViewAdapter;
import com.jj.app.dangjian.model.PeriodicalListingModel;
import com.jj.app.dangjian.model.PeriodicalOneModel;
import com.jj.app.dangjian.okhttp.RequestCenter;
import com.jj.app.dangjian.okhttp.listener.DisposeDataListener;
import com.jj.app.dangjian.utils.DevUtils;
import com.jj.app.dangjian.utils.DialogUtils;
import com.jj.app.dangjian.view.lookperiodical.LookPeriodicalActivity;

import java.util.ArrayList;
import java.util.List;

import github.jjput.mvpbaselibrary.base.BaseFragment;
import github.jjput.mvpbaselibrary.base.IPresenter;

/**
 * @作者: JJ
 * @创建时间: 2019/4/12 2:34 PM
 * @Version 1.0
 * @描述:
 */
public class ScanListingFragment extends BaseFragment {

    private GridView mGridView;

    private List<PeriodicalListingModel.DATABean> mList = new ArrayList<>();
    private PeriodicalGridViewAdapter mGridViewAdapter;

    private String pid;

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        if (bundle != null) {
            String pid = bundle.getString(ScanPeriodicalActivity.PID, null);
            if (pid != null)
                this.pid = pid;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void findView(View view) {
        super.findView(view);
        mGridView = view.findViewById(R.id.scan_per_fragment_gv);
        RequestCenter.requsetPeriodicalListing(DevUtils.getDevID(), pid, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mList = ((PeriodicalListingModel) responseObj).getDATA();
                List<PeriodicalOneModel.DATABean> gvData = new ArrayList<>();
                for (PeriodicalListingModel.DATABean data : mList) {
                    PeriodicalOneModel.DATABean dataBean = new PeriodicalOneModel.DATABean();
                    dataBean.setNAME(data.getNAME());
                    dataBean.setPICURL(data.getPICURL());
                    gvData.add(dataBean);
                }
                mGridViewAdapter = new PeriodicalGridViewAdapter(getContext(), gvData);
                mGridView.setAdapter(mGridViewAdapter);
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), LookPeriodicalActivity.class);
                        intent.putExtra(LookPeriodicalActivity.PID, mList.get(position).getID());
                        intent.putExtra(LookPeriodicalActivity.NAME, mList.get(position).getNAME());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUtils.showErrorDialog(getContext(), "获取该刊期失败", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.scan_periodical_fragment;
    }
}
