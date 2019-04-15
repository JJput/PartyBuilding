package com.jj.app.dangjian.view.lookperiodical;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jj.app.dangjian.R;
import com.jj.app.dangjian.model.PeriodicalLookModel;
import com.jj.app.dangjian.okhttp.RequestCenter;
import com.jj.app.dangjian.okhttp.listener.DisposeDataListener;
import com.jj.app.dangjian.utils.DevUtils;
import com.jj.app.dangjian.utils.GlideUtils;
import com.jj.app.dangjian.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import github.jjput.mvpbaselibrary.base.IPresenter;

/**
 * @作者: JJ
 * @创建时间: 2019/4/12 7:35 PM
 * @Version 1.0
 * @描述:
 */
public class LookPeriodicalActivity extends BaseActivity {

    public static final String PID = "PID";
    public static final String NAME = "name";
    @BindView(R.id.look_per_vp)
    ViewPager mViewPager;
    @BindView(R.id.look_per_tv1)
    TextView tv1;
    @BindView(R.id.look_per_tv2)
    TextView tv2;

    private List<String> mList = new ArrayList<>();
    //用于ViewPager
    private List<View> mViews = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.look_periodical_activity;
    }

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();

        Intent intent = getIntent();
        String pid = intent.getStringExtra(PID);
        getTitleName().setText(intent.getStringExtra(NAME));
        RequestCenter.requsetPeriodicalLook(DevUtils.getDevID(), pid, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mList = ((PeriodicalLookModel) responseObj).getDATA().get(0).getFILEURL();

                tv1.setText("1");
                tv2.setText(mList.size() + "");
                //初始化viewpager的item view
                for (int i = 0; i < mList.size(); i++) {
                    mViews.add(View.inflate(LookPeriodicalActivity.this, R.layout.vp_head_imags_layout, null));
                }

                //设置适配器
                mViewPager.setAdapter(new GuideAdapter());
                mViewPager.setOffscreenPageLimit(mList.size());
                //监听ViewPager滑动
                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        tv1.setText((position + 1) + "");
                    }

                    //pager切换
                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });

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
            if (mList.get(position) != null && imageView != null) {
                GlideUtils.glideLoading(LookPeriodicalActivity.this,
                        mList.get(position),
                        imageView);
            }
            return mViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView(mViews.get(position));
            //super.destroyItem(container, position, object);
        }
    }
}
