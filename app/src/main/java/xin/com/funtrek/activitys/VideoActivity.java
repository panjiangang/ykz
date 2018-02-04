package xin.com.funtrek.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xin.com.funtrek.R;
import xin.com.funtrek.http.ApiServce;
import xin.com.funtrek.http.RetrofitUtils;
import xin.com.funtrek.http.bean.HotBean;
import xin.com.funtrek.http.bean.HotLikeBean;

public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.video_fanhui_hot)
    ImageView videoFanhuiHot;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.simp)
    SimpleDraweeView simp;
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
    @BindView(R.id.button1)
    RadioButton button1;
    @BindView(R.id.button2)
    RadioButton button2;
    @BindView(R.id.radio)
    RadioGroup radio;
    int wid = 0;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.listview)
    ListView listview;
    private List<HotBean.DataBean> hotlist;
    private int hotposition;
    private List<HotBean.DataBean> vearbylist;
    private int vearbyposition;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);


        hotlist = (List<HotBean.DataBean>) getIntent().getSerializableExtra("Hotlist");

        hotposition = getIntent().getIntExtra("Hotposition", 1);

        vearbylist = (List<HotBean.DataBean>) getIntent().getSerializableExtra("Vearbylist");
        vearbyposition = getIntent().getIntExtra("Vearbyposition", 1);

        if (hotlist != null) {
            if (hotlist.get(hotposition).getUser().getIcon() != null) {
                simp.setImageURI(hotlist.get(hotposition).getUser().getIcon());
                videoplayer.setUp(hotlist.get(hotposition).getVideoUrl(), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, hotlist.get(hotposition).getWorkDesc() + "");
                text1.setText(hotlist.get(hotposition).getWorkDesc() + "");
                text2.setText(hotlist.get(hotposition).getPlayNum() + "次播放    " + hotlist.get(hotposition).getCreateTime().split("T")[0] + "  " + hotlist.get(hotposition).getCreateTime().split("T")[1]);
            }

        }
        if (vearbylist != null) {

            if (vearbylist.get(vearbyposition).getUser().getIcon() != null) {

                simp.setImageURI(vearbylist.get(vearbyposition).getUser().getIcon());

                videoplayer.setUp(vearbylist.get(vearbyposition).getVideoUrl(), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, vearbylist.get(vearbyposition).getWorkDesc() + "");

            }


        }
        if (hotlist != null) {
            wid = hotlist.get(hotposition).getWid();
        }
        if (vearbylist != null) {
            wid = vearbylist.get(vearbyposition).getWid();
        }
        RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn", ApiServce.class)
                .hotlike(1730 + "", wid + "", "75B3A34ABE0ABC6A6BD05725E244365B")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotLikeBean>() {
                    @Override
                    public void accept(HotLikeBean hotLikeBean) throws Exception {
                        msg = hotLikeBean.getMsg();
                        if (msg.equals("已点赞过")) {
                            button1.setChecked(true);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("VideoActivitythrowable = " + throwable);
                    }
                });
        listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return hotlist.get(hotposition).getComments().size();
            }
            @Override
            public Object getItem(int position) {
                return hotlist.get(hotposition).getComments().get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View inflate = convertView.inflate(VideoActivity.this, R.layout.activity_video_listadapter, null);
                TextView textname = inflate.findViewById(R.id.textname);
                TextView textcontent = inflate.findViewById(R.id.textcontent);
                textname.setText(hotlist.get(hotposition).getComments().get(position).getNickname()+":");
                textcontent.setText(hotlist.get(hotposition).getComments().get(position).getContent());
                return inflate;
            }
        });
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.button1:
                        Toast.makeText(VideoActivity.this, msg, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.button2:
                        Toast.makeText(VideoActivity.this, "取消点赞", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

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

                textIntent.setType("*/*");

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
