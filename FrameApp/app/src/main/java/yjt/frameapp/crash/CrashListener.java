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
    public void afterSaveCrash(File file);
}
