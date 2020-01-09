import 'dart:ui';

import 'package:flutter/material.dart';

void main() => runApp(MyApp(
      initParams: window.defaultRouteName,
    ));

class MyApp extends StatelessWidget {
  final String initParams;

  MyApp({Key key, @required this.initParams}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: _getHomePage(),
    );
  }

  Widget _getHomePage() {
    switch (initParams) {
      case "route1":
        return MyHomePage(
          title: '第一个flutter页面',
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

  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
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
            Text(
              'initParams:$initParams',
              style: TextStyle(color: Colors.red),
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.display1,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}
