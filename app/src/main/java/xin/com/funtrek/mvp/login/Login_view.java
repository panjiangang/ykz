package xin.com.funtrek.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xin.com.funtrek.R;
import xin.com.funtrek.activitys.MainActivity;

public class Login_view extends AppCompatActivity {

    @BindView(R.id.login_return)
    ImageView loginReturn;
    @BindView(R.id.login_wx_onlick)
    RelativeLayout loginWxOnlick;
    @BindView(R.id.login_qq_onlick)
    RelativeLayout loginQqOnlick;
    @BindView(R.id.login_other)
    TextView loginOther;
    @BindView(R.id.login_wxdl)
    TextView loginWxdl;
    @BindView(R.id.login_qqdl)
    TextView loginQqdl;
    private Tencent mTencent;
    private String APP_ID = "222222";
    private IUiListener loginListener;
    private String SCOPE = "all";
    private IUiListener userInfoListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_return)
    public void onLoginReturnClicked() {
        finish();
    }

//    @OnClick(R.id.login_wx_onlick)
//    public void onLoginWxOnlickClicked() {
//    }
//
//    @OnClick(R.id.login_qq_onlick)
//    public void onLoginQqOnlickClicked() {
//    }

    @OnClick(R.id.login_other)
    public void onViewClicked() {
        startActivity(new Intent(Login_view.this, OtherLogin_view.class));
    }

    @OnClick(R.id.login_wxdl)
    public void onLoginWxdlClicked() {
    }

    @OnClick(R.id.login_qqdl)
    public void onLoginQqdlClicked() {
        initQqLogin();
        mTencent.login(this, SCOPE, loginListener);
    }

    private void initQqLogin() {
        mTencent = Tencent.createInstance(APP_ID, this);
        //创建QQ登录回调接口
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //登录成功后调用的方法
                JSONObject jo = (JSONObject) o;
                Toast.makeText(Login_view.this, "登录成功", Toast.LENGTH_SHORT).show();
                Log.e("COMPLETE:", jo.toString());
                String openID;
                try {
                    openID = jo.getString("openid");
                    String accessToken = jo.getString("access_token");
                    String expires = jo.getString("expires_in");
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken, expires);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                //登录失败后调用的方法
                Toast.makeText(Login_view.this, "登录失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {
                //取消登录后调用的方法
            }
        };

        userInfoListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                if (o == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) o;
                    Log.e("JO:", jo.toString());
                    int ret = jo.getInt("ret");
                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");
                    Toast.makeText(Login_view.this, "你好，" + nickName, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                }
            }

            @Override
            public void onError(UiError uiError) {
            }

            @Override
            public void onCancel() {
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
                Tencent.handleResultData(data, loginListener);
            }
        }
    }
}
