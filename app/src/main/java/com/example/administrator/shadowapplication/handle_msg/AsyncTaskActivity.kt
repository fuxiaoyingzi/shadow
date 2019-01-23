package com.example.administrator.shadowapplication.handle_msg

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.administrator.shadowapplication.R
import java.text.SimpleDateFormat
import java.util.*

class AsyncTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task2)
        MyAsyncTask("syncTask1").execute()
        MyAsyncTask("syncTask2").execute()
        MyAsyncTask("syncTask3").execute()
        MyAsyncTask("syncTask4").execute()
       /* mAsync.execute()
        mAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"")*/
    }

    class MyAsyncTask constructor(var name:String):AsyncTask<String, Int, Boolean>(){

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("hh","onPreExecute $name")
        }

        override fun doInBackground(vararg params: String?): Boolean {
            Thread.sleep(300)
            return true
        }


        override fun onPostExecute(result: Boolean?) {
           val df =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            Log.d("hh","onPostExecute $name =  ${df.format(Date())}")
        }

    }
}
