package yjt.frameapp;

import android.app.Application;
import android.os.Environment;

import com.orhanobut.logger.Logger;
import yjt.frameapp.crash.CrashApp;

/**
 * Created by yujiangtao on 2015/12/28.
 */
public class MyApplication extends Application{
    public static  MyApplication instance=null;
    public String FILE_SAVEPATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/yjt_frame/";
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        new CrashApp(this);
       Logger.init("YJT");
    }

}
