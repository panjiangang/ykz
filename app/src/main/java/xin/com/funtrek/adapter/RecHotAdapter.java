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
import xin.com.funtrek.http.bean.Login_Success_Bean;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;
import xin.com.funtrek.mvp.recommend.RecHotPresenter;
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

        if (getItemViewType(position) == 0 && b) {
            AdHolder adHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_banner, parent, false);
                adHolder = new AdHolder(convertView);
                convertView.setTag(adHolder);
            } else adHolder = (AdHolder) convertView.getTag();

            adHolder.banner.setBannerStyle(BannerConfig.NOT_INDICATOR).setImageLoader(new GlideImageLoader()).setImages(images).start();
        } else if (getItemViewType(position) == 0 && u) {
            UserHolder userHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_item, parent, false);
                userHolder = new UserHolder(convertView);
                convertView.setTag(userHolder);
            } else userHolder = (UserHolder) convertView.getTag();

            token1 = user.getToken();

            userHolder.detailsUpSdv.setImageURI((String) user.getIcon());
            userHolder.detailsUpFans.setText(new Double((Double) user.getFans()).intValue() + " 粉丝  |    " + new Double((Double) user.getFollow()).intValue() + "关注");
        } else {
            final VideosHolder videosHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_hot_item, parent, false);
                videosHolder = new VideosHolder(convertView);
                convertView.setTag(videosHolder);
            } else {
                videosHolder = (VideosHolder) convertView.getTag();
            }

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

            MyAnimation.add(videosHolder.iconOpen, videosHolder.report, videosHolder.copylink, videosHolder.shiled);

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
        @BindView(R.id.icon)
        SimpleDraweeView icon;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.createTime)
        TextView createTime;
        @BindView(R.id.shiled)
        ImageView shiled;
        @BindView(R.id.copylink)
        ImageView copylink;
        @BindView(R.id.report)
        ImageView report;
        @BindView(R.id.icon_open)
        ImageView iconOpen;
        @BindView(R.id.workDesc)
        TextView workDesc;
        @BindView(R.id.vps)
        JZVideoPlayerStandard vps;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.praiseNum)
        TextView praiseNum;
        @BindView(R.id.favoriteNum)
        TextView favoriteNum;
        @BindView(R.id.playNum)
        TextView playNum;
        @BindView(R.id.commentNum)
        TextView commentNum;
        @BindView(R.id.first_name)
        TextView firstName;
        @BindView(R.id.com_first)
        TextView comFirst;
        @BindView(R.id.second_name)
        TextView secondName;
        @BindView(R.id.com_second)
        TextView comSecond;

        VideosHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class UserHolder {
        @BindView(R.id.details_up_bgimg)
        ImageView detailsUpBgimg;
        @BindView(R.id.details_up_return)
        ImageView detailsUpReturn;
        @BindView(R.id.details_up_title)
        TextView detailsUpTitle;
        @BindView(R.id.details_up_share)
        ImageView detailsUpShare;
        @BindView(R.id.details_up_news)
        ImageView detailsUpNews;
        @BindView(R.id.details_up_fans)
        TextView detailsUpFans;
        @BindView(R.id.details_up_follow)
        Button detailsUpFollow;
        @BindView(R.id.details_up_works)
        TextView detailsUpWorks;
        @BindView(R.id.details_up_relativeLayout)
        RelativeLayout detailsUpRelativeLayout;
        @BindView(R.id.details_up_sdv)
        SimpleDraweeView detailsUpSdv;

        UserHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
