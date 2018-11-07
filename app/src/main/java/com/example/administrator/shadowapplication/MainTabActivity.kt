package com.example.administrator.shadowapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.administrator.shadowapplication.Gallery.GalleryActivity
import com.example.administrator.shadowapplication.Gallery.GoodsDetailActivity
import com.example.administrator.shadowapplication.Gallery.TestViewPagerChangeDataActivity
import com.example.administrator.shadowapplication.Gallery.ViewPagerChangeDataActivity
import com.example.administrator.shadowapplication.activity.*
import com.example.administrator.shadowapplication.aidl.AidlBindServiceActivity
import com.example.administrator.shadowapplication.album.AlbumActivity
import com.example.administrator.shadowapplication.android_http.DownImageActivity
import com.example.administrator.shadowapplication.android_http.DownWebPageActivity
import com.example.administrator.shadowapplication.anim.ValueAnimatorActivity
import com.example.administrator.shadowapplication.broadcast_receive.BroadcastReceiveActivity
import com.example.administrator.shadowapplication.databing.UserActivity
import com.example.administrator.shadowapplication.date.CalendarActivity
import com.example.administrator.shadowapplication.date.DateMainActivity
import com.example.administrator.shadowapplication.db.SqliteTestActivity
import com.example.administrator.shadowapplication.eventbus.EventBusTestActivity
import com.example.administrator.shadowapplication.glide.GiphyActivity
import com.example.administrator.shadowapplication.hot_fix.HotRestoreActivity
import com.example.administrator.shadowapplication.http.HttpUrlConnectionActivity
import com.example.administrator.shadowapplication.http.RetrofitActivity

import com.example.administrator.shadowapplication.ipc.IPCServiceActivity
import com.example.administrator.shadowapplication.map.AMapActivity
import com.example.administrator.shadowapplication.map.AndroidLocationActivity
import com.example.administrator.shadowapplication.map.MapActivity
import com.example.administrator.shadowapplication.material_design.FooterBehaviorActivity
import com.example.administrator.shadowapplication.material_design.MaterialMainActivity
import com.example.administrator.shadowapplication.media.MediaActivity
import com.example.administrator.shadowapplication.recycle.RecycleStaggeredActivity
import com.example.administrator.shadowapplication.scrollview.TestScrollviewActivity
import com.example.administrator.shadowapplication.setting.SettingActivity
import com.example.administrator.shadowapplication.thread_test.AsyncTaskActivity
import com.example.administrator.shadowapplication.timer.CustomTimer1Activity
import kotlinx.android.synthetic.main.activity_main_tab.*

class MainTabActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.valueAnimator -> {
                startActivity(Intent(this, ValueAnimatorActivity::class.java))
            }

            R.id.scrollView -> {
                startActivity(Intent(this, TestScrollviewActivity::class.java))
            }

            R.id.album -> {
                startActivity(Intent(this, AlbumActivity::class.java))
            }

            R.id.gallery -> {
                startActivity(Intent(this, GalleryActivity::class.java))
            }
            R.id.viewPagerChangeData -> {
                startActivity(Intent(this, ViewPagerChangeDataActivity::class.java))
            }
            R.id.goodsDetail -> {
                startActivity(Intent(this, GoodsDetailActivity::class.java))
            }

            R.id.viewPagerChangeData2 -> {
                startActivity(Intent(this, TestViewPagerChangeDataActivity::class.java))
            }

            R.id.calendar -> {
                startActivity(Intent(this, CalendarActivity::class.java))
            }

            R.id.media -> {
                startActivity(Intent(this, MediaActivity::class.java))
            }

            R.id.androidLocation -> {
                startActivity(Intent(this, AndroidLocationActivity::class.java))
            }

            R.id.material -> {
                startActivity(Intent(this, MaterialMainActivity::class.java))
            }

            R.id.footerBehavior -> {
                startActivity(Intent(this, FooterBehaviorActivity::class.java))
            }

            R.id.retrofit -> {
                startActivity(Intent(this, RetrofitActivity::class.java))
            }

            R.id.sqliteTest -> {
                startActivity(Intent(this, SqliteTestActivity::class.java))
            }

            R.id.aidlBindService -> {
                startActivity(Intent(this, AidlBindServiceActivity::class.java))
            }

            R.id.broadcastReceive -> {
                startActivity(Intent(this, BroadcastReceiveActivity::class.java))
            }

            R.id.eventBusTest -> {
                startActivity(Intent(this, EventBusTestActivity::class.java))
            }

            R.id.iPCService -> {
                startActivity(Intent(this, IPCServiceActivity::class.java))
            }

            R.id.customShaderText -> {
                startActivity(Intent(this, CustomShaderTextViewActivity::class.java))
            }

            R.id.glide -> {
                startActivity(Intent(this, GiphyActivity::class.java))
            }

            R.id.httpUrlConnection -> {
                startActivity(Intent(this, HttpUrlConnectionActivity::class.java))
            }

            R.id.newCustomView -> {
                startActivity(Intent(this, NewCustomViewActivity::class.java))
            }

            R.id.customViewGroup -> {
                startActivity(Intent(this, CustomViewGroupActivity::class.java))
            }

            R.id.customColorMatrix -> {
                startActivity(Intent(this, CustomColorMatrixActivity::class.java))
            }

            R.id.customWatch -> {
                startActivity(Intent(this, CustomWatchActivity::class.java))
            }

            R.id.notification -> {
                startActivity(Intent(this, NotificationActivity::class.java))
            }

            R.id.main -> {
                startActivity(Intent(this, MainActivity::class.java))
            }

            R.id.testWeb -> {
                startActivity(Intent(this, TestWebActivity::class.java))
            }

            R.id.asyncTask -> {
                startActivity(Intent(this, AsyncTaskActivity::class.java))
            }

            R.id.setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }

            R.id.dataBinding -> {
                startActivity(Intent(this, UserActivity::class.java))
            }

            R.id.map -> {
                startActivity(Intent(this, MapActivity::class.java))
            }

            R.id.material2 -> {
                startActivity(Intent(this, MaterialDesignActivity::class.java))
            }

            R.id.textView -> {
                startActivity(Intent(this, TextViewActivity::class.java))
            }

            R.id.dateMain -> {
                startActivity(Intent(this, DateMainActivity::class.java))
            }

            R.id.viewpagerGallery -> {
                startActivity(Intent(this, ViewpagerGrallerActivity::class.java))
            }

            R.id.multitypeActivity -> {
                startActivity(Intent(this, multitypeActivity::class.java))
            }

            R.id.popupWindowActivity -> {
                startActivity(Intent(this, popupWindowActivity::class.java))
            }

            R.id.tvAmap -> {
                startActivity(Intent(this, AMapActivity::class.java))
            }
            R.id.tvTimerLayout -> {
                startActivity(Intent(this, CustomTimer1Activity::class.java))
            }
            R.id.tvRecycleStaggered -> {
                startActivity(Intent(this, RecycleStaggeredActivity::class.java))
            }

            R.id.tvHxDemo -> {
                //startActivity(Intent(this, HxDemoActivity::class.java))
            }
            R.id.tvDownPage -> {
                startActivity(Intent(this, DownWebPageActivity::class.java))
            }
            R.id.tvDownImage -> {
                startActivity(Intent(this, DownImageActivity::class.java))
            }

            R.id.tvHotRestore -> {
                startActivity(Intent(this, HotRestoreActivity::class.java))
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)
        valueAnimator.setOnClickListener(this)
        scrollView.setOnClickListener(this)
        album.setOnClickListener(this)
        gallery.setOnClickListener(this)
        viewPagerChangeData.setOnClickListener(this)
        goodsDetail.setOnClickListener(this)
        viewPagerChangeData2.setOnClickListener(this)
        calendar.setOnClickListener(this)
        media.setOnClickListener(this)
        androidLocation.setOnClickListener(this)
        material.setOnClickListener(this)
        footerBehavior.setOnClickListener(this)
        retrofit.setOnClickListener(this)
        sqliteTest.setOnClickListener(this)
        aidlBindService.setOnClickListener(this)
        broadcastReceive.setOnClickListener(this)
        eventBusTest.setOnClickListener(this)
        iPCService.setOnClickListener(this)
        customShaderText.setOnClickListener(this)
        glide.setOnClickListener(this)
        httpUrlConnection.setOnClickListener(this)
        newCustomView.setOnClickListener(this)
        customViewGroup.setOnClickListener(this)
        customColorMatrix.setOnClickListener(this)
        customWatch.setOnClickListener(this)
        notification.setOnClickListener(this)
        main.setOnClickListener(this)
        testWeb.setOnClickListener(this)
        asyncTask.setOnClickListener(this)
        setting.setOnClickListener(this)
        dataBinding.setOnClickListener(this)
        map.setOnClickListener(this)
        material2.setOnClickListener(this)
        textView.setOnClickListener(this)
        dateMain.setOnClickListener(this)
        viewpagerGallery.setOnClickListener(this)
        multitypeActivity.setOnClickListener(this)
        popupWindowActivity.setOnClickListener(this)
        tvAmap.setOnClickListener(this)
        tvTimerLayout.setOnClickListener(this)
        tvRecycleStaggered.setOnClickListener(this)
        tvHxDemo.setOnClickListener(this)
        tvDownPage.setOnClickListener(this)
        tvDownImage.setOnClickListener(this)
        tvHotRestore.setOnClickListener(this)
    }
}
