package yjt.frameapp.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import yjt.frameapp.MyApplication;

/**
 * Created by yujiangtao on 2015/12/28.
 */
public class HttpUtil {
    /**
     * 检测网络是否可用
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

}
