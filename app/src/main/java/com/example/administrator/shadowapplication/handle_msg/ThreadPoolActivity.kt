package com.example.administrator.shadowapplication.handle_msg

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_thread_pool.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory


/**
 *线程池
 */
class ThreadPoolActivity : AppCompatActivity() {
    lateinit var fixThreadPool: ExecutorService
    lateinit var cacheThreadPool: ExecutorService
    lateinit var singleThreadPool: ExecutorService
    lateinit var scheduledThreadPool: ExecutorService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_pool)
        //固定核心线程和最大线程个数，阻塞队列无界
        fixThreadPool = Executors.newFixedThreadPool(5)
        //没有核心线程 最大线程个数不限制，阻塞队列不存储
        cacheThreadPool = Executors.newCachedThreadPool(object : ThreadFactory {
            var count = 0
            override fun newThread(r: Runnable?): Thread {
                Log.d("hh", "新开的线程: newThreadPool_$count")
                val thread = Thread(r, "newThreadPool_${count++}")
                thread.setUncaughtExceptionHandler { t, e ->
                    //处理非正常的线程中止,多线程中通过trycatch试图捕获线程的异常是不可取的
                    Log.d("hh", t.name)
                    e.printStackTrace()
                }
                return thread
            }
        })

        //核心线程和最大线程个数 只有一个，阻塞队列无界
        singleThreadPool = Executors.newSingleThreadExecutor()
        //定时任务的线程池
        scheduledThreadPool = Executors.newScheduledThreadPool(5)


        tvFixThreadPool.setOnClickListener {
            for (i in 0..10) {
                cacheThreadPoolExecutor(Runnable {
                    Log.d("hh", "i = $i")
                })
            }
        }

        ivDownImg.setOnClickListener {
            //downLoadImg("https://y3.cnliveimg.com:8080/image/itv/2016/1112/7e151ac5d7f84f58a4653e879c973192_161409_100.jpg")
            arrayBlockingQueue()
        }
    }

    /**
     * 线程池 调用
     */
    private fun cacheThreadPoolExecutor(r: Runnable) {
        cacheThreadPool.execute(r)
    }


    /**
     * 下载图片
     */
    private fun downLoadImg(path: String) {
        //它有3个泛型参数，分别为Params、Progress和Result，
        // 其中Params为 参数类型，
        // Progress为后台任务执行进度的类型，
        // Result为返回结果的类型。
        // 如果不需要某个参数，可以将 其设置为Void类型
        val asynctask =
                object : AsyncTask<String, Int, Bitmap>() {


                    //在主线程中执行。一般在任务执行前做准备工作，比如对 UI 做一些标记。
                    override fun onPreExecute() {
                        Log.d("hh", "onPreExecute")
                    }

                    //在线程池中执行。在 onPreExecute方法执行后运行，用来执 行较为耗时的操作。在执行过程中可以调用publishProgress（Progress...values）来更新进度信息。
                    override fun doInBackground(vararg params: String?): Bitmap? {
                        Log.d("hh", "doInBackground")
                        val url = URL(params[0])
                        var bitmap: Bitmap? = null
                        /* val inputStream = url.openStream()
                         val bitmap = BitmapFactory.decodeStream(inputStream)*/

                        //开启连接
                        /* val conn = url.openConnection() as HttpURLConnection
                         //设置连接超时
                         conn.connectTimeout = 5000
                         //设置请求方式
                         conn.requestMethod = "GET"
                         //conn.connect();
                         if (conn.responseCode === 200) {
                             val inputStream = conn.getInputStream()
                             bitmap = BitmapFactory.decodeStream(inputStream)
                             inputStream.close()
                         }
                         conn.disconnect()*/


                        val client = OkHttpClient()
                        //获取请求对象
                        val request = Request.Builder().url(url).build()

                        //获取响应体
                        val body = client.newCall(request).execute().body()

                        //获取流
                        val inputStream = body!!.byteStream()
                        //转化为bitmap

                        return BitmapFactory.decodeStream(inputStream)
                    }

                    //：在主线程中执行。当后台任务执行完成后，它会被执行。 doInBackground方法得到的结果就是返回的result的值。此方法一般做任务执行后的收尾工作，比如更新UI 和数据。
                    override fun onPostExecute(result: Bitmap?) {
                        Log.d("hh", "onPostExecute")
                        ivDownImg.setImageBitmap(result)
                    }


                    //在主线程中执行。当调用 publishProgress（Progress...values）时，此方法会将进度更新到UI组件上
                    override fun onProgressUpdate(vararg values: Int?) {
                        Log.d("hh", "onProgressUpdate")
                    }

                }.execute(path)
    }

    fun arrayBlockingQueue() {
        val arrayBlockingQueue = ArrayBlockingQueue<String>(128)
        arrayBlockingQueue.put("hello")
        arrayBlockingQueue.put("world")
        arrayBlockingQueue.offer("hello")
        arrayBlockingQueue.offer("shadow")
        Log.d("hh", "first = ${arrayBlockingQueue.first()}")
        Log.d("hh", "poll = ${arrayBlockingQueue.poll()}")
    }
}
