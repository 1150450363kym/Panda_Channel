package comuxi.example.administrator.panda_channel.mode.Url_Path;

/**
 * Created by Administrator on 2017/7/11.
 */

public class Url {
    //服务器地址
    private static final String BASE_URL = "http://www.ipanda.com/kehuduan/";

    //熊猫直播
    public static final String PANDALIVE = BASE_URL + "PAGE14501769230331752/index.json";

    //    首页
    public static final String PANDAHOME = BASE_URL + "PAGE14501749764071042/index.json";

    //滚滚视频

    public static final String GGTV = BASE_URL +"xmwh/index.json";

    //熊猫直播--直播--多视角直播

    public static final String MORELIVE = BASE_URL+"PAGE14501769230331752/PAGE14501787896813312/index.json";

    //熊猫播报
    public static final String PANDABROADCASTIMG = BASE_URL +"PAGE14503485387528442/index.json";

    //熊猫播报列表
//    http://api.cntv.cn/apicommon/index?path=iphoneInterface/general/getArticleAndVideoListInfo.json&primary_id=PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE1451473628934144&serviceId=panda
    public static final String PANDABROADCAST = "http://api.cntv.cn/apicommon/index";

//熊猫直播 ---精彩一刻
//http:api.cntv.cn/video/videolistById?vsid=VSET100167216881&n=7&serviceId=panda&o=desc&of=time&p=1
    public static final String WONDERFULONE =  "http://api.cntv.cn/video/videolistById";
    //熊猫直播--直播--边看边聊
    //http://newcomment.cntv.cn/comment/list?app=ipandaApp&itemid=zhiboye_chat&nature=1&page=1
    public static final String WATCHCHAT =  "//http://newcomment.cntv.cn/comment/list";


}
