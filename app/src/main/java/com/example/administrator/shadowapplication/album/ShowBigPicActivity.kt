package com.example.administrator.shadowapplication.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_show_big_pic.*
import java.util.ArrayList

class ShowBigPicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_big_pic)
        showBigPic.setOnClickListener{
            val list = ArrayList<String>()
            //网络图片
            list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg")
            list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgdmpxi7erj20qy0qyjtr.jpg")
            list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg")
            list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg")
           /* //本地SD卡图片文件
            list.add("/storage/emulated/0/DCIM/Camera/IMG_20170126_113019.jpg")
            list.add("/storage/emulated/0/DCIM/Camera/IMG_20170126_113014.jpg")
            list.add("/storage/emulated/0/DCIM/Camera/IMG_20170126_114018.jpg")
            list.add("/storage/emulated/0/DCIM/Camera/IMG_20171031_152627.jpg")*/

           /* val config = PictureConfig.Builder()
                    .setListData(list)//图片数据List<String> list
                    .setPosition(0)//图片下标（从第position张图片开始浏览）
                    .setDownloadPath("pictureviewer")//图片下载文件夹地址
                    .setIsShowNumber(true)//是否显示数字下标
                    .needDownload(true)//是否支持图片下载
                    .setPlacrHolder(R.drawable.like)//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                    .build()
            ImagePagerActivity.startActivity(this@ShowBigPicActivity, config)*/

        }
    }
}
