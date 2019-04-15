
package com.jj.app.dangjian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jj.app.dangjian.R;
import com.jj.app.dangjian.model.PeriodicalOneModel;
import com.jj.app.dangjian.utils.GlideUtils;

import java.util.List;


/**
 * @作者: JJ
 * @创建时间: 2018/7/14 下午3:21
 * @Version 1.0
 * @描述: 设备Fragment GridView适配器
 */
public class PeriodicalGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<PeriodicalOneModel.DATABean> mList;

    public PeriodicalGridViewAdapter(Context context, List<PeriodicalOneModel.DATABean> mList) {
        this.mContext = context;
        this.mList = mList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.gv_home_per_layout, null);
            viewHolder.ivImage = convertView.findViewById(R.id.home_gv_item_iv);
            viewHolder.tvTitle = convertView.findViewById(R.id.home_gv_item_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PeriodicalOneModel.DATABean dataBean = mList.get(position);
        //网络加载图片
        if (dataBean.getPICURL() != null) {
            //            Glide.with(mContext)
            //                    .load(dataBean.getPICURL())
            //                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            //                    .into(viewHolder.ivImage);
            GlideUtils.glideLoading(mContext, dataBean.getPICURL(), viewHolder.ivImage);
        }
        viewHolder.tvTitle.setText(dataBean.getNAME());
        return convertView;
    }


    class ViewHolder {
        private TextView tvTitle;
        private ImageView ivImage;
    }

}
