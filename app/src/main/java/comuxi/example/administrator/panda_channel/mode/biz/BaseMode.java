package comuxi.example.administrator.panda_channel.mode.biz;

import comuxi.example.administrator.panda_channel.mode.Http.HttpFactroy;
import comuxi.example.administrator.panda_channel.mode.Http.IHttp;

/**
 * Created by Administrator on 2017/7/12.
 *
 * efk
 *
 */

public interface BaseMode {
    public static IHttp I_HTTP = HttpFactroy.craet();
}
