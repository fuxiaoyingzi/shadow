package com.example.administrator.shadowapplication.flutter.channel

import android.app.Activity
import com.example.administrator.shadowapplication.crash_log.ToastUtil
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.StringCodec
import io.flutter.view.FlutterView

/**
 * @author 付影影
 * @desc BasicMessageChannel （主要是传递字符串和一些半结构体的数据）
 * BasicMessageChannel(@NonNull BinaryMessenger messenger, @NonNull String name, @NonNull MessageCodec<T> codec)
 * @date 2020/1/9
 */
class BasicMessageChannelPlugin constructor(flutterView: FlutterView) : BasicMessageChannel.MessageHandler<String> {
    lateinit var activity: Activity;
    lateinit var messageChannel: BasicMessageChannel<String>;
    val CHANNEL_NAME = "BasicMessageChannelPlugin"

    /**
     * BasicMessageChannel的初始化，入参有三个，
     * 第一个是BinaryMessage，
     * 第二个参数是定义的channel name,
     * 第三个参数是MessageCodec解码器
     * 官方提供了四种消息编码器，最终自动转为ByteBuffer
     */
    init {
        activity = flutterView.context as Activity
        messageChannel = BasicMessageChannel<String>(flutterView, CHANNEL_NAME, StringCodec.INSTANCE);
        //设置消息处理器，处理来自dart的消息
        messageChannel.setMessageHandler(this)
    }


    /**
     * 第二种 使用：dart 主动发起，Android端为接收方（收到dart的消息，并且发送回复）
     */
    override fun onMessage(message: String?, reply: BasicMessageChannel.Reply<String>) {
        reply.reply("BasicMessageChannel收到：$message") //可以通过reply进行回复
        ToastUtil.showMsg(message) //接收到的消息
    }

    /**
     * 第一种 使用：发送消息到Dart ，并且 接收到 dart的回复
     */
    fun send(message: String, callBack: BasicMessageChannel.Reply<String>) {
        messageChannel.send(message, callBack)
    }


}