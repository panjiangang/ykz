package xin.com.funtrek.mvp.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xin.com.funtrek.R;
import xin.com.funtrek.activitys.MainActivity;
import xin.com.funtrek.http.bean.Login_Success_Bean;
import xin.com.funtrek.utils.MessageEvent;
import xin.com.funtrek.utils.Retrofit_Interface;

public class OtherLogin_view extends AppCompatActivity implements Login_presenter.MainPresenter {

    @BindView(R.id.other_return)
    ImageView otherReturn;
    @BindView(R.id.other_register)
    TextView otherRegister;
    @BindView(R.id.other_username)
    EditText otherUsername;
    @BindView(R.id.other_password)
    EditText otherPassword;
    @BindView(R.id.other_login)
    TextView otherLogin;
    @BindView(R.id.other_visitorLogin)
    TextView otherVisitorLogin;
    private Login_presenter loginPresenter;
    SharedPreferences sp;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_login);
        ButterKnife.bind(this);
        loginPresenter = new Login_presenter(this);

        sp = getSharedPreferences("SharedPreferences", MODE_APPEND);

    }

    @OnClick(R.id.other_return)
    public void onOtherReturnClicked() {
        finish();
        overridePendingTransition(R.anim.slide_in_other, R.anim.slide_in_close);
    }

    @OnClick(R.id.other_register)
    public void onOtherRegisterClicked() {
        loginPresenter.regClick();
    }

    @OnClick(R.id.other_login)
    public void onOtherLoginClicked() {
        loginPresenter.loginClick("login", otherUsername.getText().toString(), otherPassword.getText().toString());
    }

    @OnClick(R.id.other_visitorLogin)
    public void onOtherVisitorLoginClicked() {
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("uid", "1730");
        edit.putString("token", "75B3A34ABE0ABC6A6BD05725E244365B");
        edit.putString("username", "游客登录");
        edit.putBoolean("login", true);
        edit.commit();
        startActivity(new Intent(OtherLogin_view.this, MainActivity.class));
        finish();
    }

    @Override
    public void lClick(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if (msg.equals("登录成功")) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.zhaoapi.cn")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Retrofit_Interface minterface = retrofit.create(Retrofit_Interface.class);

            Call<Login_Success_Bean> call = minterface.login_success(otherUsername.getText().toString(), otherPassword.getText().toString());

            call.enqueue(new Callback<Login_Success_Bean>() {
                @Override
                public void onResponse(Call<Login_Success_Bean> call, Response<Login_Success_Bean> response) {
                    Login_Success_Bean bean = response.body();
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("uid", "" + bean.getData().getUid());
                    edit.putString("token", "" + bean.getData().getToken());
                    edit.putString("username", "" + bean.getData().getUsername());
                    edit.putBoolean("login", true);
                    edit.commit();
                    startActivity(new Intent(OtherLogin_view.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Call<Login_Success_Bean> call, Throwable t) {
                    System.out.println("失败");
                }
            });

        }

    }

    @Override
    public void rClick() {
        startActivity(new Intent(OtherLogin_view.this, Register_view.class));
        overridePendingTransition(R.anim.slide_in_open, R.anim.slide_in_other);
    }
}
