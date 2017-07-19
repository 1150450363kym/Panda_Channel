package comuxi.example.administrator.panda_channel.moudel.Panda_Live.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import comuxi.example.administrator.panda_channel.Base.BaseFragment;
import comuxi.example.administrator.panda_channel.R;
import comuxi.example.administrator.panda_channel.mode.Panda_TextBean.WatchChatBean;
import comuxi.example.administrator.panda_channel.moudel.Panda_Live.adapter.WatchPulltoAdapter;


/**
 * Created by lenovo on 2017/7/12.
 * <p>
 * 熊猫直播 ---直播 --- 边看边聊
 * http://newcomment.cntv.cn/comment/list?app=ipandaApp&itemid=zhiboye_chat&nature=1&page=1
 */

public class WatchChat extends BaseFragment implements PullToRefreshListener{

    private WatchChatBean watch;
    @BindView(R.id.watchchat_pulltorefresh)
    PullToRefreshRecyclerView watchchatPulltorefresh;
    private List<WatchChatBean.Bean> list;
    private WatchPulltoAdapter adapter;
    private int page = 1;


    @Override
    protected int getlayoutID() {
        return R.layout.watchchat;
    }

    @Override
    protected void init(View view) {

        list = new ArrayList<>();
        adapter = new WatchPulltoAdapter(getContext(),list);
        watchchatPulltorefresh.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        watchchatPulltorefresh.setLayoutManager(manager);

        watchchatPulltorefresh.setLoadingMoreEnabled(true);
        watchchatPulltorefresh.setPullRefreshEnabled(true);
        watchchatPulltorefresh.setPullToRefreshListener(this);
    }

    @Override
    protected void loadData() {

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://newcomment.cntv.cn/comment/list?app=ipandaApp&itemid=zhiboye_chat&nature=1&page="+page);
                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                    huc.setDoInput(true);

                    InputStream inputStream = huc.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    int re=0;
                    byte[] byt = new byte[1024];

                    while ((re=inputStream.read(byt))!=-1){
                        buffer.append(new String(byt,0,re));
                    }

                    inputStream.close();
                    huc.disconnect();

                    JSONObject object = new JSONObject(buffer.toString());


                    watch = new WatchChatBean();
                    watch.setTime(object.getInt("time"));
                    final JSONObject data = object.getJSONObject("data");
                    watch.setTotal(data.getString("total"));
                    JSONArray content = data.getJSONArray("content");
                    ArrayList<WatchChatBean.Bean> arrayList = new ArrayList<>();
                    for (int i=0;i<content.length();i++){
                        JSONObject jsonObject = content.getJSONObject(i);
                        WatchChatBean.Bean bean = new WatchChatBean.Bean();

                        bean.setAuthor(jsonObject.getString("author"));
                        bean.setAuthorId(jsonObject.getString("authorid"));
                        bean.setDateline(jsonObject.getString("dateline"));
                        bean.setLocale(jsonObject.getString("locale"));
                        bean.setMessage(jsonObject.getString("message"));
                        bean.setPid(jsonObject.getString("pid"));
                        bean.setTid(jsonObject.getString("tid"));
                        arrayList.add(bean);
                    }
                    watch.setList(arrayList);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

//                            int floor = Integer.parseInt(watch.getTotal());
//                            FloorDate dates = new FloorDate();
//                            dates.setTotal(floor);
//                            dates.setDate(watch.getTime());
//                            strings.add(dates);
                            list.addAll(watch.getList());
                            adapter.notifyDataSetChanged();
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

        watchchatPulltorefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                loadData();
                adapter.notifyDataSetChanged();
                watchchatPulltorefresh.setLoadMoreComplete();


            }
        },200);
    }
}
