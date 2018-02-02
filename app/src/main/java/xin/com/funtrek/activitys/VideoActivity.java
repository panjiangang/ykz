package xin.com.funtrek.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import xin.com.funtrek.R;
import xin.com.funtrek.http.bean.HotBean;

public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.video_fanhui_hot)
    ImageView videoFanhuiHot;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.simp)
    SimpleDraweeView simp;
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);


        List<HotBean.DataBean> data = (List<HotBean.DataBean>) getIntent().getSerializableExtra("hotdata");

        int hotposition = getIntent().getIntExtra("hotposition", 1);

        simp.setImageURI(data.get(hotposition).getUser().getIcon());

        videoplayer.setUp(data.get(hotposition).getVideoUrl(), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "1111");

    }
    @OnClick({R.id.video_fanhui_hot, R.id.share, R.id.simp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.video_fanhui_hot:
                finish();
                break;
            case R.id.share:

                Intent textIntent = new Intent(Intent.ACTION_SEND);

                textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");

                startActivity(Intent.createChooser(textIntent, "分享"));

                break;

            case R.id.simp:
                Toast.makeText(this, "作者详情", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}
