package xin.com.funtrek.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xin.com.funtrek.R;
import xin.com.funtrek.mvp.login.Login_view;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.setting_log_off)
    Button settingLogOff;
    SharedPreferences sp;
    @BindView(R.id.setting_back)
    LinearLayout mSettingBack;
    @BindView(R.id.title)
    TextView mTitle;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        sp = getSharedPreferences("SharedPreferences", MODE_APPEND);
        mTitle.setText("设置");
    }

    @OnClick({R.id.setting_back, R.id.setting_log_off})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();

                break;
            case R.id.setting_log_off:
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("login", false);
                edit.commit();
//        EventBus.getDefault().post(new MessageEvent(true));
                startActivity(new Intent(SettingActivity.this, Login_view.class));
                finish();
                break;
        }
    }
}
