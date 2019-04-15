package com.jj.app.dangjian.view.scanperiodical;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jj.app.dangjian.R;
import com.jj.app.dangjian.custom.CustomDialog;
import com.jj.app.dangjian.model.PeriodicalTwoModel;
import com.jj.app.dangjian.utils.DialogUtils;
import com.jj.app.dangjian.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 3:43 PM
 * @Version 1.0
 * @描述: 二级刊期展示
 */
public class ScanPeriodicalActivity extends BaseActivity<ScanPeriodicalContract.Presenter> implements ScanPeriodicalContract.View {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String PID = "pid";

    @BindView(R.id.scan_per_tl)
    TabLayout mTabLayout;
    @BindView(R.id.scan_per_vp)
    ViewPager mViewPager;

    private CustomDialog mLoadingDialog;
    private List<String> mTitle;
    private List<Fragment> mFragment;
    private List<PeriodicalTwoModel.DATABean> mDtatBeans;

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        getTitleName().setText(intent.getStringExtra(TITLE));
        String id = intent.getStringExtra(ID);
        mLoadingDialog = new CustomDialog(this, R.layout.dialog_loading_layout, R.style.Theme_dialog);
        mLoadingDialog.setCancelable(false);
        if (id != null) {
            mPresenter.getPeriodicalList(id);
            mLoadingDialog.show();
        } else {
            DialogUtils.showErrorDialog(this, "该刊期没有其他内容了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
        mTitle = new ArrayList<>();
        mFragment = new ArrayList<>();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.scan_periodical_activity;
    }

    @Override
    protected ScanPeriodicalContract.Presenter initInjector() {
        return new ScanPeriodicalPresenter();
    }

    @Override
    public void setPeriodicalDataSuccess(PeriodicalTwoModel periodicalTwoModel) {
        mLoadingDialog.dismiss();
        mDtatBeans = periodicalTwoModel.getDATA();
        if (mDtatBeans.size() == 0) {
            DialogUtils.showTitleDialog(this, "你好", "该期刊还未发布任何内容!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
            return;
        }
        for (PeriodicalTwoModel.DATABean data : mDtatBeans) {
            mTitle.add(data.getNAME());
            ScanListingFragment scanListFragment = new ScanListingFragment();
            Bundle bundle = new Bundle();
            bundle.putString(PID, data.getID());
            scanListFragment.setArguments(bundle);
            mFragment.add(scanListFragment);
        }

        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());


        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void periodicalDataFail(String errCode) {
        mLoadingDialog.dismiss();
        DialogUtils.showErrorDialog(this, errCode, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }

}
