package com.example.administrator.shadowapplication.crash_log;


/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/3/2
 *     desc   :此类主要异常数据保存到本地之后，需要些其他的统计操作而设置，发送广播，友盟统计crash信息等
 * </pre>
 */
public class OtherProcessCrashListener implements CrashListener {
    @Override
    public void afterSaveLog(Throwable ex) {

    }
}
