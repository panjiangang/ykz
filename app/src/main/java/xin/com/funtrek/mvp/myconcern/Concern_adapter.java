package xin.com.funtrek.mvp.myconcern;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xin.com.funtrek.R;
import xin.com.funtrek.http.bean.ConcernBean;

/**
 * date:2018/2/4  20:04
 * author:Mr.XIn💕
 */


public class Concern_adapter extends RecyclerView.Adapter<Concern_adapter.ConcernView> {

    private Context mContext;
    protected List<ConcernBean.DataBean> mDatas;
    private LayoutInflater mInflater;

    //    提供接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    //    声明类型
    private OnItemClickListener mOnItemClickListener;

    //    提供它的set方法，供activity设置回调
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //提供一个合适的构造方法
    public Concern_adapter(Context context, List<ConcernBean.DataBean> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ConcernView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.concern_item, parent, false);
        ConcernView viewHolder = new ConcernView(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ConcernView holder, int position) {
        holder.mDate.setText(mDatas.get(position).getCreatetime());
        Log.e("XXXXXX", mDatas.get(position).getCreatetime() );

    }

    protected void setUpItemEvent(final ConcernView viewHolder) {
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView, layoutPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ConcernView extends RecyclerView.ViewHolder {
        @BindView(R.id.tv1)
        TextView mTv1;
        @BindView(R.id.tv2)
        TextView mTv2;
        @BindView(R.id.date)
        TextView mDate;
        public ConcernView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
