package com.example.administrator.shadowapplication.crash_log;

import java.io.File;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/3/2
 *     desc   :
 * </pre>
 */


public interface CrashListener {
    /**
     * called when CrashHandler saved crash log
     *
     * @param
     */
    void afterSaveLog(Throwable ex);
}
