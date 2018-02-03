package xin.com.funtrek.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseActivity;
import xin.com.funtrek.framgments.Recommend;
import xin.com.funtrek.framgments.Session;
import xin.com.funtrek.framgments.Video;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.login.Login_view;
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
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavBar;
    @BindView(R.id.nav_view2)
    NavigationView mNavView2;
    @BindView(R.id.main_drawlayout)
    DrawerLayout mMainDrawlayout;
    private Recommend mRecommend;
    private Session mSession;
    private Video mVideo;
    private FragmentManager manager;
    private Fragment fm;
    private FragmentTransaction mTransaction1;
    private Recommend mRecommend1;
    private LinearLayout mWorks;
    private LinearLayout mSetting;
    private ImageView mUser_img;

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
        if (mRecommend == null) {
            mRecommend = new Recommend();
        }
        changeFragment(mRecommend);
        navBar();
    }

    @Override
    protected void logic() {

        mySide();

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
        mTransaction1 = manager.beginTransaction();
        if (fm != null) {
            mTransaction1.hide(fm);
        }
        // isAdded:是否被添加过 被添加过 is true 反之
        if (!ment.isAdded()) {
            // 调用replace 添加fragment
            mTransaction1.add(R.id.frame, ment);
        } else {
            // 显示出来
            mTransaction1.show(ment);
        }
        mTransaction1.commit();

        // 记录当前的Fragment
        fm = ment;
    }

    public void navBar() {
        mBottomNavBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor("#FF0888E9")
                .setInActiveColor("#B3B3B3")
                .addItem(new BottomNavigationItem(R.drawable.tuijian_select, "推荐")
                        .setInactiveIconResource(R.drawable.tuijian_default))
                .addItem(new BottomNavigationItem(R.drawable.duanzi_select, "段子")
                        .setInactiveIconResource(R.drawable.duanzi_default))
                .addItem(new BottomNavigationItem(R.drawable.video_select, "视频")
                        .setInactiveIconResource(R.drawable.video_defaults))
                .initialise();
        mBottomNavBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
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

    public void mySide() {
        navView.setItemIconTintList(null);

        mUser_img = navView.getHeaderView(0)
                .findViewById(R.id.user_image);
        mUser_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login_view.class));
            }
        });
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.attention:
                        Toast.makeText(MainActivity.this, "关注", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.collect:
                        Toast.makeText(MainActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.friends:
                        Toast.makeText(MainActivity.this, "好友", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.message:
                        Toast.makeText(MainActivity.this, "通知", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        mWorks = mNavView2.getHeaderView(0).findViewById(R.id.works);
        mWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "本地", Toast.LENGTH_SHORT).show();

            }
        });
        mSetting = mNavView2.getHeaderView(0).findViewById(R.id.setting);
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
            }
        });

    }

    ;


}
