package com.example.administrator.shadowapplication.aidl;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.example.administrator.shadowapplication.IOnNewBookArrivedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/28
 *     desc   :
 * </pre>
 */

public class BookRemoteManagerImpl extends BookRemoteInterface.Stub {
    private CopyOnWriteArrayList<Book> bookList ;
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList ;

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }


    public BookRemoteManagerImpl(CopyOnWriteArrayList<Book> bookList,RemoteCallbackList<IOnNewBookArrivedListener> mListenerList) {
        this.bookList = bookList;
        this.mListenerList = mListenerList;
    }

    /**
     * 服务端的方法本身就运行在服务端Binder线程池中，所以服务端方法可以执行大量耗时操作
     * 这个时候不要在服务端开启子线程执行异步任务
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Book> getBookList() throws RemoteException {
        return bookList;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        bookList.add(book);
    }

    @Override
    public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
       /* if (!mListenerList.contains(listener)){
            mListenerList.add(listener);
        }else {
            Log.d("hh","already exists");
        }
        Log.d("hh","registerListener mListenerList.size = "+mListenerList.size());*/
       mListenerList.register(listener);
    }

    @Override
    public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
       /* if (mListenerList.contains(listener)){
            mListenerList.remove(listener);
        }else {
            Log.d("hh","not found ,not can unregister");
        }
        Log.d("hh","unregisterListener mListenerList.size = "+mListenerList.size());*/
       mListenerList.unregister(listener);
    }
}
