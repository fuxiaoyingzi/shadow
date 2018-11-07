package com.example.administrator.shadowapplication.kotlin

import kotlin.reflect.KProperty

/**
 * Author : shadow
 * Desc : 属性代理
 * Date :2018/7/20/020
 */
class Delegates {
    val a by lazy { "hello shadow by lazy" }
    val b by X() //val代理，初始化值由代理对象X决定
    var c by X() //var代理，可以修改代理值


}

//代理对象,必须要有getValue(),如果是var属性代理，必须要有setValue()方法
class X {
    var value: String? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        print("getValue $thisRef -> ${property.name}")
        return value ?: "hello shadow!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        print("setValue $thisRef -> ${property.name} = $value")
        this.value = value
    }

    fun main(args: Array<String>) {
        val delegate = Delegates()
        print(delegate.a)
        print(delegate.b)
        print(delegate.c)
        delegate.c = "hello world"
        print(delegate.c)
    }

}




