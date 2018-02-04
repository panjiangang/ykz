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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xin.com.funtrek.R;
import xin.com.funtrek.http.bean.Release_Success_Bean;
import xin.com.funtrek.utils.ImageUtils;
import xin.com.funtrek.utils.Retrofit_Interface;

public class SatinActivity extends AppCompatActivity {

    @BindView(R.id.satin_cancel)
    TextView satinCancel;
    @BindView(R.id.satin_release)
    TextView satinRelease;
    @BindView(R.id.satin_edit)
    EditText satinEdit;
    protected static final int BAOCUN = 0;
    protected static final int BUBAOCUN = 1;
    SharedPreferences sp;
    @BindView(R.id.satin_addImg)
    ImageView satinAddImg;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satin);
        ButterKnife.bind(this);
        sp = getSharedPreferences("SharedPreferences", MODE_APPEND);
        String satin = sp.getString("satin", "");
        if (!satin.equals("")) {
            satinEdit.setText(satin);
        }
    }

    @OnClick(R.id.satin_cancel)
    public void onSatinCancelClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否保存到草稿箱?");
        String[] items = {"保存", "不保存"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case BAOCUN: // 保存
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putString("satin", satinEdit.getText().toString());
                        edit.commit();
                        finish();
                        break;
                    case BUBAOCUN: // 不保存
                        SharedPreferences.Editor edit1 = sp.edit();
                        edit1.putString("satin", "");
                        edit1.commit();
                        finish();
                        break;
                }
            }
        });
        final AlertDialog myDialog = builder.create();
        Window window = myDialog.getWindow();
        myDialog.show();
        window.setGravity(Gravity.BOTTOM);//底部出现
    }

    @OnClick(R.id.satin_release)
    public void onSatinReleaseClicked() {
//        https://www.zhaoapi.cn/quarter/publishJoke?uid=12229&content=发布的内容&token=E881C1A97438C6D81EE8FD14D6F7234A&source=android&appVersion=101
        String uid = sp.getString("uid", "1730");
        String token = sp.getString("token", "75B3A34ABE0ABC6A6BD05725E244365B");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit_Interface minterface = retrofit.create(Retrofit_Interface.class);

        Call<Release_Success_Bean> call = minterface.release_success(uid, satinEdit.getText().toString(), token, "android", "101");

        call.enqueue(new Callback<Release_Success_Bean>() {
            @Override
            public void onResponse(Call<Release_Success_Bean> call, Response<Release_Success_Bean> response) {
                Release_Success_Bean bean = response.body();
                Toast.makeText(SatinActivity.this, "" + bean.getMsg(), Toast.LENGTH_SHORT).show();
                if (bean.getMsg().equals("发布成功")) {
                    Intent intent = new Intent(SatinActivity.this, ReleaseSuccessActivity.class);
                    intent.putExtra("mshare", satinEdit.getText().toString());
                    SharedPreferences.Editor edit1 = sp.edit();
                    edit1.putString("satin", "");
                    edit1.commit();
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Release_Success_Bean> call, Throwable t) {
                System.out.println("失败");
            }
        });
    }

    @OnClick(R.id.satin_addImg)
    public void onViewClicked() {
        showChoosePicDialog();
    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("上传图片");
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
//            photo = ImageUtils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            satinAddImg.setImageBitmap(photo);
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







