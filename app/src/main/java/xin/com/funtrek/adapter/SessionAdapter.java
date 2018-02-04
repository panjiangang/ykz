package xin.com.funtrek.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xin.com.funtrek.R;
import xin.com.funtrek.http.bean.SessionBean;
import xin.com.funtrek.other.MyAnimation;

//import xin.com.funtrek.activitys.DetailsActivity;

/**
 * date:2018/1/25
 * author:张伟(Administrator)
 * desc:
 */

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder> {

    private Context context;
    private SessionBean sessionBean;

    public SessionAdapter(FragmentActivity activity, SessionBean sessionBean) {
        this.context = activity;
        this.sessionBean = sessionBean;
    }

    @Override
    public SessionAdapter.SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.session_layout_item, null);
        return new SessionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SessionAdapter.SessionViewHolder holder, final int position) {

        MyAnimation.add(holder.iconOpen, holder.report, holder.copylink, holder.shiled);

        Glide.with(context).load(sessionBean.data.get(position).imgUrls).into(((SessionViewHolder) holder).sessionItemImgLeft);
        ((SessionViewHolder) holder).sessionItemName.setText(sessionBean.data.get(position).user.nickname);
        ((SessionViewHolder) holder).sessionItemTime.setText(sessionBean.data.get(position).createTime);
        ((SessionViewHolder) holder).sessionItemContent.setText(sessionBean.data.get(position).content);
//        Glide.with(context).load(sessionBean.data.get(position)).into(((SessionViewHolder)holder).rightImg);

//        ((SessionViewHolder) holder).leftImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public  oid onClick(View view) {
//                Intent intent = new Intent(context, SessionXQActivity.class);
//                intent.putExtra("uid", sessionBean.data.get(position).uid);
//                intent.putExtra("imgUrls",sessionBean.data.get(position).imgUrls+"");
//                context.startActivity(intent);
//            }
//        });
//
//        ((SessionViewHolder) holder).timeText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, SessionXQActivity.class);
//                intent.putExtra("uid", sessionBean.data.get(position).uid);
//                intent.putExtra("imgUrls",sessionBean.data.get(position).imgUrls+"");
//                context.startActivity(intent);
//            }
//        });
//
//        ((SessionViewHolder) holder).nameText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, SessionXQActivity.class);
//                intent.putExtra("uid", sessionBean.data.get(position).uid);
//                intent.putExtra("imgUrls",sessionBean.data.get(position).imgUrls+"");
//                context.startActivity(intent);
//            }
//        });

//        ((SessionViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailsActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return sessionBean.data.size();
    }

    static class SessionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.session_item_img_left)
        ImageView sessionItemImgLeft;
        @BindView(R.id.session_item_name)
        TextView sessionItemName;
        @BindView(R.id.shiled)
        ImageView shiled;
        @BindView(R.id.copylink)
        ImageView copylink;
        @BindView(R.id.report)
        ImageView report;
        @BindView(R.id.icon_open)
        ImageView iconOpen;
        @BindView(R.id.session_item_time)
        TextView sessionItemTime;
        @BindView(R.id.session_item_content)
        TextView sessionItemContent;

        SessionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
