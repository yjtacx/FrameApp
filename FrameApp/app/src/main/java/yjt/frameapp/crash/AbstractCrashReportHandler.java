package yjt.frameapp.crash;

/**
 * Created by yujiangtao on 2016/1/6.
 */

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.File;

import yjt.frameapp.MyApplication;

public abstract class AbstractCrashReportHandler implements CrashListener {
    private Context mContext;

    public AbstractCrashReportHandler(Context context) {
        mContext = context;
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(getLogDir(context), this);
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    protected File getLogDir(Context context) {
        return new File(MyApplication.instance.FILE_SAVEPATH+"log/", "crash.txt");
    }

    protected abstract void sendReport(String title, String body, File file,String exmsg);

    @Override
    public void afterSaveCrash(File file,String exmsg) {
        sendReport(buildTitle(mContext), buildBody(mContext), file,exmsg);
    }

    public String buildTitle(Context context) {
        return "Crash Log: "
                + context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
    }

    public String buildBody(Context context) {
        StringBuilder sb = new StringBuilder();

        PackageManager pm = context.getPackageManager();
        ApplicationInfo ai = context.getApplicationInfo();
        sb.append("Application : ").append(pm.getApplicationLabel(ai)).append('\n');

        try {
            PackageInfo pi = pm.getPackageInfo(ai.packageName, 0);
            sb.append("Version Code: ").append(pi.versionCode).append('\n');
            sb.append("Version Name: ").append(pi.versionName).append('\n');
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        sb.append('\n').append("DEVICE INFORMATION").append('\n');
        sb.append("Board: ").append(Build.BOARD).append('\n');
        sb.append("BOOTLOADER: ").append(Build.BOOTLOADER).append('\n');
        sb.append("BRAND: ").append(Build.BRAND).append('\n');
        sb.append("CPU_ABI: ").append(Build.CPU_ABI).append('\n');
        sb.append("CPU_ABI2: ").append(Build.CPU_ABI2).append('\n');
        sb.append("DEVICE: ").append(Build.DEVICE).append('\n');
        sb.append("DISPLAY: ").append(Build.DISPLAY).append('\n');
        sb.append("FINGERPRINT: ").append(Build.FINGERPRINT).append('\n');
        sb.append("HARDWARE: ").append(Build.HARDWARE).append('\n');
        sb.append("HOST: ").append(Build.HOST).append('\n');
        sb.append("ID: ").append(Build.ID).append('\n');
        sb.append("MANUFACTURER: ").append(Build.MANUFACTURER).append('\n');
        sb.append("PRODUCT: ").append(Build.PRODUCT).append('\n');
        sb.append("TAGS: ").append(Build.TAGS).append('\n');
        sb.append("TYPE: ").append(Build.TYPE).append('\n');
        sb.append("USER: ").append(Build.USER).append('\n');
        return sb.toString();
    }
}
