package com.example.administrator.shadowapplication.sp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.example.administrator.shadowapplication.base.MyApp;
import com.example.administrator.shadowapplication.crash_log.LogUtil;

import java.util.HashMap;

/**
 * Author : shadow
 * Desc : sharedPreferences 管理类
 * Date :2018/3/25/025
 */

public class SharedManager implements ISharedPreference {
    private SharedPreferences sharedPreferences;
    private HashMap<String, Object> settings;

    private static SharedManager instance = null;

    public static SharedManager getInstance() {
        if (instance == null) {
            synchronized (SharedManager.class) {
                if (instance == null) {
                    instance = new SharedManager();
                }
            }
        }
        return instance;
    }

    private SharedManager() {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApp.getInstance());
        settings = new HashMap<>();
    }

    /**
     * 设置值到SharePreference文件中
     *
     * @param key key
     * @param val value
     */
    @Override
    public void setValue(String key, Object val) {
        if (TextUtils.isEmpty(key)) return;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (val instanceof Integer) {
            editor.putInt(key, (Integer) val);
        } else if (val instanceof Boolean) {
            editor.putBoolean(key, (Boolean) val);
        } else if (val instanceof String) {
            editor.putString(key, (String) val);
        } else if (val instanceof Long) {
            editor.putLong(key, (Long) val);
        } else if (val instanceof Float) {
            editor.putFloat(key, (Float) val);
        } else if (val instanceof Enum) {
            editor.putString(key, ((Enum) val).name());
        }
        editor.apply();
        settings.put(key, val);
    }

    /**
     * 获取int值，首先从内存读取，若内存不存在则从SharedPreferences文件读取
     *
     * @param key    key
     * @param defVal default value
     * @return 根据key查找到的value，若未找到，则返回默认值
     */
    @Override
    public int getInt(String key, int defVal) {
        if (TextUtils.isEmpty(key)) return defVal;

        Object val = settings.get(key);
        if (val == null) {
            val = sharedPreferences.getInt(key, defVal);
            settings.put(key, val);
        }
        try {
            return (int) val;
        } catch (Exception e) {
            LogUtil.d("hh", "getInt: get key [" + key + "] class is" + val.getClass());
            e.printStackTrace();
            return defVal;
        }
    }

    /**
     * 获取boolean值，首先从内存读取，若内存不存在则从SharedPreferences文件读取
     *
     * @param key    key
     * @param defVal default value
     * @return 根据key查找到的value，若未找到，则返回默认值
     */
    @Override
    public boolean getBoolean(String key, boolean defVal) {
        if (TextUtils.isEmpty(key)) return defVal;

        Object val = settings.get(key);
        if (val == null) {
            val = sharedPreferences.getBoolean(key, defVal);
            settings.put(key, val);
        }
        try {
            return (boolean) val;
        } catch (Exception e) {
            LogUtil.d("hh", "getInt: get key [" + key + "] class is" + val.getClass());
            e.printStackTrace();
            return defVal;
        }
    }

    /**
     * 获取String值，首先从内存读取，若内存不存在则从SharedPreferences文件读取
     *
     * @param key    key
     * @param defVal default value
     * @return 根据key查找到的value，若未找到，则返回默认值
     */
    @Override
    public String getString(String key, String defVal) {
        if (TextUtils.isEmpty(key)) return defVal;

        Object val = settings.get(key);
        if (val == null) {
            val = sharedPreferences.getString(key, defVal);
            settings.put(key, val);
        }
        try {
            return (String) val;
        } catch (Exception e) {
            LogUtil.d("hh", "getInt: get key [" + key + "] class is" + val.getClass());
            e.printStackTrace();
            return defVal;
        }
    }

    /**
     * 获取long值，首先从内存读取，若内存不存在则从SharedPreferences文件读取
     *
     * @param key    key
     * @param defVal default value
     * @return 根据key查找到的value，若未找到，则返回默认值
     */
    @Override
    public long getLong(String key, long defVal) {
        if (TextUtils.isEmpty(key)) return defVal;

        Object val = settings.get(key);
        if (val == null) {
            val = sharedPreferences.getLong(key, defVal);
            settings.put(key, val);
        }
        try {
            return (long) val;
        } catch (Exception e) {
            LogUtil.d("hh", "getInt: get key [" + key + "] class is" + val.getClass());
            e.printStackTrace();
            return defVal;
        }
    }

    /**
     * 获取float值，首先从内存读取，若内存不存在则从SharedPreferences文件读取
     *
     * @param key    key
     * @param defVal default value
     * @return 根据key查找到的value，若未找到，则返回默认值
     */
    @Override
    public float getFloat(String key, float defVal) {
        if (TextUtils.isEmpty(key)) return defVal;

        Object val = settings.get(key);
        if (val == null) {
            val = sharedPreferences.getFloat(key, defVal);
            settings.put(key, val);
        }
        try {
            return (float) val;
        } catch (Exception e) {
            LogUtil.d("hh", "getInt: get key [" + key + "] class is" + val.getClass());
            e.printStackTrace();
            return defVal;
        }
    }

    @Override
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public void remove(String key) {
        settings.remove(key);
        sharedPreferences.edit().remove(key).apply();
    }


    @Override
    public void apply() {
        sharedPreferences.edit().apply();
    }
}
