package com.example.administrator.shadowapplication.adapter

/**
 * @author 付影影
 * @desc
 * @date 2019/10/23
 */
class Shadow {
    var name: String? = null
        get() = if (field == null) "" else field
    var age: String = 0.toString()
}
