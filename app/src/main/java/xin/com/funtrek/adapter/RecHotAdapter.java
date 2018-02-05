package xin.com.funtrek.adapter;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerStandard;
import xin.com.funtrek.R;
import xin.com.funtrek.activitys.DetailsActivity;
import xin.com.funtrek.http.bean.Login_Success_Bean;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;
import xin.com.funtrek.mvp.recommend.RecHotPresenter;
import xin.com.funtrek.other.DdyAnimation;
import xin.com.funtrek.other.GlideImageLoader;
import xin.com.funtrek.other.MyAnimation;

/**
 * @author ddy
 */
public class RecHotAdapter extends BaseAdapter {

    boolean b = false, u = false, f = true, p = true, c = true;
    private List<RecItemBean.DataBean> videos = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private Login_Success_Bean.DataBean user = new Login_Success_Bean.DataBean();
    Handler handler = new Handler(Looper.getMainLooper());
    private final RecHotPresenter reHotFramgentPresenter1;
    private final int uid1;
    private String token1;

    public RecHotAdapter(RecHotPresenter reHotFramgentPresenter, int uid, Object token) {
        reHotFramgentPresenter1 = reHotFramgentPresenter;
        uid1 = uid;
        token1 = (String) token;
    }

    @Override
    public int getCount() {
        return videos == null ? 0 : videos.size();
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final VideosHolder videosHolder;
        if (convertView == null) {
            videosHolder = new VideosHolder();

            if (getItemViewType(position) == 0 && b) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_banner, parent, false);
                videosHolder.banner = convertView.findViewById(R.id.banner);

            } else if (getItemViewType(position) == 0 && u) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_item, parent, false);
                videosHolder.detailsUpSdv = convertView.findViewById(R.id.details_up_sdv);
                videosHolder.detailsUpFans = convertView.findViewById(R.id.details_up_fans);

            } else {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_hot_item, parent, false);

                videosHolder.icon = convertView.findViewById(R.id.icon);
                videosHolder.nickname = convertView.findViewById(R.id.nickname);
                videosHolder.createTime = convertView.findViewById(R.id.createTime);
                videosHolder.iconOpen = convertView.findViewById(R.id.icon_open);
                videosHolder.report = convertView.findViewById(R.id.report);
                videosHolder.copylink = convertView.findViewById(R.id.copylink);
                videosHolder.shiled = convertView.findViewById(R.id.shiled);
                videosHolder.jb = convertView.findViewById(R.id.jb);
                videosHolder.fz = convertView.findViewById(R.id.fz);
                videosHolder.pb = convertView.findViewById(R.id.pb);
                videosHolder.workDesc = convertView.findViewById(R.id.workDesc);
                videosHolder.vps = convertView.findViewById(R.id.vps);
                videosHolder.favoriteNum = convertView.findViewById(R.id.favoriteNum);
                videosHolder.playNum = convertView.findViewById(R.id.playNum);
                videosHolder.praiseNum = convertView.findViewById(R.id.praiseNum);
                videosHolder.commentNum = convertView.findViewById(R.id.commentNum);
                videosHolder.ll = convertView.findViewById(R.id.ll);
                videosHolder.firstName = convertView.findViewById(R.id.first_name);
                videosHolder.comFirst = convertView.findViewById(R.id.com_first);
                videosHolder.secondName = convertView.findViewById(R.id.second_name);
                videosHolder.comSecond = convertView.findViewById(R.id.com_second);
            }
            convertView.setTag(videosHolder);
        } else videosHolder = (VideosHolder) convertView.getTag();

        if (getItemViewType(position) == 0 && b) {
            videosHolder.banner.setBannerStyle(BannerConfig.NOT_INDICATOR).setImageLoader(new GlideImageLoader()).setImages(images).start();

        } else if (getItemViewType(position) == 0 && u) {
            token1 = user.getToken();
            videosHolder.detailsUpSdv.setImageURI((String) user.getIcon());
            videosHolder.detailsUpFans.setText(new Double((Double) user.getFans()).intValue() + " 粉丝  |    " + new Double((Double) user.getFollow()).intValue() + "关注");

        } else {
            if (u) {
                videosHolder.icon.setImageURI((String) user.getIcon());

                String nickname = (String) user.getNickname();
                videosHolder.nickname.setText(nickname == null ? "无名" : nickname);
            } else {
                //有默认图片
                videosHolder.icon.setImageURI((String) videos.get(position).getUser().getIcon());

                String nickname = (String) videos.get(position).getUser().getNickname();
                videosHolder.nickname.setText(nickname == null ? "无名" : nickname);
            }

            videosHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(parent.getContext(), DetailsActivity.class);
                    intent.putExtra("uid", videos.get(position).getUid());
                    parent.getContext().startActivity(intent);
                }
            });

            String[] split = videos.get(position).getCreateTime().split("T");
            videosHolder.createTime.setText(split[0] + "  " + split[1]);

            DdyAnimation.add(videosHolder.iconOpen, videosHolder.report, videosHolder.copylink, videosHolder.shiled, videosHolder.jb, videosHolder.fz, videosHolder.pb);

            String workDesc = (String) videos.get(position).getWorkDesc();
            videosHolder.workDesc.setText(workDesc == null ? "无题" : workDesc);

            videosHolder.vps.setUp(videos.get(position).getVideoUrl(), JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
            Glide.with(parent.getContext()).load(videos.get(position).getCover()).into(videosHolder.vps.thumbImageView);

            videosHolder.praiseNum.setText("" + videos.get(position).getPraiseNum());
            videosHolder.praiseNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (p) {

                        reHotFramgentPresenter1.praise(uid1, videos.get(position).getWid(), token1);

                        videosHolder.praiseNum.setCompoundDrawablesWithIntrinsicBounds(null, parent.getResources().getDrawable(R.drawable.xin22), null, null);
                        videosHolder.praiseNum.setText("" + (videos.get(position).getPraiseNum() + 1));
                        p = false;
                    } else {
                        videosHolder.praiseNum.setCompoundDrawablesWithIntrinsicBounds(null, parent.getResources().getDrawable(R.drawable.xin11), null, null);
                        videosHolder.praiseNum.setText("" + (videos.get(position).getPraiseNum()));
                        p = true;
                    }
                }
            });

            videosHolder.favoriteNum.setText("" + videos.get(position).getFavoriteNum());
            videosHolder.favoriteNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (f) {

                        reHotFramgentPresenter1.addFavorite(uid1, videos.get(position).getWid(), token1);

                        videosHolder.favoriteNum.setCompoundDrawablesWithIntrinsicBounds(null, parent.getResources().getDrawable(R.drawable.xing22), null, null);
                        videosHolder.favoriteNum.setText("" + (videos.get(position).getFavoriteNum() + 1));
                        f = false;
                    } else {

                        reHotFramgentPresenter1.cancelFavorite(uid1, videos.get(position).getWid(), token1);

                        videosHolder.favoriteNum.setCompoundDrawablesWithIntrinsicBounds(null, parent.getResources().getDrawable(R.drawable.xing11), null, null);
                        videosHolder.favoriteNum.setText("" + (videos.get(position).getFavoriteNum()));
                        f = true;
                    }
                }
            });

            videosHolder.playNum.setText("" + videos.get(position).getPlayNum());
            videosHolder.playNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, videos.get(position).getWorkDesc());
                    shareIntent.setType("text/plain");
                    parent.getContext().startActivity(Intent.createChooser(shareIntent, "分享到"));
                }
            });

            videosHolder.commentNum.setText("" + videos.get(position).getCommentNum());
            videosHolder.commentNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(parent.getContext(), "你点错了", Toast.LENGTH_SHORT).show();
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (c) {
                        videosHolder.ll.setVisibility(View.GONE);
                        c = false;
                    } else {
                        videosHolder.ll.setVisibility(View.VISIBLE);
                        c = true;
                    }
                }
            });

            if (videos.get(position).getCommentNum() == 0) {
                videosHolder.firstName.setText("");
                videosHolder.comFirst.setText("还没有评论哦,赶快抢占沙发吧!");
                videosHolder.secondName.setText("");
                videosHolder.comSecond.setText("");
            } else if (videos.get(position).getCommentNum() == 1) {
                List<RecItemBean.DataBean.CommentsBean> comments = videos.get(position).getComments();

                String nickname1 = (String) comments.get(0).getNickname();
                videosHolder.firstName.setText(nickname1 == null ? "无名" : nickname1);
                videosHolder.comFirst.setText("  :  " + comments.get(0).getContent());

                videosHolder.secondName.setText("");
                videosHolder.comSecond.setText("");
            } else {
                List<RecItemBean.DataBean.CommentsBean> comments = videos.get(position).getComments();

                String nickname1 = (String) comments.get(0).getNickname();
                videosHolder.firstName.setText(nickname1 == null ? "无名" : nickname1);
                videosHolder.comFirst.setText("  :  " + comments.get(0).getContent());

                String nickname2 = (String) comments.get(1).getNickname();
                videosHolder.secondName.setText(nickname1 == null ? "无名" : nickname1);
                videosHolder.comSecond.setText("  :  " + comments.get(0).getContent());
            }
        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    public void addAd(RecBannerBean recBannerBean) {
        List<RecBannerBean.DataBean> ad = recBannerBean.getData();
        for (int i = 0; i < ad.size(); i++) {
            images.add(ad.get(i).getIcon());
        }
        b = true;
    }

    public void addVideos(RecItemBean recItemBean, int page) {
        List<RecItemBean.DataBean> data = recItemBean.getData();

        if (page == 1) {
            videos = data;
        } else {
            videos.addAll(data);
        }
    }

    public void addUser(Login_Success_Bean loginSuccessBean) {
        user = loginSuccessBean.getData();
        u = true;
    }

    static class AdHolder {
        @BindView(R.id.banner)
        Banner banner;

        AdHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class VideosHolder {
        SimpleDraweeView icon;
        TextView nickname;
        TextView createTime;
        ImageView shiled;
        ImageView copylink;
        ImageView report;
        ImageView iconOpen;
        TextView jb;
        TextView fz;
        TextView pb;
        TextView workDesc;
        JZVideoPlayerStandard vps;
        TextView praiseNum;
        TextView favoriteNum;
        TextView playNum;
        TextView commentNum;
        LinearLayout ll;
        TextView firstName;
        TextView comFirst;
        TextView secondName;
        TextView comSecond;

        Banner banner;

        ImageView detailsUpBgimg;
        ImageView detailsUpReturn;
        TextView detailsUpTitle;
        ImageView detailsUpShare;
        ImageView detailsUpNews;
        TextView detailsUpFans;
        Button detailsUpFollow;
        TextView detailsUpWorks;
        RelativeLayout detailsUpRelativeLayout;
        SimpleDraweeView detailsUpSdv;
    }
}
