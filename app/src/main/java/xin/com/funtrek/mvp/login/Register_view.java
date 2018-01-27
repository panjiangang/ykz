package xin.com.funtrek.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xin.com.funtrek.R;
import xin.com.funtrek.activitys.MainActivity;
import xin.com.funtrek.mvp.login.Login_presenter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        loginPresenter = new Login_presenter(this);
    }

    @OnClick(R.id.register_return)
    public void onRegisterReturnClicked() {
        finish();
    }

    @OnClick(R.id.register_return1)
    public void onRegisterReturn1Clicked() {
        finish();
    }

    @Override
    public void lClick(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(Register_view.this, MainActivity.class));
        finish();
    }
}
