package xin.com.funtrek.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseActivity;
import xin.com.funtrek.http.bean.UpPic;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.collect.Collect_presenter;
import xin.com.funtrek.mvp.collect.Collect_view;

//收藏
public class CollectActivity extends BaseActivity<Collect_view,Collect_presenter>implements Collect_view {
@Inject
Collect_presenter mCollect_presenter;
    @Override
    protected int setLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().inject_Collect(this);

    }

    @Override
    protected Collect_presenter getPresenter() {
        return mCollect_presenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void logic() {

    }

    @Override
    public void showData(UpPic bean) {

    }
}
