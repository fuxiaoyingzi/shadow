import 'dart:async';
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp(
      initParams: window.defaultRouteName,
    ));

class MyApp extends StatelessWidget {
  final String initParams;

  MyApp({Key key, @required this.initParams}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter 混合开发',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: _getHomePage(),
    );
  }

  ///根据 native 传递的参数，选择要展示的界面
  Widget _getHomePage() {
    switch (initParams) {
      case "route1":
        return MyHomePage(
          title: 'flutter 混合开发',
          initParams: "hello shadow!!",
        );
        break;
    }
  }
}

class MyHomePage extends StatefulWidget {
  final String initParams;

  MyHomePage({Key key, this.title, this.initParams}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState(initParams);
}

class _MyHomePageState extends State<MyHomePage> {
  final String initParams;

  _MyHomePageState(this.initParams);

  ///调⽤通道上的⽅法，指定通过字符串标识符调⽤⽅法	getBatteryLevel	。
  ///该调⽤可能失败(平台不⽀持平台 API，例如在模拟器中运⾏时)，所以我们将invokeMethod调⽤包装在try-catch语句中。
  ///我们使⽤返回的结果，在	setState	中来更新⽤户界⾯状态	batteryLevel
  String _batteryLevel = "unknow battery level";

  //通信方式2
  MethodChannel _methodChannel = MethodChannel("MethodChannelPlugin");

  String textContent = " Flutter 初始数据";

  @override
  void initState() {
    super.initState();
    _methodChannel.setMethodCallHandler((MethodCall call) async {
      switch (call.method) {
        case "showText":
          String content = await call.arguments["content"];
          if (content != null && content.isNotEmpty) {
            setState(() {
              textContent = content;
            });
            return "success";
          } else {
            throw PlatformException(
                code: "error", message: "失败", details: "content is null");
          }
          break;
        default:
          throw MissingPluginException();
      }
    });
  }

  Future<Null> _getBatteryLevel() async {
    String batteryLevel;
    try {
      final int result = await _methodChannel.invokeMethod("getBatteryLevel");
      batteryLevel = 'Battery	level	at	$result	%	.';
    } on PlatformException catch (e) {
      batteryLevel = "Failed	to	get	battery	level:	'${e.message}'.";
    }
    setState(() {
      _batteryLevel = batteryLevel;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            RaisedButton(
              onPressed: () {
                _getBatteryLevel();
              },
              child: Text(
                'FlutterActivity 传递数据:$initParams',
                style: TextStyle(color: Colors.red),
              ),
            ),
            Text(
              '$_batteryLevel',
              style: Theme.of(context).textTheme.display1,
            ),
            Text(" Android端数据 -- $textContent")
          ],
        ),
      ),
    );
  }
}

/// 第二种 通信 方式 BasicMessageChannel
class BasicMessageChannelPage extends StatefulWidget {
  @override
  _BasicMessageChannelPageState createState() =>
      _BasicMessageChannelPageState();
}

class _BasicMessageChannelPageState extends State<BasicMessageChannelPage> {

  String _receiveData ="flutter 默认数据";
  var _messageChannel = BasicMessageChannel<String>("BasicMessageChannelPlugin", StringCodec());

  //发送消息 到 Android，并且收到Android的回复
  Future<String> sendMessage() async {
    String reply = await _messageChannel.send("hello shadow，我是 dart");
    print(reply);
    return reply;
  }

  //从Android 端接收消息，并且发送回复
  void receiveMessage() {
    _messageChannel.setMessageHandler((message) async {
      print("接收 Android 端发送的消息，$message");
      return "哈哈哈哈，这是我给你的回复";
    });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text("EventChanne 接收的Android数据：$_receiveData"),
    );
  }
}

/// 第三种通信方式 EventChannel
class EventChannelPage extends StatefulWidget {
  @override
  _EventChannelPageState createState() => _EventChannelPageState();
}

class _EventChannelPageState extends State<EventChannelPage> {
  String _receiveData = "Flutter 默认数据";
  var CHANNEL_NAME = "EventChannelPlugin";

  var __eventChannel = EventChannel("CHANNEL_NAME");

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    __eventChannel.receiveBroadcastStream().listen(eventListener);
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text("EventChanne 接收的Android数据：$_receiveData"),
    );
  }

  void eventListener(event) {
    print("收到的数据：$event");
    setState(() {
      _receiveData = event;
    });
  }
}
