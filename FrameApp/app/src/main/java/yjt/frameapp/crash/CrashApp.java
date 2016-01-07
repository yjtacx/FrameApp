package yjt.frameapp.crash;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.io.File;

import yjt.frameapp.R;
import yjt.frameapp.managers.AppManager;

/**
 * Created by yujiangtao on 2016/1/6.
 */
public class CrashApp extends AbstractCrashReportHandler {
    private Context context=null;

    public CrashApp(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    protected void sendReport(String title, String body, File file) {

    }
    /**
     * 发送App异常崩溃报告
     *
     * @param
     * @param crashReport
     */
    public static void sendAppCrashReport(final Context context,
                                          final String crashReport) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setCancelable(false);
        builder.setTitle(R.string.app_error);
        builder.setMessage(R.string.app_error_message);
//		builder.setMessage(crashReport);
        builder.setPositiveButton(R.string.submit_report,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 发送异常报告
                        Intent i = new Intent(Intent.ACTION_SEND);
                        // i.setType("text/plain"); //模拟器
                        i.setType("message/rfc822"); // 真机
                        // 接收错误报告的邮箱地址
                        i.putExtra(Intent.EXTRA_EMAIL,
                                new String[] { "yjtacx@163.com" });
                        i.putExtra(Intent.EXTRA_SUBJECT,
                                "XXX,Android客户端 - 错误报告");
                        i.putExtra(Intent.EXTRA_TEXT, crashReport);
                        context.startActivity(Intent.createChooser(i, "发送错误报告"));
                        // 退出
                        AppManager.getAppManager().AppExit(context);
                    }
                });
        builder.setNegativeButton(R.string.cancle,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 退出
                        AppManager.getAppManager().AppExit(context);
                    }
                });
        builder.show();
    }
}
