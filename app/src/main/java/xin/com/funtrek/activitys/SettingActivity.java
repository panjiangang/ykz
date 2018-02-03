package xin.com.funtrek.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xin.com.funtrek.R;
import xin.com.funtrek.mvp.login.Login_view;
import xin.com.funtrek.mvp.login.Register_view;
import xin.com.funtrek.utils.MessageEvent;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.setting_log_off)
    Button settingLogOff;
    SharedPreferences sp;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        sp = getSharedPreferences("SharedPreferences", MODE_APPEND);
    }

    @OnClick(R.id.setting_log_off)
    public void onViewClicked() {
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("login", false);
        edit.commit();

        EventBus.getDefault().post(new MessageEvent(true));
        startActivity(new Intent(SettingActivity.this, Login_view.class));

        finish();
    }
}
