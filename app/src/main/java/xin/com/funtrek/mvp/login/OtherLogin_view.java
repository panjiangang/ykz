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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_login);
        ButterKnife.bind(this);
        loginPresenter = new Login_presenter(this);

    }

    @OnClick(R.id.other_return)
    public void onOtherReturnClicked() {
        finish();
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
        startActivity(new Intent(OtherLogin_view.this, MainActivity.class));
    }

    @Override
    public void lClick(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rClick() {
        startActivity(new Intent(OtherLogin_view.this, Register_view.class));
    }
}
