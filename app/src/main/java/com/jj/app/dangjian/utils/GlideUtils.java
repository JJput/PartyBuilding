package com.jj.app.dangjian.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @作者: JJ
 * @创建时间: 2019/4/11 10:52 PM
 * @Version 1.0
 * @描述:
 */
public class GlideUtils {

    public static final void glideLoading(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                //                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(imageView);
    }
}
