package com.example.administrator.shadowapplication.process_communication.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

/**
 * @author 付影影
 * @desc 内容提供者
 * @date 2019/8/2
 */
public class BookContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.android.shadow.book.provider";
    private static final String BOOK_PATH = "book";
    private static final String USER_PATH = "user";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BOOK_PATH);
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + USER_PATH);

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher mUrimatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /**
     * 把 uri 和 uri_code 绑定起来
     */
    static {
        mUrimatcher.addURI(AUTHORITY, BOOK_PATH, BOOK_URI_CODE);
        mUrimatcher.addURI(AUTHORITY, USER_PATH, USER_URI_CODE);
    }

    /**
     * 根据传递过来的uri，匹配uri_code,获取表格名称
     *
     * @param uri
     * @return
     */
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (mUrimatcher.match(uri)) {
            case USER_URI_CODE:
                tableName = BookDataBase.USER_TABLE_NAME;
                break;
            case BOOK_URI_CODE:
                tableName = BookDataBase.BOOK_TABLE_NAME;
                break;
        }
        return tableName;
    }

    private SQLiteDatabase mDatabase;

    private Context mContext;

    /**
     * onCreate 运行在主进程 不要做耗时操作
     *
     * @return
     */
    @Override
    public boolean onCreate() {

        Log.d("hh", "onCreate current thread:" + Thread.currentThread().getName());
        mContext = getContext();
        mDatabase = new BookDataBase(mContext).getWritableDatabase();
        return true;
    }

    /**
     * 以及其他的几个方法 都运行在binder 线程中，可鞥会出现线程同步的问题
     * 但是因为用的是一个database，而database 内部实现了线程同步，所以在此处不需要处理
     * 但是如果用别的数据存储形式实现provide，比如list，文件等就需要考虑同步问题
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Log.d("hh", "query current thread:" + Thread.currentThread().getName());
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("unSupport URI:" + uri);
        }
        return mDatabase.query(tableName, strings, s, strings1, null, null, s, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d("hh", "getType current thread:" + Thread.currentThread().getName());
        return null;
    }

    /**
     * 插入数据
     *
     * @param uri
     * @param contentValues
     * @return
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Log.d("hh", "insert current thread:" + Thread.currentThread().getName());


        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("unSupport URI:" + uri);
        }
        mDatabase.insert(tableName, null, contentValues);
        //insert update delete 等方法会引起数据源的改变，所以需要通过contentResolve 的notifyChange方法
        //通知外界当前contentProvider的数据已经发生改变
        //如果需要观察一个ContentProvider中的数据改变情况，可以通过contentResolve的registerContentObserver
        //方法来注册观察者，通过unRegisterContentObserver方法来解除观察者
        Log.d("hh", "insert success ");
        mContext.getContentResolver().notifyChange(uri, null);
        return null;
    }

    /**
     * 删除数据
     *
     * @param uri
     * @param s
     * @param strings
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        Log.d("hh", "delete current thread:" + Thread.currentThread().getName());
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("unSupport URI:" + uri);
        }
        int column = mDatabase.delete(tableName, s, strings);
        if (column > 0) {
            Log.d("hh", "delete success " + column);
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return 0;
    }

    /**
     * 更新数据
     *
     * @param uri
     * @param contentValues
     * @param s
     * @param strings
     * @return
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        Log.d("hh", "update current thread:" + Thread.currentThread().getName());
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("unSupport URI:" + uri);
        }
        int column = mDatabase.update(tableName, contentValues, s, strings);
        if (column > 0) {
            Log.d("hh", "update success " + column);
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return 0;
    }
}
