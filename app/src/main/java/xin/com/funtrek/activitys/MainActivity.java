package xin.com.funtrek.activitys;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjm.bottomtabbar.BottomTabBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseActivity;
import xin.com.funtrek.framgments.Recommend;
import xin.com.funtrek.framgments.Session;

import xin.com.funtrek.framgments.Video;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.main.Main_presenter;
import xin.com.funtrek.mvp.main.Main_view;

public class MainActivity extends BaseActivity<Main_view, Main_presenter> implements Main_view, BottomTabBar.OnTabChangeListener {
    @Inject
    Main_presenter mMain_presenter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.user_image1)
    ImageView mUserImage1;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.publish)
    ImageView mPublish;
    @BindView(R.id.navigatbar)
    BottomTabBar mNavigatbar;
    @BindView(R.id.main_drawlayout)
    DrawerLayout mMainDrawlayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    private Recommend mRecommend;
    private Session mSession;
    private Video mVideo;
    private FragmentTransaction mTransaction;
    private FragmentManager manager;
    private Fragment fm;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().injectMain(this);
    }

    @Override
    protected Main_presenter getPresenter() {
        return mMain_presenter;
    }

    @Override
    protected void initView() {

        mNavigatbar.init(getSupportFragmentManager())
                .addTabItem("推荐", R.drawable.tuijian_select, R.drawable.tuijian_default, Recommend.class)
                .addTabItem("段子", R.drawable.duanzi_select, R.drawable.duanzi_default, Session.class)
                .addTabItem("视频", R.drawable.video_select, R.drawable.video_defaults, Video.class);
    }

    @Override
    protected void logic() {
        mNavigatbar.setOnTabChangeListener(this);
    }

    @OnClick({R.id.user_image1, R.id.publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_image1:
                mMainDrawlayout.openDrawer(GravityCompat.START);
                break;
            case R.id.publish:
                //发表
                break;
        }
    }

    @Override
    public void onTabChange(int position, String name) {
        mTitle.setText(name);
    }
}
