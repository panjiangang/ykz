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
import xin.com.funtrek.mvp.login.Login_presenter;
import xin.com.funtrek.utils.Retrofit_Interface;

public class Register_view extends AppCompatActivity implements Login_presenter.MainPresenter {

    @BindView(R.id.register_return)
    ImageView registerReturn;
    @BindView(R.id.register_return1)
    TextView registerReturn1;
    @BindView(R.id.register_iphone)
    EditText registerIphone;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.register_register)
    TextView registerRegister;
    @BindView(R.id.register_visitorLogin)
    TextView registerVisitorLogin;
    private Login_presenter loginPresenter;
    SharedPreferences sp;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        loginPresenter = new Login_presenter(this);
        sp = getSharedPreferences("SharedPreferences", MODE_APPEND);

    }

    @OnClick(R.id.register_return)
    public void onRegisterReturnClicked() {
        finish();
        overridePendingTransition(R.anim.slide_in_other, R.anim.slide_in_close);
    }

    @OnClick(R.id.register_return1)
    public void onRegisterReturn1Clicked() {
        finish();
        overridePendingTransition(R.anim.slide_in_other, R.anim.slide_in_close);
    }

    @Override
    public void lClick(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if (msg.equals("注册成功")){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.zhaoapi.cn")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Retrofit_Interface minterface = retrofit.create(Retrofit_Interface.class);

            Call<Login_Success_Bean> call = minterface.login_success(registerIphone.getText().toString(), registerPassword.getText().toString());

            call.enqueue(new Callback<Login_Success_Bean>() {
                @Override
                public void onResponse(Call<Login_Success_Bean> call, Response<Login_Success_Bean> response) {
                    Login_Success_Bean bean = response.body();
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("uid", "" + bean.getData().getUid());
                    edit.putString("token", "" + bean.getData().getToken());
                    edit.commit();
                    startActivity(new Intent(Register_view.this, MainActivity.class));
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

    }

    @OnClick(R.id.register_register)
    public void onRegisterRegisterClicked() {
        loginPresenter.loginClick("reg", registerIphone.getText().toString(), registerPassword.getText().toString());
    }

    @OnClick(R.id.register_visitorLogin)
    public void onRegisterVisitorLoginClicked() {
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("uid", "1730");
        edit.putString("token", "75B3A34ABE0ABC6A6BD05725E244365B");
        edit.commit();
        startActivity(new Intent(Register_view.this, MainActivity.class));
    }
}
