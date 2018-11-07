package com.example.administrator.shadowapplication.kotlin

import java.lang.Exception

/**
 * Author : shadow
 * Desc : 测试kotlin
 * Date :2018/7/17/017
 */
fun main(args: Array<String>) {

    for ((index ,value) in args.withIndex()){ //循环遍历
        print("$index -> $value")
    }
    while (true){
        try {
            print("请输入算式，例如3 + 4")
            val inputValue= readLine()
            val data = inputValue!!.trim().split(" ")
            if (data.size < 3){
                print("请输入正确的格式")
                throw IllegalArgumentException()
            }
            val arg1 = data[0].toDouble()
            val op = data[1]
            val arg2 = data[2].toDouble()
            print("$arg1 $op $arg2 = ${Operation(op).apply(arg1,arg2)}")
        }catch (e:Exception){
            print("请输入正确的格式 ,空格间隔，3 + 4")
        }
        print("再来一发？[Y]")
        val again = readLine()
        if (again == null || again.toLowerCase() != "y"){
            break
        }
    }
    print("谢谢使用我们的计算器！")

}


class Operation( op:String){
    val a :( arg1:Double, arg2:Double) -> Double //lambda 表达式
    init {
        a = when(op){
            "+" ->{a,b -> a+b}
            "-" ->{a,b -> a-b}
            "*" ->{a,b -> a*b}
            "/" ->{a,b -> a/b}
            "%" ->{a,b -> a%b}
            else ->{
                throw UnsupportedClassVersionError()
            }
        }

    }
    fun apply(arg1: Double,arg2: Double):Double{
        test(1,"1","2","3",c = "4") //可变参数+具名参数
        val array = arrayOf("a","v","b","c")
        test(b = *array,c = "4") //可变参数，具名参数，指定参数
        for (i in array){
            print(i)
        }
        return a(arg1,arg2)

    }

    fun test(a:Int = 1,vararg b:String,c:String){
        print(a)
    }

}



