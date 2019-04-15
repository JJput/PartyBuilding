package com.jj.app.dangjian.view.listperiodical;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.jj.app.dangjian.R;
import com.jj.app.dangjian.adapter.PeriodicalGridViewAdapter;
import com.jj.app.dangjian.model.PeriodicalOneModel;
import com.jj.app.dangjian.view.BaseActivity;
import com.jj.app.dangjian.view.scanperiodical.ScanPeriodicalActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import github.jjput.mvpbaselibrary.base.IPresenter;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 3:20 PM
 * @Version 1.0
 * @描述: 党建刊期 点击更多后进入此界面
 */
public class ListPeriodicalActivity extends BaseActivity {


    public static final String DATA = "data";
    @BindView(R.id.list_per_gv)
    GridView mGridView;

    private List<PeriodicalOneModel.DATABean> mList = new ArrayList<>();
    private PeriodicalGridViewAdapter mGridViewAdapter;

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        String json = intent.getStringExtra(DATA);
        Gson gson = new Gson();
        mList = gson.fromJson(json, PeriodicalOneModel.class).getDATA();

    }

    @Override
    protected void initView() {
        super.initView();
        getTitleName().setText("党建期刊");
        mGridViewAdapter = new PeriodicalGridViewAdapter(this, mList);
        mGridView.setAdapter(mGridViewAdapter);


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListPeriodicalActivity.this, ScanPeriodicalActivity.class);
                intent.putExtra(ScanPeriodicalActivity.ID, mList.get(position).getID());
                intent.putExtra(ScanPeriodicalActivity.TITLE, mList.get(position).getNAME());
                startActivity(intent);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_periodical_activity;
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }

}
