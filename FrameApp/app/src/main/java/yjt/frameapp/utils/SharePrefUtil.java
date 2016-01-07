package yjt.frameapp.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import yjt.frameapp.MyApplication;


/**
 * sharepreference全局保存数据和读取数据
 * @author yujiangtao
 * @date 2015-4-5
 *
 */
public class SharePrefUtil {
	private static SharedPreferences share = null;
	private static final Object syncobj = new Object();

	private static boolean sIsAtLeastGB;

	static {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			sIsAtLeastGB = true;
		}
	}
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static void apply(SharedPreferences.Editor editor) {
		if (sIsAtLeastGB) {
			editor.apply();
		} else {
			editor.commit();
		}
	}
	/**
	 * @param c
	 * 双重锁定，引用的时候创建实例
	 */
	public static void init(Context c) {
		if (share == null) {
			synchronized (syncobj) {
				if(share==null){
					share = c.getSharedPreferences("SHAREPREF", Context.MODE_PRIVATE);
				}
			}
		}
	}
	public static String getAPPID(){
			if(share==null)init(MyApplication.instance);
			return share.getString("APPID", null);
		}
	public static void setAPPID(String b){
		if(share==null)init(MyApplication.instance);
		apply(share.edit().putString("APPID", b));
	}

	public static long getTimeStamp(){
		if(share==null)init(MyApplication.instance);
		return share.getLong("TIMESTAMP", 0);
	}
	public static void setTimeStamp(long b){
		if(share==null)init(MyApplication.instance);
		apply(share.edit().putLong("TIMESTAMP", b));
	}

	public static String getTocken(){
		if(share==null)init(MyApplication.instance);
		return share.getString("Tocken", null);
	}
	public static void setTocken(String b){
		if(share==null)init(MyApplication.instance);
		apply(share.edit().putString("Tocken", b));
	}
	public static String getUserName(){
		if(share==null)init(MyApplication.instance);
		return share.getString("USERNAME", "");
	}
	public static void setUserName(String b){
		if(share==null)init(MyApplication.instance);
		apply(share.edit().putString("USERNAME", b));
	}
	public static String getUserPWD(){
		if(share==null)init(MyApplication.instance);
		return share.getString("UserPWD", "");
	}
	public static void setUserPWD(String b){
		if(share==null)init(MyApplication.instance);
		apply(share.edit().putString("UserPWD", b));
	}
	public static boolean getLoginStatus(){
		if(share==null)init(MyApplication.instance);
		return share.getBoolean("LoginStatus", false);
	}
	public static void setLoginStatus(boolean b){
		if(share==null)init(MyApplication.instance);
		apply(share.edit().putBoolean("LoginStatus", b));
	}

}
