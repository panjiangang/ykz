package xin.com.funtrek.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import xin.com.funtrek.R;
import xin.com.funtrek.activitys.VideoActivity;
import xin.com.funtrek.http.bean.HotBean;
import xin.com.funtrek.framgments.videofragments.HotFragment;

/**
 * Created by D-H-F on 2018/01/26.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {

    List<HotBean.DataBean> data = new ArrayList<>();
    HotFragment hotFragment;
    private View view;
    private List<Integer> heightList;
    public HotAdapter(HotFragment hotFragment) {
        this.hotFragment=hotFragment;
    }
    public void add(List<HotBean.DataBean> data) {
        this.data=data;
        heightList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            int height = new Random().nextInt(200) + 300;//[100,300)的随机数
            heightList.add(height);
        }
    }
    @Override
    public HotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = View.inflate(hotFragment.getContext(), R.layout.videofragment_hotvideo, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HotAdapter.ViewHolder holder, final int position) {
        holder.simp.setImageURI(data.get(position).getCover());
        ViewGroup.LayoutParams params = holder.simp.getLayoutParams();
        params.height=heightList.get(position);
        holder.simp.setLayoutParams(params);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hotFragment.getActivity(), VideoActivity.class);
                intent.putExtra("hotdata", (Serializable) data);
                intent.putExtra("hotposition",position);
                hotFragment.startActivity(intent);
            }
        });
        
    }
    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simp;
        public ViewHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.simp);
        }
    }

}
