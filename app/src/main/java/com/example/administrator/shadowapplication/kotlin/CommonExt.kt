package com.example.administrator.shadowapplication.kotlin

/**
 * Author : shadow
 * Desc :
 * Date :2018/7/20/020
 */

//测试string 扩展方法 fun X.y():Z{}
fun String.test(): Boolean {
    return this == "hello shadow"
}

//测试 扩展属性
val String.a:Int
get() = 6
