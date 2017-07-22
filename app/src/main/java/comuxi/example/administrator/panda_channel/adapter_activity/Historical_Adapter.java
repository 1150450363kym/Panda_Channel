package comuxi.example.administrator.panda_channel.adapter_activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import comuxi.example.administrator.panda_channel.Base.BaseActivity;
import comuxi.example.administrator.panda_channel.HistroyGreeDao.HistroyGreeDao;
import comuxi.example.administrator.panda_channel.R;

/**
 * Created by Administrator on 2017/7/21.
 */

public class Historical_Adapter extends RecyclerView.Adapter {

    public interface Onclick {
        void get_Onclick(View view, int postion);
    }

    private Onclick oclick;

    public void set_Onclick(Onclick oclick) {
        this.oclick = oclick;

    }

    BaseActivity content;
    List<HistroyGreeDao> his_array;

    public Historical_Adapter(BaseActivity content, ArrayList<HistroyGreeDao> his_array) {

        this.content = content;
        this.his_array = his_array;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(content).inflate(R.layout.historical_list_item, null);


        return new My_Viwe(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final My_Viwe my_view = (My_Viwe) holder;

        my_view.title.setText(his_array.get(position).getName());
        my_view.data.setText(his_array.get(position).getData());

        if (his_array.get(position).isFlg() == true) {

            my_view.radioButton.setVisibility(View.VISIBLE);

        } else  {

            my_view.radioButton.setVisibility(View.GONE);
        }

        if (his_array.get(position).isFlg_bulen() ) {
            my_view.radioButton.setChecked(true);

        }else{
            my_view.radioButton.setChecked(false);
        }


        Glide.with(content).load(his_array.get(position).getImagpath()).into(my_view.imageView);



        my_view.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_view.radioButton.setChecked(true);

            }
        });


        my_view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oclick.get_Onclick(v, position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return his_array.size();
    }

    class My_Viwe extends RecyclerView.ViewHolder {
        private RadioButton radioButton;
        private ImageView imageView;
        private TextView title, data;

        public My_Viwe(View itemView) {
            super(itemView);

            radioButton = (RadioButton) itemView.findViewById(R.id.original_radio);
            imageView = (ImageView) itemView.findViewById(R.id.OriginalNews_item_img);
            title = (TextView) itemView.findViewById(R.id.OriginalNews_item_title);
            data = (TextView) itemView.findViewById(R.id.OriginalNews_item_time);


        }
    }


}
