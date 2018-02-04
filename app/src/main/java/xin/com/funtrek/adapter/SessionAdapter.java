package xin.com.funtrek.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import xin.com.funtrek.R;
import xin.com.funtrek.http.bean.SessionBean;

/**
 * date:2018/1/25
 * author:张伟(Administrator)
 * desc:
 */

public class SessionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private SessionBean sessionBean;

    public SessionAdapter(FragmentActivity activity, SessionBean sessionBean) {
        this.context = activity;
        this.sessionBean = sessionBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.session_layout_item, null);
        return new SessionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context).load(sessionBean.data.get(position).imgUrls).into(((SessionViewHolder) holder).leftImg);
        ((SessionViewHolder) holder).nameText.setText(sessionBean.data.get(position).user.nickname);
        ((SessionViewHolder) holder).timeText.setText(sessionBean.data.get(position).createTime);
        ((SessionViewHolder) holder).contentText.setText(sessionBean.data.get(position).content);
//        Glide.with(context).load(sessionBean.data.get(position)).into(((SessionViewHolder)holder).rightImg);

//        ((SessionViewHolder) holder).leftImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
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

    }

    @Override
    public int getItemCount() {
        return sessionBean.data.size();
    }

    class SessionViewHolder extends RecyclerView.ViewHolder {

        private final ImageView leftImg;
        private final TextView nameText;
        private final TextView timeText;
        private final TextView contentText;
        //        private final ImageView rightImg;

        public SessionViewHolder(View itemView) {
            super(itemView);

            leftImg = itemView.findViewById(R.id.session_item_img_left);
            nameText = itemView.findViewById(R.id.session_item_name);
            timeText = itemView.findViewById(R.id.session_item_time);
            contentText = itemView.findViewById(R.id.session_item_content);
//            rightImg = itemView.findViewById(R.id.session_item_img_right);
        }
    }

}
