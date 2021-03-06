package com.example.administrator.shadowapplication

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bugtags.library.Bugtags
import com.example.administrator.shadowapplication.Gallery.GalleryActivity
import com.example.administrator.shadowapplication.Gallery.GoodsDetailActivity
import com.example.administrator.shadowapplication.Gallery.TestViewPagerChangeDataActivity
import com.example.administrator.shadowapplication.Gallery.ViewPagerChangeDataActivity
import com.example.administrator.shadowapplication.activity.*
import com.example.administrator.shadowapplication.adapter.BaseRecycleAdapterActivity
import com.example.administrator.shadowapplication.aidl.AidlBindServiceActivity
import com.example.administrator.shadowapplication.album.AlbumActivity
import com.example.administrator.shadowapplication.android_drawable.AndroidDrawableActivity
import com.example.administrator.shadowapplication.android_http.DownImageActivity
import com.example.administrator.shadowapplication.android_http.DownWebPageActivity
import com.example.administrator.shadowapplication.anim.LottieActivity
import com.example.administrator.shadowapplication.anim.PathActivity
import com.example.administrator.shadowapplication.anim.TweenAnimationActivity
import com.example.administrator.shadowapplication.anim.ValueAnimatorActivity
import com.example.administrator.shadowapplication.broadcast_receive.BroadcastReceiveActivity
import com.example.administrator.shadowapplication.constraint.ConstraintActivity
import com.example.administrator.shadowapplication.dagger.test.WatchActivity
import com.example.administrator.shadowapplication.databing.UserActivity
import com.example.administrator.shadowapplication.date.CalendarActivity
import com.example.administrator.shadowapplication.date.DateMainActivity
import com.example.administrator.shadowapplication.db.SqliteTestActivity
import com.example.administrator.shadowapplication.dialog.CustomDialog
import com.example.administrator.shadowapplication.dispatch.DispatchTouchEventActivity
import com.example.administrator.shadowapplication.eventbus.EventBusTestActivity
import com.example.administrator.shadowapplication.flutter.FlutterActivity
import com.example.administrator.shadowapplication.glide.GiphyActivity
import com.example.administrator.shadowapplication.handle_msg.ThreadPoolActivity
import com.example.administrator.shadowapplication.hot_fix.HotRestoreActivity
import com.example.administrator.shadowapplication.http.HttpUrlConnectionActivity
import com.example.administrator.shadowapplication.http.retrofit.RetrofitActivity
import com.example.administrator.shadowapplication.ipc.IPCServiceActivity
import com.example.administrator.shadowapplication.ipc.messenger.MessengerServiceActivity
import com.example.administrator.shadowapplication.jpush.JpushMainActivity
import com.example.administrator.shadowapplication.map.AMapActivity
import com.example.administrator.shadowapplication.map.AndroidLocationActivity
import com.example.administrator.shadowapplication.map.MapActivity
import com.example.administrator.shadowapplication.material_design.FooterBehaviorActivity
import com.example.administrator.shadowapplication.material_design.MaterialMainActivity
import com.example.administrator.shadowapplication.material_design.ScrollingBehaviorActivity
import com.example.administrator.shadowapplication.material_design.ScrollingBehaviorActivity1
import com.example.administrator.shadowapplication.media.MediaActivity
import com.example.administrator.shadowapplication.nine_gridlayout.NineGridlayoutActivity
import com.example.administrator.shadowapplication.notification.NewNotificationActivity
import com.example.administrator.shadowapplication.open_gl.OpenGlActivity
import com.example.administrator.shadowapplication.popup_windows.PopupActivity
import com.example.administrator.shadowapplication.process_communication.ProcessCommunicationActivity
import com.example.administrator.shadowapplication.progress.ProgressActivity
import com.example.administrator.shadowapplication.recycle.RecycleStaggeredActivity
import com.example.administrator.shadowapplication.scrollview.TestScrollviewActivity
import com.example.administrator.shadowapplication.service_demo.activity.ServiceActivity
import com.example.administrator.shadowapplication.setting.SettingActivity
import com.example.administrator.shadowapplication.thread_test.AsyncTaskActivity
import com.example.administrator.shadowapplication.timer.CustomTimer1Activity
import kotlinx.android.synthetic.main.activity_main_tab.*
import me.ele.uetool.UETool

class MainTabActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.valueAnimator -> {
                startActivity(Intent(this, ValueAnimatorActivity::class.java))
            }

            R.id.pathAnimator -> {
                startActivity(Intent(this, PathActivity::class.java))
            }
            R.id.lottieAc -> {
                startActivity(Intent(this, LottieActivity::class.java))
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

            R.id.tvService -> {
                startActivity(Intent(this, ServiceActivity::class.java))
            }
            R.id.tvNotification -> {
                startActivity(Intent(this, NewNotificationActivity::class.java))
            }

            R.id.tvProgress -> {
                startActivity(Intent(this, ProgressActivity::class.java))
            }

            R.id.scrollBehavior -> {
                startActivity(Intent(this, ScrollingBehaviorActivity::class.java))
            }

            R.id.scrollBehavior1 -> {
                startActivity(Intent(this, ScrollingBehaviorActivity1::class.java))
                val handler = Handler()
                handler.looper
            }

            R.id.customDialog -> {
                CustomDialog(this, "hello world").show()
            }

            R.id.showPopup -> {
                startActivity(Intent(this, PopupActivity::class.java))

            }

            R.id.messageBindService -> {
                startActivity(Intent(this, MessengerServiceActivity::class.java))

            }
            R.id.nineGridlayout -> {
                startActivity(Intent(this, NineGridlayoutActivity::class.java))

            }

            R.id.androidDraw -> {
                startActivity(Intent(this, AndroidDrawableActivity::class.java))

            }

            R.id.shadowShapeActivity -> {
                startActivity(Intent(this, ShadowShapeActivity::class.java))
            }

            R.id.openGl -> {
                startActivity(Intent(this, OpenGlActivity::class.java))
            }
            R.id.jPushCount -> {
                startActivity(Intent(this, JpushMainActivity::class.java))
            }

            R.id.layoutAnimation -> {
                startActivity(Intent(this, TweenAnimationActivity::class.java))
            }

            R.id.androidDrawable -> {
                startActivity(Intent(this, com.example.administrator.shadowapplication.Drawable.AndroidDrawableActivity::class.java))
            }

            R.id.mAutoSize -> {
                startActivity(Intent(this, AutoSizeActivity::class.java))
            }

            R.id.tinkerUpdate -> {
                startActivity(Intent(this, MediaPlayerActivity::class.java))
            }
            R.id.processCommunication -> {
                startActivity(Intent(this, ProcessCommunicationActivity::class.java))
            }
            R.id.dispatch -> {
                startActivity(Intent(this, DispatchTouchEventActivity::class.java))
            }

            R.id.constraintLayout -> {
                startActivity(Intent(this, ConstraintActivity::class.java))
            }

            R.id.dagger -> {
                startActivity(Intent(this, WatchActivity::class.java))
            }
            R.id.threadPool -> {
                startActivity(Intent(this, ThreadPoolActivity::class.java))
            }
            R.id.BRVAH -> {
                startActivity(Intent(this, BaseRecycleAdapterActivity::class.java))
            }

            R.id.flutter -> {
                startActivity(Intent(this, FlutterActivity::class.java))
            }


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)
        control.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (!UETool.showUETMenu()) {
                    control.isChecked = false
                }
            } else {
                UETool.dismissUETMenu()
            }
        }
        control.isChecked = false


        valueAnimator.setOnClickListener(this)
        pathAnimator.setOnClickListener(this)
        lottieAc.setOnClickListener(this)
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
        tvService.setOnClickListener(this)
        tvNotification.setOnClickListener(this)
        tvProgress.setOnClickListener(this)
        scrollBehavior.setOnClickListener(this)
        scrollBehavior1.setOnClickListener(this)
        customDialog.setOnClickListener(this)
        showPopup.setOnClickListener(this)
        messageBindService.setOnClickListener(this)
        nineGridlayout.setOnClickListener(this)
        androidDraw.setOnClickListener(this)
        layoutAnimation.setOnClickListener(this)
        shadowShapeActivity.setOnClickListener(this)
        openGl.setOnClickListener(this)
        jPushCount.setOnClickListener(this)
        androidDrawable.setOnClickListener(this)
        mAutoSize.setOnClickListener(this)
        tinkerUpdate.setOnClickListener(this)
        processCommunication.setOnClickListener(this)
        dispatch.setOnClickListener(this)
        constraintLayout.setOnClickListener(this)
        dagger.setOnClickListener(this)
        threadPool.setOnClickListener(this)
        BRVAH.setOnClickListener(this)
        flutter.setOnClickListener(this)

    }

    //APP字体大小不随系统字体大小而改变
    override fun onConfigurationChanged(newConfig: Configuration) {
        //非默认值
        if (newConfig.fontScale != 1.0f) {
            resources
        }
        super.onConfigurationChanged(newConfig)
    }


    override fun getResources(): Resources {
        val res = super.getResources()
        val config = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.displayMetrics)
        return res
    }


    override fun onResume() {
        super.onResume()
        Bugtags.onResume(this);
    }

    override fun onPause() {
        super.onPause()
        Bugtags.onPause(this);
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Bugtags.onDispatchTouchEvent(this, ev)
        return super.dispatchTouchEvent(ev)
    }
}
