package comuxi.example.administrator.panda_channel.moudel.Home.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.Home_China_Movie_Text;

/**
 * Created by Administrator on 2017/7/13.
 */
//
public class Home_China_Moive_Adapter extends RecyclerView.Adapter {

    public interface Movie_live_Onclick{
        void  get_movie_live(View view,int movie_postion);
    }
    private Movie_live_Onclick movie_live_onclick;

    public void set_China_movie_click(Movie_live_Onclick movie_live_onclick){
        this.movie_live_onclick=movie_live_onclick;
    }






    FragmentActivity activity;
    ArrayList<Home_China_Movie_Text.ListBean> movie_array;
    public Home_China_Moive_Adapter(FragmentActivity activity, ArrayList<Home_China_Movie_Text.ListBean> movie_array) {
        this.activity=activity;
        this.movie_array = movie_array;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(activity).inflate(R.layout.home_panda_look_down_item,null);

        return new My_View(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       My_View my_view = (My_View) holder;

        my_view.title.setText(movie_array.get(position).getTitle());
        my_view.data.setText(movie_array.get(position).getDaytime());
        my_view.time.setText(movie_array.get(position).getVideoLength());
        Glide.with(activity).load(movie_array.get(position).getImage()).placeholder(R.mipmap.umeng_socialize_share_pic).into(my_view.imageView);


        my_view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie_live_onclick.get_movie_live(v,position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movie_array.size();
    }
    class My_View extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title,data,time;
        public My_View(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.look_down_image);

            title = (TextView) itemView.findViewById(R.id.look_down_title);
            data= (TextView) itemView.findViewById(R.id.look_down_data);
            time = (TextView) itemView.findViewById(R.id.movie_time);


        }
    }
}
