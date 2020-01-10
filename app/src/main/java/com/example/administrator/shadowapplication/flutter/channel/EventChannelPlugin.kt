package com.example.administrator.shadowapplication.flutter.channel

import io.flutter.plugin.common.EventChannel
import io.flutter.view.FlutterView

/**
 * @author 付影影
 * @desc 用于数据流 event streams的通信，持续通信，通常用于native向dart的通信
 * 如手机电量变化，网络连接变化，陀螺仪，传感器等
 * @date 2020/1/9
 */
class EventChannelPlugin constructor(flutterView: FlutterView) : EventChannel.StreamHandler {
    private val CHANNEL_NAME = "EventChannelPlugin"

    init {
        EventChannel(flutterView, CHANNEL_NAME).setStreamHandler(this)
    }

    /**
     * onCancel代表对面不再接收，这里我们应该做一些clean up的事情。
     * onListen则代表通道已经建好，Native可以发送数据了。
     * 注意onListen里带的EventSink这个参数，后续Native发送数据都是经过EventSink的
     */
    var events: EventChannel.EventSink? = null
    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        this.events = events
    }

    override fun onCancel(arguments: Any?) {
        events = null
    }

    /**
     * 给 dart端发送消息
     */
    fun send(params: Any) {
        if (events != null) {
            events!!.success(params)
        }
    }


}