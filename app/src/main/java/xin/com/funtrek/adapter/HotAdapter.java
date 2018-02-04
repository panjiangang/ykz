package xin.com.funtrek.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import xin.com.funtrek.R;
import xin.com.funtrek.framgments.videofragments.HotFragment;
import xin.com.funtrek.framgments.videofragments.VearbyFragment;
import xin.com.funtrek.http.bean.HotBean;

/**
 * Created by D-H-F on 2018/01/26.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {

    List<HotBean.DataBean> list = new ArrayList<>();
    HotFragment hotFragment;
    private View view;
    VearbyFragment vearbyFragment;
    private List<Integer> heightList;
    public HotAdapter(HotFragment hotFragment) {
        this.hotFragment=hotFragment;
    }
    public HotAdapter(VearbyFragment vearbyFragment) {
        this.vearbyFragment=vearbyFragment;
    }
    public void add(List<HotBean.DataBean> list) {
        this.list=list;
        heightList = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            int height = new Random().nextInt(200) + 300;//[100,300)的随机数
            heightList.add(height);
        }
    }
    @Override
    public HotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = View.inflate(parent.getContext(), R.layout.videofragment_hotvideo, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HotAdapter.ViewHolder holder, final int position) {
        holder.simp.setImageURI(list.get(position).getCover());
        ViewGroup.LayoutParams params = holder.simp.getLayoutParams();
        params.height=heightList.get(position);
        holder.simp.setLayoutParams(params);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position,list);
            }
        });


    }
    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simp;
        public ViewHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.simp);
        }
    }

    onItemClickListener onItemClickListener;

    public void setonItemClickListener( onItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public interface  onItemClickListener{
        void onItemClick(int position,List<HotBean.DataBean> list);
    }
}
