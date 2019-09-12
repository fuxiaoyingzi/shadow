package com.example.administrator.shadowapplication.map

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdate
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MarkerOptions
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.crash_log.ToastUtil
import com.example.administrator.shadowapplication.popup_windows.PopupWindowUtil
import kotlinx.android.synthetic.main.activity_amap.*
import kotlinx.android.synthetic.main.map_navagation_sheet.view.*


/**
 * 高德地图
 */
class AMapActivity : AppCompatActivity(), AMap.CancelableCallback {
    override fun onFinish() {
        ToastUtil.showMsg("Animation to 陆家嘴 onFinish")
    }

    override fun onCancel() {
        ToastUtil.showMsg("Animation to 陆家嘴 canceled")
    }


    private lateinit var aMap: AMap
    private lateinit var mUiSettings: UiSettings
    lateinit var switcher: PopupWindowUtil
    private var lat: Double = 0.0
    private var lng: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amap)
        amapView.onCreate(savedInstanceState)
        aMap = amapView.map
        mUiSettings = aMap.uiSettings
        mUiSettings.isZoomControlsEnabled = false

        lat = 31.078095
        lng = 121.509477
        val latLng = LatLng(31.078095, 121.509477)
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(
                latLng, 18f, 30f, 0f)))
        aMap.clear()
        aMap.addMarker(MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(resources, R.mipmap.ic_launcher))))


        btnToCenter.setOnClickListener {
            changeCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latLng, 18f, 30f, 0f)), this)
            aMap.clear()
            aMap.addMarker(MarkerOptions().position(latLng)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED)))
        }

        //导航
        ivNavigate.setOnClickListener {
            val parent = (this.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
            val view = LayoutInflater.from(this).inflate(R.layout.map_navagation_sheet, null)
            view.tvCancel.setOnClickListener { switcher.dismissPopup() }
            view.tvAMap.setOnClickListener {
                if (isAvailable(this, "com.autonavi.minimap")) {
                    ToastUtil.showMsg("高德地图")
                   /* val naviIntent = Intent("android.intent.action.VIEW",
                            Uri.parse("androidamap://route?sourceApplication=appName&slat=&slon=&sname=我的位置&dlat=$lat&dlon=$lng&dname=目的地&dev=0&t=2"))
                    startActivity(naviIntent)*/
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    //将功能Scheme以URI的方式传入data
                    val uri = Uri.parse("androidamap://navi?sourceApplication=appname&poiname=fangheng&lat=$lat&lon=$lng&dev=1&style=2")
                    intent.data = uri
                    //启动该页面即可
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "您尚未安装高德地图", Toast.LENGTH_LONG).show()
                    val uri = Uri.parse("market://details?id=com.autonavi.minimap")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                }
                switcher.dismissPopup()
            }
            view.tvBMap.setOnClickListener {
                ToastUtil.showMsg("百度地图")
                if (isAvailable(this, "com.baidu.BaiduMap")) {//传入指定应用包名
                    val naviIntent = Intent("android.intent.action.VIEW",
                            Uri.parse("baidumap://map/geocoder?location=$lat,$lng"))
                    startActivity(naviIntent)


                } else {//未安装
                    //market为路径，id为包名
                    //显示手机上所有的market商店
                    Toast.makeText(this, "您尚未安装百度地图", Toast.LENGTH_LONG).show()
                    val uri = Uri.parse("market://details?id=com.baidu.BaiduMap")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    }
                }
                switcher.dismissPopup()
            }
            switcher = PopupWindowUtil(this, view)
            switcher.showAsBottom(parent)
        }
    }

    /**
     * 是否安装了
     */
    private fun isAvailable(mContext: Context, packagename: String): Boolean {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = mContext.packageManager.getPackageInfo(packagename, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } finally {
            return packageInfo != null
        }
    }


    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private fun changeCamera(update: CameraUpdate, callback: AMap.CancelableCallback?) {
        aMap.animateCamera(update, 1000, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        amapView.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        amapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        amapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        amapView.onSaveInstanceState(outState)
    }
}
