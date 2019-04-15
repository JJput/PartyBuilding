package com.jj.app.dangjian.view.newslist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jj.app.dangjian.R;
import com.jj.app.dangjian.view.BaseActivity;
import com.jj.app.dangjian.view.newslist.fragment.NewsListItemFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.jjput.mvpbaselibrary.base.IPresenter;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 9:49 PM
 * @Version 1.0
 * @描述: 点击党建动态 - 政策文件 - 基层党建 的更多时跳转此界面
 */
public class NewsListActivity extends BaseActivity {
    //总页数
    private static int LOCAL = 1;

    public static final String TITLE_NAME = "title";
    public static final String CATEGORY_ID = "categoryId";
    public static final String COUNT = "count";

    @BindView(R.id.newslist_vp)
    ViewPager mViewPager;

    //当前页
    private int nowPage = 1;
    private FragmentManager manager = getSupportFragmentManager();
    private Map<Integer, Fragment> fragmentMap;
    private Fragment f1, f2, f3;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.newslist_activity;
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        getTitleName().setText(intent.getStringExtra(TITLE_NAME));
        int count = intent.getIntExtra(COUNT, 999);
        //        if (count < NewsListItemFragment.PAGE_NUM)
        //            LOCAL = 1;
        //        else
        if (count % NewsListItemFragment.PAGE_NUM == 0) {
            LOCAL = count / NewsListItemFragment.PAGE_NUM;
        } else {
            LOCAL = count / NewsListItemFragment.PAGE_NUM + 1;
        }
        id = intent.getStringExtra(CATEGORY_ID);

        fragmentMap = new HashMap<>();
        f1 = new NewsListItemFragment(nowPage, LOCAL, id);
        f2 = new NewsListItemFragment(nowPage + 1, LOCAL, id);
        f3 = new NewsListItemFragment(nowPage + 2, LOCAL, id);
        fragmentMap.put(1, f1);
        fragmentMap.put(2, f2);
        fragmentMap.put(3, f3);


        FragmentPagerAdapter adapter = new FragmentPagerAdapter(manager) {
            @Override
            public Fragment getItem(int i) {
                return fragmentMap.get(i + 1);
            }

            @Override
            public int getCount() {
                return LOCAL;
            }
        };

        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {


            }

            @Override
            public void onPageSelected(int i) {
                nowPage = i + 1;
                //不超过
                if (i - 1 > 0 || i + 1 <= LOCAL) {

                    f1 = new NewsListItemFragment(nowPage - 1, LOCAL, id);
                    f2 = new NewsListItemFragment(nowPage, LOCAL, id);
                    f3 = new NewsListItemFragment(nowPage + 1, LOCAL, id);

                    fragmentMap.put(nowPage - 1, f1);
                    fragmentMap.put(nowPage, f2);
                    fragmentMap.put(nowPage + 1, f3);
                    if (fragmentMap.get(nowPage - 2) != null) {
                        fragmentMap.remove(nowPage - 2);
                    }
                    if (fragmentMap.get(nowPage + 2) != null) {
                        fragmentMap.remove(nowPage + 2);
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
