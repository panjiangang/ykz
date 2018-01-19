package xin.com.funtrek.activitys;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseActivity;
import xin.com.funtrek.framgments.Picture;
import xin.com.funtrek.framgments.Recommend;
import xin.com.funtrek.framgments.Session;
import xin.com.funtrek.framgments.Video;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.main.Main_presenter;
import xin.com.funtrek.mvp.main.Main_view;

public class MainActivity extends BaseActivity<Main_view, Main_presenter> implements Main_view {
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
    @BindView(R.id.frame)
    FrameLayout mFrame;
    @BindView(R.id.navigatbar)
    BottomNavigationBar mNavigatbar;
    @BindView(R.id.navigatview)
    NavigationView mNavigatview;
    @BindView(R.id.main_drawlayout)
    DrawerLayout mMainDrawlayout;
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
        ButterKnife.bind(this);
        if (mRecommend == null) {
            mRecommend = new Recommend();
        }
        changeFragment(mRecommend);
        mNavigatbar.setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor("#FF0888E9")
                .setInActiveColor("#B3B3B3")
                .addItem(new BottomNavigationItem(R.drawable.tuijian_select, "推荐")
                        .setInactiveIconResource(R.drawable.tuijian_default))
                .addItem(new BottomNavigationItem(R.drawable.duanzi_select, "段子")
                        .setInactiveIconResource(R.drawable.duanzi_default))
                .addItem(new BottomNavigationItem(R.drawable.video_select, "视频")
                        .setInactiveIconResource(R.drawable.video_defaults))
                .addItem(new BottomNavigationItem(R.drawable.video_defaults, "趣图")
                        .setInactiveIconResource(R.drawable.video_select))
                .initialise();

    }

    @Override
    protected void logic() {
        mNavigatbar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {

            private Picture mPicture;

            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        if (mRecommend == null) {
                            mRecommend = new Recommend();
                        }
                        changeFragment(mRecommend);
                        mTitle.setText("推荐");

                        break;
                    case 1:
                        if (mSession == null) {
                            mSession = new Session();
                        }
                        changeFragment(mSession);
                        mTitle.setText("段子");
                        break;
                    case 2:
                        if (mVideo == null) {
                            mVideo = new Video();
                        }
                        changeFragment(mVideo);
                        mTitle.setText("视频");
                        break;
                    case 3:
                        if (mPicture == null) {
                            mPicture = new Picture();
                        }
                        changeFragment(mPicture);
                        mTitle.setText("趣图");
                        break;
                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

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

    public void changeFragment(Fragment ment) {

        manager = getSupportFragmentManager();
        mTransaction = manager.beginTransaction();
        if (fm != null) {
            mTransaction.hide(fm);
        }
        // isAdded:是否被添加过 被添加过 is true 反之
        if (!ment.isAdded()) {
            // 调用replace 添加fragment
            mTransaction.add(R.id.frame, ment);
        } else {
            // 显示出来
            mTransaction.show(ment);
        }
        mTransaction.commit();

        // 记录当前的Fragment
        fm = ment;
    }

}
