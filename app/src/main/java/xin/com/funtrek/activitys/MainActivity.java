package xin.com.funtrek.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayerStandard;
import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseActivity;
import xin.com.funtrek.framgments.Recommend;
import xin.com.funtrek.framgments.Session;
import xin.com.funtrek.framgments.Video;
import xin.com.funtrek.http.bean.UpPic;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.main.Main_presenter;
import xin.com.funtrek.mvp.main.Main_view;
import xin.com.funtrek.utils.ImageUtils;

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
    private String path = Environment.getExternalStorageDirectory().getPath() + "/head.jpg";
    SharedPreferences sp;
    private FragmentTransaction mTransaction1;
    private Recommend mRecommend1;
    private LinearLayout mWorks;
    private LinearLayout mSetting;
    private ImageView mUser_img;
    private String mUsername;
    private String mToken;
    private String mUid;
    private TextView mCamera;
    private TextView mPicture;
    private AlertDialog mAlertDialog;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;

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
        View mDialog = View.inflate(this, R.layout.dialogview, null);
        mCamera = mDialog.findViewById(R.id.camera);
        mPicture = mDialog.findViewById(R.id.picture);
        mAlertDialog = new AlertDialog
                .Builder(this)
                .setView(mDialog)
                .create();
        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(it, 2000);
            }
        });
        mPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 4000);
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
                Intent intent = new Intent(this, CreateActivity.class);
                startActivity(intent);
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

    @SuppressLint("WrongConstant")
    @Override
    protected void logic() {
        sp = getSharedPreferences("SharedPreferences", MODE_APPEND);
        mUid = sp.getString("uid", "");
        // 12272
        Log.e("XXXXXXXXXXXX", mUid);
        mToken = sp.getString("token", "");
        //C625F5867FC5F65790AAD571784C748F
        Log.e("XXXXXXXXXXXX", mToken);
        mUsername = sp.getString("username", "");
        mySide();
    }

    //侧拉页面
    public void mySide() {
        navView.setItemIconTintList(null);
        //获取用户名
        TextView name = navView.getHeaderView(0)
                .findViewById(R.id.user_name);
        name.setText(mUsername);
        //用户头像

        mUser_img = navView.getHeaderView(0)
                .findViewById(R.id.user_image);
        mUser_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
        //侧拉列表菜单
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.attention:
                        startActivity(new Intent(MainActivity.this, MyConcern.class));
                        Toast.makeText(MainActivity.this, "关注", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.collect:
                        startActivity(new Intent(MainActivity.this, CollectActivity.class));

                        Toast.makeText(MainActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.friends:
                        startActivity(new Intent(MainActivity.this, FriendsActivity.class));

                        Toast.makeText(MainActivity.this, "好友", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.message:
                        startActivity(new Intent(MainActivity.this, MessageActivity.class));
                        Toast.makeText(MainActivity.this, "通知", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        //日夜间
        Switch aSwitch = mNavView2.getHeaderView(0).findViewById(R.id.day_night);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "T", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "F", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //本地作品
        mWorks = mNavView2.getHeaderView(0).findViewById(R.id.works);
        mWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, productionActivity.class));
                Toast.makeText(MainActivity.this, "本地", Toast.LENGTH_SHORT).show();
            }
        });
        //跳转到设置页面
        mSetting = mNavView2.getHeaderView(0).findViewById(R.id.setting);
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * backPress函数判断了点击回退按钮的相应，
     * 如果全屏会退出全屏播放，如果不是全屏则会交给Activity
     */
    @Override
    public void onBackPressed() {
        if (JZVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    /**
     * 暂停 /失去焦点
     * Activity或者按Home键之后会视频就会releas(释放)
     */
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void showData(UpPic bean) {
        Log.e("", bean.getMsg() );
        Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("上传头像");
        String[] items = {"选择本地图片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = ImageUtils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            mUser_img.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        String imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));

        if (imagePath != null) {
            // 拿着imagePath上传了
//            Toast.makeText(SatinActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
        }
    }
}
