package com.example.administrator.newsclient.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.administrator.newsclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */

public class MainFragment1 extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void initData() {


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        viewPager = (ViewPager) super.mRootView.findViewById(R.id.view_pager);
        tabLayout = (TabLayout) super.mRootView.findViewById(R.id.tab_layout);

        initViewPager();
    }

    private void initViewPager() {
        final String[] titles = new String[]{"头条", "社会", "科技", "财经", "体育", "汽车"};
        final String[] channelIds = new String[]{
                "T1348647909107",
                "T1348648037603",
                "T1348649580692",
                "T1348648756099",
                "T1348649079062",
                "T1348654060988",
        };

        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            NewsItemFragment fragment = new NewsItemFragment();
            fragment.setNewsCategoryId(channelIds[i]);
            fragments.add(fragment);
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_01;
    }
}
