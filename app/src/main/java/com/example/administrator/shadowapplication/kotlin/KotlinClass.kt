package com.example.administrator.shadowapplication.kotlin

/**
 * Author : shadow
 * Desc :
 * Date :2018/7/18/018
 */
class A private constructor(a: String) { //私有构造方法
    companion object { //伴生对象
        //单例构造
        fun getA(): A { //通过伴生对象创建A，类似单例
            return A("shaodw")
        }

        //静态方法，添加jvmStatic 是为了让Java调用不用添加companion
        @JvmStatic
        fun staticMethod() {
            print("hello shadow")
        }

        //静态变量
        @JvmField
        var a: String = "2"
    }

    fun norMethod() {
        print("普通方法")
        //测试扩展方法
        print("hello".test())
        //测试扩展属性
        print("hello".a)
    }

}

fun main(args: Array<String>) {
    val a = A.getA()
    a.norMethod()
    A.staticMethod()
    print(A.a)
}