package com.jj.app.dangjian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jj.app.dangjian.R;
import com.jj.app.dangjian.model.NewsListModel;

import java.util.List;

public class NewsListAdapter extends BaseAdapter {
    private List<NewsListModel.DATABean> list = null;
    private Context context = null;

    public NewsListAdapter(List<NewsListModel.DATABean> list, Context context) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder item = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.newslist_fragment_lv_layout, null);
            item = new ViewHolder();
            item.tvTitle = (TextView) convertView.findViewById(R.id.newslist_fagment_lv_item_tv_title);
            item.tvTime = (TextView) convertView.findViewById(R.id.newslist_fagment_lv_item_tv_time);

            convertView.setTag(item);//绑定ViewHolder对象
        } else {
            item = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }

        item.tvTitle.setText(list.get(position).getTitle());
        item.tvTime.setText(list.get(position).getTime());
        /**设置TextView显示的内容，即我们存放在动态数组中的数据*/


        return convertView;
    }


    private class ViewHolder {
        public TextView tvTitle;
        public TextView tvTime;
    }

}