package comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.MoreLiveBean;

/**
 * Created by lenovo on 2017/7/13.
 */

public class MoreLiveAdapter extends RecyclerView.Adapter {

    private List<MoreLiveBean.ListBean> list;
    private Context context;
    private MyCall myCall;

    public MoreLiveAdapter(List<MoreLiveBean.ListBean> list, Context context,MyCall myCall) {
        this.list = list;
        this.context = context;
        this.myCall = myCall;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_morelive,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder holder1 = (ViewHolder) holder;
        holder1.title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).into(holder1.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView img;
        private TextView title;
        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.item_morelive_img);
            title = (TextView) itemView.findViewById(R.id.item_morelive_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(myCall != null){
                myCall.setOnItemClickListener(getAdapterPosition());
            }
        }
    }

    public interface MyCall{
        void setOnItemClickListener(int position);
    }
}
