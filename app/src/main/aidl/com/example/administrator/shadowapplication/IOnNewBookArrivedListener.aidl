// IOnNewBookArrivedListener.aidl
package com.example.administrator.shadowapplication;

// Declare any non-default types here with import statements
import com.example.administrator.shadowapplication.aidl.Book;
interface IOnNewBookArrivedListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onNewBookArried(in Book book);

}
