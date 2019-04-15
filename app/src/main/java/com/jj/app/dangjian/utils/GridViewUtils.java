package com.jj.app.dangjian.utils;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.LinearLayout;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 6:57 PM
 * @Version 1.0
 * @描述:
 */
public class GridViewUtils {

    /**
     * 水平GridView设置
     *
     * @param size     Item总数
     * @param gridView 需要设置的GridView
     */
    public static void setHorizontalGridView(DisplayMetrics dm,int size, GridView gridView) {
        int length = size;
        //一个界面要显示的几个Item
        int anInterfaceNum = 4;
        //每个Item的间距(注:如果间距过大,但屏幕宽度不够,多出的部份会被无视)
        int spcing = 10;
        float density = dm.density;
        int leftAndRight = (int) (dm.widthPixels / dm.density / 10) / 2;
        //计算当个Item的宽度( 屏幕宽度 减去- 一个屏幕下要总item数量的间距之和 最后除/ 单个屏幕要显示几个Item)
        int itemWidth = (((dm.widthPixels) - leftAndRight * 2 - ((anInterfaceNum + 1) * spcing)) / anInterfaceNum);

        //
        //                int gridviewWidth = (int) (size * (length) * density)+((size-1)*30);
        //                int itemWidth = (int) ((length) * density);
        //笔者更具实际情况改写如下:
        //GridView总长度
        int gridviewWidth = (length * (itemWidth)) + ((length - 1) * spcing);

        @SuppressWarnings("deprecation")
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        //        params.leftMargin = leftAndRight;
        //        params.rightMargin = leftAndRight;
        // 设置GirdView布局参数,横向布局的关键
        gridView.setLayoutParams(params);
        // 设置列表项宽
        gridView.setColumnWidth(itemWidth);
        // 设置列表项水平间距
        gridView.setHorizontalSpacing(spcing);
        gridView.setStretchMode(GridView.NO_STRETCH);
        // 设置列数量=列表集合数
        gridView.setNumColumns(length);

    }
}
