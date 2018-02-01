package xin.com.funtrek.framgments;

import android.view.View;

import javax.inject.Inject;

import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseFragment;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.recommend.Recommend_presenter;
import xin.com.funtrek.mvp.recommend.Recommend_view;
/**
 * date:2018/1/19  18:21
 * author:Mr.XIn💕
 */
public class Recommend extends BaseFragment<Recommend_view,Recommend_presenter>implements Recommend_view {
  @Inject
  Recommend_presenter mRecommend_presenter;
    @Override
    protected int setLayout() {
        return R.layout.recommend_layout;
    }

    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().injectRecommend(this);

    }

    @Override
    protected Recommend_presenter getPresenter() {
        return mRecommend_presenter;
    }

    @Override
    protected void initView(View mview) {
    }

    @Override
    protected void logic() {

    }
}
