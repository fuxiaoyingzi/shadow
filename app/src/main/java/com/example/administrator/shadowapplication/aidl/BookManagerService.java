package com.example.administrator.shadowapplication.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.administrator.shadowapplication.IOnNewBookArrivedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import okio.ForwardingTimeout;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/28
 *     desc   :
 * </pre>
 */
public class BookManagerService extends Service {
    private CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<>();
    //使用CopyOnWriteArrayList 存储listener对象不是惟一的，对象经过序列化会在客户端生成新的对象，
    // 所以客户端和服务端的对象listener不是同一个
    //private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList =new CopyOnWriteArrayList<>();
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    //系统提供的，专门用于删除跨进程listener的接口，RemoteCallbackList并不是list
    //当客户端进程终止的时候，能自动移除客户端注册的listener
    //内部实现了线程同步功能，所以使用他进行注册和解除注册时候，不需要做额外的线程同步功能
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        for (int i=0;i<3;i++){
            Book book = new Book(i+1,"小王子"+i,10.5f) ;
            bookList.add(book);
        }
        new Thread(new ServiceWork()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BookRemoteManagerImpl(bookList,mListenerList);
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    /**
     * 每五秒添加一本新书
     */
    public  class ServiceWork implements Runnable{
        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()){
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                int bookId = bookList.size()+1;
                Book book = new Book(bookId,"小王子"+bookId,10.5f) ;
                onNewBookArrived(book);
            }

        }
    }

    /**
     * 添加新书
     * 远程服务端调用客户端的listener中的方法时，被调用的方法也运行在Binder线程池，客户端的Binder线程池
     * 不能在服务端，调用客户端比较耗时的方法，比如 listener.onNewBookArried(book);
     * 如果onNewBookArried比较耗时，就需要开启新的线程
     * @param book
     */
    private void onNewBookArrived(Book book) {
        bookList.add(book);
        /*for (int i = 0; i< mListenerList.size();i++){
            IOnNewBookArrivedListener listener = mListenerList.get(i);
            try {
                listener.onNewBookArried(book);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }*/
        //beginBroadcast 和finishBroadcast 配对出现
        final int listenerSize = mListenerList.beginBroadcast();
        for (int i = 0; i< listenerSize ; i++){
            IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
            if (listener != null){
                try {
                    listener.onNewBookArried(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();
    }


}
