package com.example.administrator.shadowapplication.hot_fix.andfix;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author : shadow
 * Desc :
 * Date :2018/11/6/006
 */
public class FixPackageManager {
    private static FixPackageManager mInstance;
    private PatchManager mPatchManager;

    /**
     * 构造方法私有化
     */
    private FixPackageManager() {
    }

    public static FixPackageManager getInstance() {
        if (mInstance == null) {
            synchronized (FixPackageManager.class) {
                if (mInstance == null) {
                    mInstance = new FixPackageManager();
                }
            }
        }
        return mInstance;
    }

    public void initPatch(Context mContext) {
        mPatchManager = new PatchManager(mContext);
        mPatchManager.init(getVersionName(mContext));
        mPatchManager.loadPatch();
    }

    /**
     * path of the patch file that was downloaded
     *
     * @param patchPath
     */
    public void addPatch(String patchPath) {
        try {
            mPatchManager.addPatch(patchPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getVersionName(Context mContext) {
        PackageManager manager = mContext.getPackageManager();
        String versionName = null;
        try {
            PackageInfo mPackageInfo = manager.getPackageInfo(mContext.getPackageName(), 0);
            versionName = mPackageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    private void downLoaderFile(String path) {
        File file = new File(path);
        try {
            InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int line;

            while ((line = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(buffer);
            ((ByteArrayOutputStream) outputStream).toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
