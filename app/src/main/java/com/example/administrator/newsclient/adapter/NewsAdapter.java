package com.example.administrator.newsclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newsclient.R;
import com.example.administrator.newsclient.bean.NewsEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NewsAdapter extends BaseAdapter {


    private Context context;

    private List<NewsEntity.ResultBean> listDatas;

    public NewsAdapter(List<NewsEntity.ResultBean> listDatas, Context context) {
        this.context = context;
        this.listDatas = listDatas;
    }


    @Override
    public int getCount() {
        return (listDatas == null) ? 0 : listDatas.size();
    }

    @Override
    public NewsEntity.ResultBean getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        if (type == ITEM_TYPE_1_IMAGE) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_news_1, null);
            }
            ImageView iv01 = (ImageView) convertView.findViewById(R.id.iv_01);
            TextView tvNewsTitle = (TextView) convertView.findViewById(R.id.tv_news_title);
            TextView tvNewsFrom = (TextView) convertView.findViewById(R.id.tv_news_from);
            TextView tvCommentCount = (TextView) convertView.findViewById(R.id.tv_comment_count);

            NewsEntity.ResultBean news =  getItem(position);

            tvNewsTitle.setText(news.getTitle());
            tvNewsFrom.setText(news.getSource());
            tvCommentCount.setText(news.getReplyCount() + "跟帖");
            Picasso.with(context).load(news.getImgsrc()).into(iv01);

        }else {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_news_2, parent, false);
            }
            ImageView iv01 = (ImageView) convertView.findViewById(R.id.iv_01);
            ImageView iv02 = (ImageView) convertView.findViewById(R.id.iv_02);
            ImageView iv03 = (ImageView) convertView.findViewById(R.id.iv_03);
            TextView tvNewsTitle = (TextView) convertView.findViewById(R.id.tv_news_title);
            TextView tvCommentCount = (TextView) convertView.findViewById(R.id.tv_comment_count);

            NewsEntity.ResultBean news = getItem(position);

            tvNewsTitle.setText(news.getTitle());
            tvCommentCount.setText(news.getReplyCount()+"跟帖");

            Picasso.with(context).load(news.getImgsrc()).into(iv01);
            Picasso.with(context).load(news.getImgextra().get(0).getImgsrc()).into(iv02);
            Picasso.with(context).load(news.getImgextra().get(1).getImgsrc()).into(iv03);
        }
        return convertView;
    }


    //================列表显示多种类型的item(begin)=======================

    private static final int ITEM_TYPE_1_IMAGE = 0;

    private static final int ITEM_TYPE_3_IMAGE = 1;


    @Override
    public int getItemViewType(int position) {

        NewsEntity.ResultBean news = (NewsEntity.ResultBean) getItem(position);
        if (news.getImgextra() != null && news.getImgextra().size() > 0) {
            return ITEM_TYPE_3_IMAGE;
        } else {
            return ITEM_TYPE_1_IMAGE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

}
