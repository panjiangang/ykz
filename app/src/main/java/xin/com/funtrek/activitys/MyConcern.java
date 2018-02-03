package xin.com.funtrek.activitys;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

//我的关注页面
public class MyConcern extends BaseActivity<MyConcern_view, MyConcern_presenter> implements MyConcern_view {


    @Inject
    MyConcern_presenter mMyConcern_presenter;
    @BindView(R.id.setting_back)
    LinearLayout mSettingBack;
    @BindView(R.id.title)
    TextView mTitle;

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
        ButterKnife.bind(this);
        mTitle.setText("我的关注");


    }

    @Override
    protected void logic() {

    }


    @OnClick(R.id.setting_back)
    public void onViewClicked() {
        finish();
    }
}
