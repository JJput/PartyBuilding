package com.jj.app.dangjian.view.newslist.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jj.app.dangjian.R;
import com.jj.app.dangjian.adapter.NewsListAdapter;
import com.jj.app.dangjian.model.NewsListModel;
import com.jj.app.dangjian.okhttp.RequestCenter;
import com.jj.app.dangjian.okhttp.listener.DisposeDataListener;
import com.jj.app.dangjian.utils.DevUtils;
import com.jj.app.dangjian.view.webview.WebsiteActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsListItemFragment extends LazyloadFragment {
    //一页有多少条新闻
    public final static int PAGE_NUM = 14;

    private LinearLayout ll_pb, ll_page;
    private TextView tv_nowpage, tv_localpage;
    private ListView lv;
    private int nowPage;
    private int localPage;
    private List<NewsListModel.DATABean> datas = new ArrayList<>();
    private String id = "";

    public NewsListItemFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @SuppressLint("ValidFragment")
    public NewsListItemFragment(int nowPage, int localPage, String pid) {
        this.nowPage = nowPage;
        this.localPage = localPage;
        this.id = pid;
    }

    @Override
    protected int setContentView() {
        return R.layout.newslist_fragment;
    }

    @Override
    protected void init(View view) {
        lv = view.findViewById(R.id.newslist_fragment_lv);
        ll_pb = view.findViewById(R.id.newslist_fragment_ll_loading);
        ll_page = view.findViewById(R.id.newslist_fagment_ll_page);
        tv_nowpage = view.findViewById(R.id.newslist_fagment_tv_nowpage);
        tv_localpage = view.findViewById(R.id.newslist_fagment_tv_localpage);
    }


    @Override
    protected void lazyLoad() {
        //加载数据
        initData();

    }

    private void initData() {
        datas.clear();
        //        for (int i = 0; i < PAGE_NUM; i++) {
        //            NewsListModel.DATABean bean = new NewsListModel.DATABean();
        //            bean.setTitle(i + "test");
        //            bean.setTime("2019年" + i + "月20日");
        //            bean.setUrl("www.baidu.com");
        //            //            bean.setTime(String.valueOf(new Date().getTime()));
        //            datas.add(bean);
        //        }
        RequestCenter.requsetFindArticle(DevUtils.getDevID(), id, nowPage + "", PAGE_NUM + "", new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                datas = ((NewsListModel) responseObj).getDATA();
                NewsListAdapter newsListAdapter = new NewsListAdapter(datas, getContext());
                lv.setAdapter(newsListAdapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), WebsiteActivity.class);
                        intent.putExtra(WebsiteActivity.TITLE_NAME, datas.get(position).getTitle());
                        intent.putExtra(WebsiteActivity.URL, datas.get(position).getUrl());
                        startActivity(intent);
                    }
                });

                show();
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });


    }


    /**
     * 加载完成之后显示界面
     */
    private void show() {
        tv_nowpage.setText(String.valueOf(nowPage));
        tv_localpage.setText(String.valueOf(localPage));
        lv.setVisibility(View.VISIBLE);
        ll_pb.setVisibility(View.GONE);
        ll_page.setVisibility(View.VISIBLE);
    }
}
