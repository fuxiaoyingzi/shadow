package com.example.administrator.shadowapplication.flutter.channel

import android.app.Activity
import com.example.administrator.shadowapplication.flutter.FlutterActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.FlutterView

/**
 * @author 付影影
 * @desc 用于数据流 event streams的通信，持续通信，通常用于native向dart的通信
 * 如手机电量变化，网络连接变化，陀螺仪，传感器等
 * 用于传递方法调用（method invocation）
 * @date 2020/1/9
 */
class MethodChannelPlugin constructor(flutterView: FlutterView) : MethodChannel.MethodCallHandler {

    var activity: Activity = flutterView.context as Activity
    private val CHANNEL_NAME = "MethodChannelPlugin"

    /**
     * MethodChannel，它有三个参数，两个必传参数
     * FlutterView 实现了 BinaryMessenger 接口。
     * 一个是 MethodChannel 的 Name，这个 Name 要保证是唯一的，后面 Flutter 端实现中会用到这个 Name。
     * 注册 MethodCallHandler，用于监听回调的数据
     */
    init {
        MethodChannel(flutterView, CHANNEL_NAME).setMethodCallHandler(this)
    }


    /**
     * MethodCall 用于或得 dart传递的数据，方法名称，方法参数等
     * MethodChannel.Result 用于dart请求的回调
     *
     * onMethodCall 方法中的 result 是 Flutter 端传来的数据，我们需要对数据进行判断，然后向 Flutter 端发送数据。
       向 Flutter 端发送数据有以下方法：

      result.success(Object result)
      结果成功，将 result 返回给 Flutter 端。

      result.error(String errorCode,String errorMsg,Object errorDetails)
      结果失败，将 errorCode、errorMsg、errorDetails 返回给 Flutter 端。

      result.notImplemented()
      Android 端没有实现 Flutter 端需要的方法，会将 notImplemented 返回给 Flutter 端。
     */
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "getBatteryLevel" -> {
                val batteryLevel = (activity as FlutterActivity).getBatteryLevel()
                if (batteryLevel != -1) {
                    result.success(batteryLevel)
                } else {
                    result.error("UNAVAILABLE", "Battery	level	not	available.", null)
                }
            }
            else -> { // Android 端没有实现 Flutter 端需要的方法，会将 notImplemented 返回给 Flutter 端。
                result.notImplemented()
            }
        }
    }


}