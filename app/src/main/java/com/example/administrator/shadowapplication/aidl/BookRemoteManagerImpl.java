package com.example.administrator.shadowapplication.aidl;

import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/28
 *     desc   :
 * </pre>
 */

public class BookRemoteManagerImpl extends BookRemoteInterface.Stub {
    private List<Book> bookList ;
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    public BookRemoteManagerImpl(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        return bookList;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        bookList.add(book);
    }
}
