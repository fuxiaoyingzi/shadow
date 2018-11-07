package com.example.administrator.shadowapplication.sp;

/**
 * Author : shadow
 * Desc : sp 基类
 * Date :2018/3/25/025
 */

public interface ISharedPreference {
    /**
     * 设置值到SharePreference文件中
     *
     * @param key key
     * @param val value
     */
    void setValue(String key, Object val);

    /**
     * 从SharePreference文件中获取int值
     *
     * @param key    key
     * @param defVal default value
     */
    int getInt(String key, int defVal);

    /**
     * 从SharePreference文件中获取boolean值
     *
     * @param key    key
     * @param defVal default value
     */
    boolean getBoolean(String key, boolean defVal);

    /**
     * 从SharePreference文件中获取string值
     *
     * @param key    key
     * @param defVal default value
     */
    String getString(String key, String defVal);

    /**
     * 从SharePreference文件中获取Enum值
     *
     * @param key    key
     * @param defVal default value
     */
    //Enum getEnum(String key, Enum defVal);

    /**
     * 从SharePreference文件中获取long值
     *
     * @param key    key
     * @param defVal default value
     */
    long getLong(String key, long defVal);

    /**
     * 从SharePreference文件中获取float值
     *
     * @param key    key
     * @param defVal default value
     */
    float getFloat(String key, float defVal);

    boolean contains(String key);

    void remove(String key);

    /**
     * 强制提交所有修改内容
     */
    void apply();
}
