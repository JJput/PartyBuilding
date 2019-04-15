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

public class HomeListAdapter extends BaseAdapter {
    private List<NewsListModel.DATABean> list = null;
    private Context context = null;

    public HomeListAdapter(List<NewsListModel.DATABean> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_home_layout, null);
            item = new ViewHolder();
            item.tv = (TextView) convertView.findViewById(R.id.lv_home_item_tv);

            convertView.setTag(item);//绑定ViewHolder对象
        } else {
            item = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        /**设置TextView显示的内容，即我们存放在动态数组中的数据*/
        item.tv.setText(list.get(position).getTitle());


        return convertView;
    }


    private class ViewHolder {
        public TextView tv;
    }

}