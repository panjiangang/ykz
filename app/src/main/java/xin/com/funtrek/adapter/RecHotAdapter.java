package xin.com.funtrek.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xin.com.funtrek.R;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;
import xin.com.funtrek.other.banner.GlideImageLoader;

/**
 * @author ddy
 */
public class RecHotAdapter extends BaseAdapter {

    private List<RecItemBean.DataBean> videos = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();

    @Override
    public int getCount() {
        return videos.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (getItemViewType(position) == 0) {

            AdHolder adHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_banner, parent, false);
                adHolder = new AdHolder(convertView);
                convertView.setTag(adHolder);
            } else adHolder = (AdHolder) convertView.getTag();

            adHolder.banner.setBannerStyle(BannerConfig.NOT_INDICATOR).setImageLoader(new GlideImageLoader()).setImages(images).start();
        } else {

            VideosHolder videosHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_hot_item, parent, false);
                videosHolder = new VideosHolder(convertView);
                convertView.setTag(videosHolder);
            } else {
                videosHolder = (VideosHolder) convertView.getTag();
            }

            videosHolder.icon.setImageURI((String) videos.get(position).getUser().getIcon());
            videosHolder.nickname.setText((String) videos.get(position).getUser().getNickname());
            videosHolder.createTime.setText(videos.get(position).getCreateTime());
            videosHolder.workDesc.setText(videos.get(position).getWorkDesc());
            Glide.with(convertView).load(videos.get(position).getCover()).into(videosHolder.cover);
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
    }

    public void addVideos(RecItemBean recItemBean) {
        videos = recItemBean.getData();
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
        @BindView(R.id.workDesc)
        TextView workDesc;
        @BindView(R.id.cover)
        ImageView cover;
        @BindView(R.id.com_first)
        TextView comFirst;
        @BindView(R.id.com_second)
        TextView comSecond;

        VideosHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
