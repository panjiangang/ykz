package xin.com.funtrek.framgments;

import android.view.View;

import javax.inject.Inject;

import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseFragment;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.picture.Picture_presenter;
import xin.com.funtrek.mvp.picture.Picture_view;
import xin.com.funtrek.mvp.video.Video_presenter;
import xin.com.funtrek.mvp.video.Video_view;

/**
 * date:2018/1/19  18:21
 * author:Mr.XIn💕
 */


public class Picture extends BaseFragment<Picture_view, Picture_presenter> implements Picture_view {
    @Inject
   Picture_presenter mVideoPresenter;

    @Override
    protected int setLayout() {
        return R.layout.picture_layout;
    }

    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().injectPicture(this);

    }

    @Override
    protected Picture_presenter getPresenter() {
        return mVideoPresenter;
    }

    @Override
    protected void initView(View mview) {

    }

    @Override
    protected void logic() {

    }
}
