package xin.com.funtrek.framgments;

import android.view.View;

import javax.inject.Inject;

import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseFragment;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.recommend.Recommend_presenter;
import xin.com.funtrek.mvp.session.Session_presenter;
import xin.com.funtrek.mvp.session.Session_view;
import xin.com.funtrek.mvp.video.Video_presenter;
import xin.com.funtrek.mvp.video.Video_view;

/**
 * date:2018/1/19  18:21
 * author:Mr.XIn💕
 */


public class Video extends BaseFragment<Video_view, Video_presenter> implements Video_view {
    @Inject
    Video_presenter mVideoPresenter;

    @Override
    protected int setLayout() {
        return R.layout.video_layout;
    }

    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().injectVideo(this);

    }

    @Override
    protected Video_presenter getPresenter() {
        return mVideoPresenter;
    }

    @Override
    protected void initView(View mview) {

    }

    @Override
    protected void logic() {

    }
}
