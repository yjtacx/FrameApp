package yjt.frameapp.crash;

import java.io.File;

/**
 * Created by yujiangtao on 2016/1/6.
 */
public interface CrashListener {
    /**
     * 保存异常的日志。
     *
     * @param file
     */
    void afterSaveCrash(File file,String exmsg);

}
