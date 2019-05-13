package com.jj.app.dangjian.view.home;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jj.app.dangjian.R;
import com.jj.app.dangjian.adapter.HomeListAdapter;
import com.jj.app.dangjian.adapter.PeriodicalGridViewAdapter;
import com.jj.app.dangjian.custom.CustomDialog;
import com.jj.app.dangjian.custom.CustomViewPager;
import com.jj.app.dangjian.model.NewsListModel;
import com.jj.app.dangjian.model.PeriodicalOneModel;
import com.jj.app.dangjian.utils.DialogUtils;
import com.jj.app.dangjian.utils.GlideUtils;
import com.jj.app.dangjian.utils.GridViewUtils;
import com.jj.app.dangjian.view.listperiodical.ListPeriodicalActivity;
import com.jj.app.dangjian.view.newslist.NewsListActivity;
import com.jj.app.dangjian.view.scanperiodical.ScanPeriodicalActivity;
import com.jj.app.dangjian.view.webview.WebsiteActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.update.BmobUpdateAgent;
import github.jjput.mvpbaselibrary.base.BaseActivity;
import github.jjput.utils.BarUtils;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 11:01 AM
 * @Version 1.0
 * @描述: 首页
 */
public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {

    public static final String[] LIST_TITLE_NAME = {"横滚轮播图片", "党建动态", "政策文件", "基层党建", "党建期刊"};
    public static final String CONNECT_INTERNET_TIME_OUT = "time out";
    //间隔时间
    private static int PAGER_TIOME = 1000 * 10;

    /**
     * 头部横滚图片 使用ViewPager
     */
    @BindView(R.id.home_head_cvp)
    CustomViewPager mViewPager;
    @BindView(R.id.home_head_images_tv_title)
    TextView tvHeadImagesTitle;
    @BindView(R.id.home_head_images_tv_page)
    TextView tvHeadImagesPage;
    @BindView(R.id.home_head_images_tv_thispage)
    TextView tvHeadImagesThisPage;

    /**
     * 党建期刊 使用横滚GridView + HorizontalScrollView
     */
    @BindView(R.id.home_hsv)
    HorizontalScrollView mhScrollView;
    @BindView(R.id.home_gv)
    GridView mGridView;

    /**
     * 党建动态listView
     */
    @BindView(R.id.home_lv_trends)
    ListView lvTrends;
    /**
     * 基层党建listView
     */
    @BindView(R.id.home_lv_grass_roots)
    ListView lvGrassRoots;
    /**
     * 基层党建左侧图片
     */
    @BindView(R.id.home_iv_grass_roots)
    ImageView ivGrassRoots;
    /**
     * 政策文件listView
     */
    @BindView(R.id.home_lv_zfile)
    ListView lvZfile;


    /**
     * 加载Dialog
     */
    private CustomDialog mLoadingDialog;

    //用于ViewPager
    private List<View> mViews = new ArrayList<>();
    //期刊适配器
    private PeriodicalGridViewAdapter mGridViewAdapter;
    //党建动态适配器
    private HomeListAdapter adapterThrends;
    //基层党建适配器
    private HomeListAdapter adapterGrassRoots;
    //政府文件适配器
    private HomeListAdapter adapterZfile;

    //各个listview、GridView数据容器
    private List<PeriodicalOneModel.DATABean> mListPer;
    private List<NewsListModel.DATABean> mListHeadImags;
    private List<NewsListModel.DATABean> mListThrends;
    private List<NewsListModel.DATABean> mListGrassRoots;
    private List<NewsListModel.DATABean> mListZfile;

    /**
     * 记录id号
     */
    private String idThrends;
    private String idGrassRoots;
    private String idZfile;

    /**
     * 总计新闻数量
     */
    private int countThrends;
    private int countGrassRoots;
    private int countZfile;
    /**
     * 用于记录数据是否都完成加载
     */
    private static final int LOAD_SUCCESS = 0;
    private static final int LOAD_FAIL = 1;
    private int count = 0;
    private String errCode = "";
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (++count >= 5) {
                mLoadingDialog.dismiss();
                if (!TextUtils.isEmpty(errCode)) {
                    DialogUtils.showErrorDialog(HomeActivity.this, errCode, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                }
            }
            if (LOAD_FAIL == msg.what) {
                if (TextUtils.isEmpty(errCode)) {
                    errCode = "以下几项加载数据失败!请联系管理员。";
                }
                errCode += "\n * " + msg.obj.toString();
            }
        }
    };

    public void setIdThrends(String idThrends) {
        this.idThrends = idThrends;
    }

    public void setIdGrassRoots(String idGrassRoots) {
        this.idGrassRoots = idGrassRoots;
    }

    public void setIdZfile(String idZfile) {
        this.idZfile = idZfile;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    protected HomeContract.Presenter initInjector() {
        return new HomePresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        BarUtils.setStatusBarVisibility(this, false);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 设置仅WiFi环境更新
        BmobUpdateAgent.update(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mPresenter.getPeriodicalList();
        mPresenter.getFindAllCategory();
        //        mPresenter.getTrendsData();
        //        mPresenter.getGrassRootsData();
        //        mPresenter.getZFileData();
        //        mPresenter.getHeadImagesData();

        mLoadingDialog = new CustomDialog(this, R.layout.dialog_loading_layout, R.style.Theme_dialog);
        //        mLoadingDialog.setCancelable(false);
        mLoadingDialog.show();
    }

    /**
     * 头部 横滚图片
     *
     * @param newsListModel
     */
    @Override
    public void showHeadImags(NewsListModel newsListModel) {
        mListHeadImags = newsListModel.getDATA();
        //设置总页数
        tvHeadImagesPage.setText(mListHeadImags.size() + "");
        //默认当前为第一幅图
        tvHeadImagesThisPage.setText("1");
        //获取第一个图片的标题
        tvHeadImagesTitle.setText(mListHeadImags.get(0).getTitle());
        //初始化viewpager的item view
        for (int i = 0; i < mListHeadImags.size(); i++) {
            mViews.add(View.inflate(this, R.layout.vp_head_imags_layout, null));
        }

        //设置适配器
        mViewPager.setAdapter(new GuideAdapter());
        mViewPager.setOffscreenPageLimit(mListHeadImags.size());
        //监听ViewPager滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvHeadImagesThisPage.setText((position + 1) + "");
                tvHeadImagesTitle.setText(mListHeadImags.get(position).getTitle());
            }

            //pager切换
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        autoPlayView();
        mHandler.sendEmptyMessage(LOAD_SUCCESS);
    }


    /**
     * 党建动态
     *
     * @param newsListModel
     */
    @Override
    public void showLvTrends(NewsListModel newsListModel) {
        mListThrends = newsListModel.getDATA();
        countThrends = newsListModel.getCount();
        adapterThrends = new HomeListAdapter(mListThrends, this);
        lvTrends.setAdapter(adapterThrends);
        lvTrends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, WebsiteActivity.class);
                intent.putExtra(WebsiteActivity.TITLE_NAME, mListThrends.get(position).getTitle());
                intent.putExtra(WebsiteActivity.URL, mListThrends.get(position).getUrl());
                startActivity(intent);
            }
        });
        mHandler.sendEmptyMessage(LOAD_SUCCESS);
    }

    /**
     * 政策文件
     *
     * @param newsListModel
     */
    @Override
    public void showLvZFile(NewsListModel newsListModel) {
        mListZfile = newsListModel.getDATA();
        countZfile = newsListModel.getCount();
        adapterZfile = new HomeListAdapter(mListZfile, this);
        lvZfile.setAdapter(adapterZfile);
        lvZfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, WebsiteActivity.class);
                intent.putExtra(WebsiteActivity.TITLE_NAME, mListZfile.get(position).getTitle());
                intent.putExtra(WebsiteActivity.URL, mListZfile.get(position).getUrl());
                startActivity(intent);
            }
        });
        mHandler.sendEmptyMessage(LOAD_SUCCESS);
    }


    /**
     * 基层党建
     *
     * @param newsListModel
     */
    @Override
    public void showLvGrassRoots(NewsListModel newsListModel) {
        mListGrassRoots = newsListModel.getDATA();
        countGrassRoots = newsListModel.getCount();
        adapterGrassRoots = new HomeListAdapter(mListGrassRoots, this);
        lvGrassRoots.setAdapter(adapterGrassRoots);
        lvGrassRoots.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, WebsiteActivity.class);
                intent.putExtra(WebsiteActivity.TITLE_NAME, mListGrassRoots.get(position).getTitle());
                intent.putExtra(WebsiteActivity.URL, mListGrassRoots.get(position).getUrl());
                startActivity(intent);
            }
        });

        GlideUtils.glideLoading(HomeActivity.this,
                mListGrassRoots.get(0).getImg(),
                ivGrassRoots);
        mHandler.sendEmptyMessage(LOAD_SUCCESS);
    }

    /**
     * 党建期刊
     *
     * @param periodicalOneModel
     */
    @Override
    public void showPeriodicalList(PeriodicalOneModel periodicalOneModel) {
        mListPer = new ArrayList<>();
        mListPer = periodicalOneModel.getDATA();

        //测试数据
        //        for (int i = 0; i < 10; i++) {
        //            PeriodicalOneModel.DATABean dataBean = new PeriodicalOneModel.DATABean();
        //            dataBean.setNAME(i + "");
        //            mListPer.add(dataBean);
        //        }


        GridViewUtils.setHorizontalGridView(getResources().getDisplayMetrics(),
                mListPer.size(),
                mGridView);
        mGridViewAdapter = new PeriodicalGridViewAdapter(HomeActivity.this, mListPer);
        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, ScanPeriodicalActivity.class);
                intent.putExtra(ScanPeriodicalActivity.ID, mListPer.get(position).getID());
                intent.putExtra(ScanPeriodicalActivity.TITLE, mListPer.get(position).getNAME());
                startActivity(intent);
            }
        });

        mHandler.sendEmptyMessage(LOAD_SUCCESS);
    }

    @Override
    public void getDataFail(String name) {
        if (name.equals(CONNECT_INTERNET_TIME_OUT)) {
            DialogUtils.showErrorDialog(HomeActivity.this, "请检查网络连接", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //跳转wifi设置
                    startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                }
            }).show();
        } else {
            Message msg = new Message();
            msg.what = LOAD_FAIL;
            msg.obj = name;
            mHandler.sendMessage(msg);
        }
    }


    /**
     * 党建期刊 左键
     */
    @OnClick(R.id.home_ib_left)
    public void vpLeft() {
        mhScrollView.pageScroll(ScrollView.FOCUS_LEFT);
    }

    /**
     * 党建期刊 右键
     */
    @OnClick(R.id.home_ib_right)
    public void vpRight() {
        mhScrollView.pageScroll(ScrollView.FOCUS_RIGHT);
    }

    /**
     * 党建期刊 更多
     */
    @OnClick(R.id.home_tv_per_more)
    public void perMore() {
        Intent intent = new Intent(HomeActivity.this, ListPeriodicalActivity.class);
        Gson gs = new Gson();
        PeriodicalOneModel model = new PeriodicalOneModel();
        model.setDATA(mListPer);
        String objectStr = gs.toJson(model);
        intent.putExtra(ListPeriodicalActivity.DATA, objectStr);
        startActivity(intent);
    }

    /**
     * 党建动态 更多
     */
    @OnClick(R.id.home_tv_trends)
    public void tvTrends() {
        Intent intent = new Intent(HomeActivity.this, NewsListActivity.class);
        intent.putExtra(NewsListActivity.TITLE_NAME, LIST_TITLE_NAME[1]);
        intent.putExtra(NewsListActivity.CATEGORY_ID, idThrends);
        intent.putExtra(NewsListActivity.COUNT, countThrends);
        startActivity(intent);
    }

    /**
     * 政策文件 更多
     */
    @OnClick(R.id.home_tv_zfile)
    public void tvZfile() {
        Intent intent = new Intent(HomeActivity.this, NewsListActivity.class);
        intent.putExtra(NewsListActivity.TITLE_NAME, LIST_TITLE_NAME[2]);
        intent.putExtra(NewsListActivity.CATEGORY_ID, idZfile);
        intent.putExtra(NewsListActivity.COUNT, countZfile);
        startActivity(intent);
    }

    /**
     * 基层党建 更多
     */
    @OnClick(R.id.home_tv_grass_roots)
    public void tvGrassRoots() {
        Intent intent = new Intent(HomeActivity.this, NewsListActivity.class);
        intent.putExtra(NewsListActivity.TITLE_NAME, LIST_TITLE_NAME[3]);
        intent.putExtra(NewsListActivity.CATEGORY_ID, idGrassRoots);
        intent.putExtra(NewsListActivity.COUNT, countGrassRoots);
        startActivity(intent);
    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(mViews.get(position));
            ImageView imageView = mViews.get(position).findViewById(R.id.vp_head_images_item_iv);
            if (mListHeadImags.get(position).getImg() != null && imageView != null) {
                GlideUtils.glideLoading(HomeActivity.this,
                        mListHeadImags.get(position).getImg(),
                        imageView);
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, WebsiteActivity.class);
                    intent.putExtra(WebsiteActivity.TITLE_NAME, mListHeadImags.get(position).getTitle());
                    intent.putExtra(WebsiteActivity.URL, mListHeadImags.get(position).getUrl());
                    startActivity(intent);
                }
            });
            return mViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView(mViews.get(position));
            //super.destroyItem(container, position, object);
        }
    }

    private boolean isStop = true;

    /**
     * 第五步: 设置自动播放,每隔PAGER_TIOME秒换一张图片
     */
    private void autoPlayView() {
        //自动播放图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isStop) {
                    SystemClock.sleep(PAGER_TIOME);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mViewPager.getCurrentItem() + 1 == mListHeadImags.size())
                                mViewPager.setCurrentItem(0);
                            else
                                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);

                        }
                    });
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isStop = false;
    }
}
