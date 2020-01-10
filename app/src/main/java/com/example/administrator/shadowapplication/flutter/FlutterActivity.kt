package com.example.administrator.shadowapplication.flutter

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.example.administrator.shadowapplication.flutter.channel.MethodChannelPlugin
import io.flutter.facade.Flutter
import io.flutter.facade.FlutterFragment
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.FlutterView
import java.util.*


/**
 * native项目加载flutter页面
将flutter页面构建成View，通过addView来显示flutter页面
将flutter页面构建成Fragment，通过对fragment的操作来显示flutter页面
 */
class FlutterActivity : FragmentActivity() {
    companion object {
        private const val TAG_FLUTTER_FRAGMENT = "flutter_fragment"
        private var flutterFragment: FlutterFragment? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //方法1
        /* setContentView(R.layout.activity_flutter)
         val fragmentManager: FragmentManager = supportFragmentManager
         flutterFragment = fragmentManager.findFragmentByTag(TAG_FLUTTER_FRAGMENT) as FlutterFragment?
         if (flutterFragment == null) {
             val newFlutterFragment = Flutter.createFragment("这里是flutter页面")
             flutterFragment = newFlutterFragment
             fragmentManager.beginTransaction()
                     .add(R.id.flutterContent, newFlutterFragment, TAG_FLUTTER_FRAGMENT)
                     .commit()
         }*/

        //方法2
        flutterView = Flutter.createView(this, lifecycle, "route1")
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        layoutParams.topMargin = 20
        addContentView(flutterView, layoutParams)

        //生成plateForm,注册 handle
        MethodChannelPlugin(flutterView)

    }

    private lateinit var flutterView: FlutterView;

    /**
     * 获取当前面 电量
     */
    fun getBatteryLevel(): Int {
        val batteryLevel: Int
        batteryLevel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else {
            val intent = ContextWrapper(applicationContext).registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 / intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        }
        return batteryLevel
    }


    /**
     * 和 Flutter 调用 Android 的代码是类似的，
     * 处创建 MethodChannel，
     * 调用 Flutter 端的 showText 方法，并将数据以 Map 的形式传递过去。
     * MethodChannel.Result () 的回调里有三个方法，通过这三个方法可以得到 Android 调用 Flutter 的结果。
     */
    override fun onResume() {
        super.onResume()
        val map = HashMap<String, String>()
        map["content"] = "Hello 付小影子，我是Android"
        val methodChannel = MethodChannel(flutterView, "MethodChannelPlugin")
        methodChannel.invokeMethod("showText", map, object : MethodChannel.Result {
            //2
            override fun success(o: Any?) {
                Log.d("hh", o as String?)
            }

            override fun error(errorCode: String, errorMsg: String?, errorDetail: Any?) {
                Log.d("hh", "errorCode:" + errorCode + " errorMsg:" + errorMsg + " errorDetail:" + errorDetail as String?)
            }

            override fun notImplemented() {
                Log.d("hh", "notImplemented")
            }
        })
    }
}
