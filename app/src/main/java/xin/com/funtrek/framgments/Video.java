package xin.com.funtrek.framgments;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseFragment;
import xin.com.funtrek.framgments.videofragments.HotFragment;
import xin.com.funtrek.framgments.videofragments.VearbyFragment;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.video.Video_presenter;
import xin.com.funtrek.mvp.video.Video_view;

/**
 * date:2018/1/19  18:21
 * author:Mr.XIn💕
 */


public class Video extends BaseFragment {
    @Inject
    Video_presenter mVideoPresenter;
    List<String> list = new ArrayList<>();
    TabLayout tab;
    ViewPager pager;
    @Override
    protected int setLayout() {
        return R.layout.video_layout;

    }

    @Override
    protected void bridge() {

    }

    @Override
    protected Video_presenter getPresenter() {
        return null;
    }
    @Override
    protected void initView(View mview) {

        list.add("热门");
        list.add("附近");
        tab = mview.findViewById(R.id.tab);
        pager = mview.findViewById(R.id.pager);

    }
    @Override
    protected void logic() {
        pager.setOffscreenPageLimit(list.size());
        tab.setupWithViewPager(pager,false);
        pager.setAdapter(new FragmentPagerAdapter(this.getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment =null;
                switch (position){
                    case 0:
                        fragment = new HotFragment();
                        break;
                    case 1:
                        fragment = new VearbyFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }
        });
    }


}
