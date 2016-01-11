package yjt.frameapp;

import android.app.Application;
import android.os.Environment;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.border.BorderConfiguration;
import com.elvishew.xlog.formatter.log.DefaultLogFormatter;
import com.elvishew.xlog.formatter.message.json.DefaultJsonFormatter;
import com.elvishew.xlog.formatter.message.method.DefaultMethodFormatter;
import com.elvishew.xlog.formatter.message.throwable.DefaultThrowableFormatter;
import com.elvishew.xlog.formatter.message.xml.DefaultXmlFormatter;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.SystemPrinter;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;

import yjt.frameapp.crash.CrashApp;

/**
 * Created by yujiangtao on 2015/12/28.
 */
public class MyApplication extends Application{
    public static  MyApplication instance=null;
    public String FILE_SAVEPATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/yjt_frame/";
    public String FILE_LOG_PATH=FILE_SAVEPATH+"log/";
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        new CrashApp(this);
    }


}
