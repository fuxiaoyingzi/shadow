package com.example.administrator.shadowapplication.flutter

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import io.flutter.facade.Flutter
import io.flutter.facade.FlutterFragment

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
        val flutterView = Flutter.createView(this, lifecycle, "route1")
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        layoutParams.topMargin = 20
        addContentView(flutterView, layoutParams)

    }
}
