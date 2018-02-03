package xin.com.funtrek.activitys;

import javax.inject.Inject;

import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseActivity;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.myconcern.MyConcern_presenter;
import xin.com.funtrek.mvp.myconcern.MyConcern_view;

/**
 * date:2018/2/3  10:11
 * author:Mr.XIn💕
 */


public class MyConcern extends BaseActivity<MyConcern_view, MyConcern_presenter> implements MyConcern_view {


    @Inject
    MyConcern_presenter  mMyConcern_presenter;

    @Override
    protected int setLayout() {
        return R.layout.myconcern_activity;
    }

    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().injectMyConcern(this);

    }

    @Override
    protected MyConcern_presenter getPresenter() {
        return mMyConcern_presenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void logic() {

    }
}
