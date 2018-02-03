package xin.com.funtrek.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xin.com.funtrek.R;

public class ReleaseSuccessActivity extends AppCompatActivity {

    @BindView(R.id.release_return)
    TextView releaseReturn;
    @BindView(R.id.release_share)
    LinearLayout releaseShare;
    @BindView(R.id.release_look_look)
    TextView releaseLookLook;
    private String mshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_success);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mshare = intent.getStringExtra("mshare");
    }

    @OnClick(R.id.release_return)
    public void onReleaseReturnClicked() {
        finish();
    }

    @OnClick(R.id.release_share)
    public void onReleaseShareClicked() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, mshare);
        shareIntent.setType("text/plain");
        this.startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    @OnClick(R.id.release_look_look)
    public void onReleaseLookLookClicked() {
        startActivity(new Intent(ReleaseSuccessActivity.this, VideoActivity.class));
    }
}
