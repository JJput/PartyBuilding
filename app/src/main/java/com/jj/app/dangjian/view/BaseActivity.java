package com.jj.app.dangjian.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jj.app.dangjian.R;

import butterknife.ButterKnife;
import github.jjput.mvpbaselibrary.base.IPresenter;
import github.jjput.utils.BarUtils;

/**
 * @作者: JJ
 * @创建时间: 2019/1/5 9:24 PM
 * @Version 1.0
 * @描述: 带返回键的Activity
 */
public abstract class BaseActivity<T extends IPresenter> extends github.jjput.mvpbaselibrary.base.BaseActivity<T> {

    private ImageButton ibBack;
    private TextView tvTitleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        BarUtils.setStatusBarVisibility(this,false);
        ibBack = findViewById(R.id.base_title_ib_back);
        tvTitleName = findViewById(R.id.base_title_tv_titlename);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected TextView getTitleName() {
        return tvTitleName;
    }
}
