package yjt.frameapp.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import yjt.frameapp.MyApplication;

/**
 * Created by yujiangtao on 2015/12/28.
 */
public class ToastUtil {

    private static String lastToast = "";
    private static long lastToastTime;
    private static Context context=null;
    private static Context getContext(){
        if(context==null){
            context=MyApplication.instance.getApplicationContext();
        }
        return context;
    }

    public static void showToast(int message) {
        showToast(message, Toast.LENGTH_LONG, 0);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.FILL_HORIZONTAL
                | Gravity.TOP);
    }

    public static void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon);
    }

    public static void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, Gravity.FILL_HORIZONTAL
                | Gravity.TOP);
    }

    public static void showToastShort(int message) {
        showToast(message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.CENTER_HORIZONTAL
                | Gravity.BOTTOM);
    }

    public static void showToastShort(int message, Object... args) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.CENTER_HORIZONTAL
                | Gravity.BOTTOM, args);
    }

    public static void showToast(int message, int duration, int icon) {
        showToast(message, duration, icon, Gravity.CENTER_HORIZONTAL
                | Gravity.BOTTOM);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity) {
        showToast(getContext().getString(message), duration, icon, gravity);
    }

    public static void showToast(int message, int duration, int icon,
                                 int gravity, Object... args) {
        showToast(getContext().getString(message, args), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon,
                                 int gravity) {
        // 防止toast显示太频繁
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 2000) {

                Toast.makeText(getContext(),
                        message, Toast.LENGTH_SHORT).show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }
}
