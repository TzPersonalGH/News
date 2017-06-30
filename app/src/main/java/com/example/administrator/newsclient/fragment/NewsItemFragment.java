package com.example.administrator.newsclient.fragment;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.administrator.newsclient.NewsDetailActivity;
import com.example.administrator.newsclient.R;
import com.example.administrator.newsclient.adapter.NewsAdapter;
import com.example.administrator.newsclient.base.URLManager;
import com.example.administrator.newsclient.bean.NewsEntity;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NewsItemFragment extends BaseFragment {
    private TextView textView;
    private String newsCategoryId;//新闻类别Id
    private ListView listView;
    private List<NewsEntity.ResultBean> listDatas;


    public void setNewsCategoryId(String newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    @Override
    public void initData() {
        getNewsDatas();
    }

    private void getNewsDatas() {

        String newsUrl = URLManager.getUrl(newsCategoryId);
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, newsUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                System.out.println("================json:" + json);

                json = json.replace(newsCategoryId, "result");
                Gson gson = new Gson();
                NewsEntity newsEntity = gson.fromJson(json, NewsEntity.class);
                System.out.println("================解析:" + newsEntity.getResult().size() + "" + "条新闻");
                List<NewsEntity.ResultBean> listDatas = newsEntity.getResult();
                showListView(listDatas);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
            }
        });

    }

    private void showListView(List<NewsEntity.ResultBean> listDatas) {

        NewsEntity.ResultBean firstNews = listDatas.get(0);

        if (firstNews.getAds() != null && firstNews.getAds().size() > 0) {
            View headerView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_header, listView, false);

            SliderLayout sliderLayout = (SliderLayout) headerView.findViewById(R.id.slider_layout);

            List<NewsEntity.ResultBean.AdsBean> ads = firstNews.getAds();

            for (int i = 0; i < ads.size(); i++) {
                NewsEntity.ResultBean.AdsBean bean = ads.get(i);

                TextSliderView textSliderView = new TextSliderView(getContext());
                textSliderView.description(bean.getTitle()).image(bean.getImgsrc());

                sliderLayout.addSlider(textSliderView);
            }
            listView.addHeaderView(headerView);
        } else {
            //没有轮播图
        }

        NewsAdapter newsAdapter = new NewsAdapter(listDatas, getContext());
        listView.setAdapter(newsAdapter);

    }

    @Override
    public void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override  // parent: 指ListView
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //NewsEntity.ResultBean news = listDatas.get(position);

                 NewsEntity.ResultBean news = (NewsEntity.ResultBean)
                         parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("news", news);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initView() {
        textView = (TextView) mRootView.findViewById(R.id.tv_01);
        listView = (ListView) mRootView.findViewById(R.id.list_view);
        textView.setText("类别id:" + newsCategoryId);

        initSpringView();
    }

    private void initSpringView() {
        final SpringView springView = (SpringView) mRootView.findViewById(R.id.spring_view);


//      springView.setHeader(new AcFunHeader(getContext(), R.drawable.ad_new_version1_img1));
        springView.setHeader(new MeituanHeader(getContext()));
        springView.setFooter(new DefaultFooter(getContext()));

        // springView.setType(SpringView.Type.OVERLAP);
        springView.setType(SpringView.Type.FOLLOW);

        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                showToast("下拉");


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore() {
                showToast("上拉");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        springView.onFinishFreshAndLoad();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news_item;
    }
}
