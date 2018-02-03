package xin.com.funtrek.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import xin.com.funtrek.R;
import xin.com.funtrek.activitys.SessionXQActivity;
import xin.com.funtrek.http.bean.SessionXQBean;

/**
 * date:2018/2/3
 * author:张伟(Administrator)
 * desc:
 */

public class SessionXQAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private SessionXQBean sessionXQBean;
    private String imgUrls;

    public SessionXQAdapter(SessionXQActivity sessionXQActivity, SessionXQBean sessionXQBean, String imgUrls) {
        this.context = sessionXQActivity;
        this.sessionXQBean = sessionXQBean;
        this.imgUrls = imgUrls;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.session_xq_layout_item, null);
        return new SessionXQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(imgUrls).into(((SessionXQViewHolder) holder).leftImg);
        ((SessionXQViewHolder) holder).nameText.setText(sessionXQBean.data.get(position).commentNum + "");
        ((SessionXQViewHolder) holder).timeText.setText(sessionXQBean.data.get(position).createTime);
        ((SessionXQViewHolder)holder).jcVideoPlayerStandard.setUp(sessionXQBean.data.get(position).videoUrl,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"视频标题");
    }

    @Override
    public int getItemCount() {
        return sessionXQBean.data.size();
    }

    class SessionXQViewHolder extends RecyclerView.ViewHolder {

        private final ImageView leftImg;
        private final TextView nameText;
        private final TextView timeText;
        private final JCVideoPlayerStandard jcVideoPlayerStandard;

        public SessionXQViewHolder(View itemView) {
            super(itemView);

            leftImg = itemView.findViewById(R.id.session_item_img_left);
            nameText = itemView.findViewById(R.id.session_item_name);
            timeText = itemView.findViewById(R.id.session_item_time);
            jcVideoPlayerStandard = itemView.findViewById(R.id.session_item_content);

        }
    }

}
