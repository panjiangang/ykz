package xin.com.funtrek.framgments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseFragment;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.picture.Picture_presenter;
import xin.com.funtrek.mvp.picture.Picture_view;

/**
 * date:2018/1/19  18:21
 * author:Mr.XIn💕
 */


public class Picture extends BaseFragment<Picture_view, Picture_presenter> implements Picture_view {
    @Inject
    Picture_presenter mVideoPresenter;
    @BindView(R.id.ss)
    TextView mSs;

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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
