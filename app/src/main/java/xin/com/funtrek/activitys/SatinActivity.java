package xin.com.funtrek.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xin.com.funtrek.R;
import xin.com.funtrek.http.bean.Login_Success_Bean;
import xin.com.funtrek.http.bean.Release_Success_Bean;
import xin.com.funtrek.mvp.login.OtherLogin_view;
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
                if (bean.getMsg().equals("发布成功")){
                    Intent intent = new Intent(SatinActivity.this, ReleaseSuccessActivity.class);
                    intent.putExtra("mshare",satinEdit.getText().toString());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Release_Success_Bean> call, Throwable t) {
                System.out.println("失败");
            }
        });
    }

}
