package comuxi.example.administrator.panda_channel.moudel.Pandan_Broadcast;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.Utils.ACache;
import comuxi.example.administrator.panda_channel.WebActivity;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastBean;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.PandaBroadCastListBean;


/**
 * Created by Administrator on 2017/7/11.
 * 熊猫播报
 */

public class Pandan_Broadcast_Fragment extends BaseFragment implements PullToRefreshListener,Pandan_Broadcast_Contract.View {

    @BindView(R.id.eyepress_fragment_pullto)
    PullToRefreshRecyclerView eyepressFragmentPullto;
    private List<String> list;
    private PulltoAdapter adapter;
    private List<PandaBroadCastListBean.ListBean> mlist;
    private List<PandaBroadCastBean.DataBean.BigImgBean> imgBeenlist;

    private Pandan_Broadcast_Contract.presenter presenter;
    private ImageView top_img;
    private TextView top_title;
    private String path = "iphoneInterface/general/getArticleAndVideoListInfo.json";
    private String primary_id = "PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE1451473628934144";
    private String primary_id2 = "PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE145147362893414";
    int i=4;
    private String serviceId = "panda";
    private String url;
    @Override
    protected int getlayoutID() {
        return R.layout.login_eyepress_fragment;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        mlist = new ArrayList<>();
        imgBeenlist = new ArrayList<>();
        adapter = new PulltoAdapter(getContext(),mlist);
        eyepressFragmentPullto.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        eyepressFragmentPullto.setLayoutManager(manager);

        eyepressFragmentPullto.setLoadingMoreEnabled(true);
        eyepressFragmentPullto.setPullRefreshEnabled(true);
        eyepressFragmentPullto.setPullToRefreshListener(this);

        View top_view = LayoutInflater.from(getContext()).inflate(R.layout.panda_broadcast_topview,null);
        top_img = (ImageView) top_view.findViewById(R.id.panda_broadcast_topview_img);
        top_title = (TextView) top_view.findViewById(R.id.panda_broadcast_topview_title);

        top_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ACache aCache = ACache.get(getContext());
                aCache.put("cache_url",url);
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);


            }
        });


        eyepressFragmentPullto.addHeaderView(top_view);
    }

    @Override
    protected void loadData() {

        presenter.showPandapresenter(path,primary_id,serviceId);
        presenter.start();

    }


    @Override
    public void onRefresh() {
        eyepressFragmentPullto.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                loadData();
                adapter.notifyDataSetChanged();
                eyepressFragmentPullto.setRefreshComplete();
            }
        },1000);

    }

    @Override
    public void onLoadMore() {
        eyepressFragmentPullto.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                StringBuffer sb = new StringBuffer(primary_id2);
                sb.append(i);
                presenter.showPandapresenter(path,primary_id2,serviceId);
                loadData();
                adapter.notifyDataSetChanged();

                if(mlist.size() == 0){
                    Toast.makeText(getContext(),"亲，没数据了",Toast.LENGTH_SHORT).show();
                }
            }

        },1000);
    }

    @Override
    public void showData(PandaBroadCastBean pandaBroadCastListBean) {

        imgBeenlist.addAll(pandaBroadCastListBean.getData().getBigImg());
        top_title.setText(imgBeenlist.get(0).getTitle());
        Glide.with(getContext()).load(imgBeenlist.get(0).getImage()).into(top_img);
        url = imgBeenlist.get(0).getUrl();

    }

    @Override
    public void showDatalist(PandaBroadCastListBean pandaBroadCastListBean) {

        mlist.addAll(pandaBroadCastListBean.getList());
        adapter.notifyDataSetChanged();

    }


    @Override
    public void showMsg(String msg) {
        Log.e("TAG",msg);

        ACache aCache =ACache.get(getContext());
        PandaBroadCastBean bean = (PandaBroadCastBean) aCache.getAsObject("PandaBroadCastBean");
        imgBeenlist.addAll(bean.getData().getBigImg());
        top_title.setText(imgBeenlist.get(0).getTitle());
        Glide.with(getContext()).load(imgBeenlist.get(0).getImage()).into(top_img);
        url = imgBeenlist.get(0).getUrl();

        PandaBroadCastListBean listbean = (PandaBroadCastListBean) aCache.getAsObject("PandaBroadCastListBean");
        mlist.addAll(listbean.getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(Pandan_Broadcast_Contract.presenter presenter) {

        this.presenter = presenter;
    }
}
