package com.example.administrator.shadowapplication.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/28
 *     desc   :
 * </pre>
 */
public class BookManagerService extends Service {
    private List<Book> bookList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        bookList = new ArrayList<>();
        for (int i=0;i<3;i++){
            Book book = new Book(i+1,"小王子"+i,10.5f) ;
            bookList.add(book);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BookRemoteManagerImpl(bookList);
    }
}
