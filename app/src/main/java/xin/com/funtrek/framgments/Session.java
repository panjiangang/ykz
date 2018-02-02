package xin.com.funtrek.framgments;

import android.view.View;

import javax.inject.Inject;

import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseFragment;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.session.Session_presenter;
import xin.com.funtrek.mvp.session.Session_view;

/**
 * date:2018/1/19  18:21
 * author:Mr.XIn💕
 */


public class Session extends BaseFragment<Session_view,Session_presenter>implements Session_view {
  @Inject
  Session_presenter mSession_presenter;
    @Override
    protected int setLayout() {
        return R.layout.session_layout;
    }

    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().injectSession(this);

    }

    @Override
    protected Session_presenter getPresenter() {
        return mSession_presenter;
    }

    @Override
    protected void initView(View mview) {
    }

    @Override
    protected void logic() {

    }
}
