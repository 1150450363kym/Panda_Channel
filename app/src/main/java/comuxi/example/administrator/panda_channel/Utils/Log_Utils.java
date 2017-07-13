package comuxi.example.administrator.panda_channel.Utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/7/13.
 */

public class Log_Utils {
    static boolean flg = true;

    public static void log_d(String tag, String message) {


        if (flg == true) {

            Log.e(tag, message);

        }

    }
}
