package com.example.administrator.shadowapplication.multitype

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_multitype.*
import me.drakeet.multitype.MultiTypeAdapter
import java.util.ArrayList

class MultitypeActivity : AppCompatActivity() {
    lateinit var list:ArrayList<Title>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multitype)
        val manager = LinearLayoutManager(this)
        recycleViewLayout.layoutManager = manager
        val adapter = MultiTypeAdapter()
        adapter.register(Title::class.java,TitleViewBinder())
        recycleViewLayout.adapter = adapter
         list = ArrayList()
        list.add(Title("shadow","http://www.baidu.com"))//模拟的初始化数据，伪代码
        list.add(Title("shadow2","http://www.baidu.com"))//模拟的初始化数据，伪代码
        list.add(Title("shadow3","http://www.baidu.com"))//模拟的初始化数据，伪代码
        list.add(Title("shadow4","http://www.baidu.com"))//模拟的初始化数据，伪代码
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
}
