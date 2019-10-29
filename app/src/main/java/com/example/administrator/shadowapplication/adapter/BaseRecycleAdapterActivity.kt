package com.example.administrator.shadowapplication.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_base_recycle_adapter.*
import java.util.*

class BaseRecycleAdapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_recycle_adapter)
        createData()
        //val adapter = BaseRecycleAdapter(dataList)
        val adapter = BaseRecycleAdapter2(dataList)


        adapter.openLoadAnimation()
        mRecycle.layoutManager = LinearLayoutManager(this)
        mRecycle.adapter = adapter

    }

    lateinit var dataList: ArrayList<ShadowMultiItem>
    private fun createData() {
        dataList = ArrayList()
        dataList.clear()
        for (i in 0..20) {
            val shadowMultiItem = ShadowMultiItem()
            val shadow = Shadow()
            shadow.name = "shadow--- $i"
            shadow.age = i.toString()
            shadowMultiItem.shadow = shadow;
            when (i) {
                0, 1, 2 -> {
                    shadowMultiItem.type = ShadowMultiItem.MULTI_TYPE_ONE
                }

                3, 4, 5, 6 -> {
                    shadowMultiItem.type = ShadowMultiItem.MULTI_TYPE_THREE
                }
                7 -> {
                    shadowMultiItem.type = ShadowMultiItem.MULTI_TYPE_FOUR
                }
                8, 9 -> {
                    shadowMultiItem.type = ShadowMultiItem.MULTI_TYPE_TWO
                }

                10, 11 -> {
                    shadowMultiItem.type = ShadowMultiItem.MULTI_TYPE_THREE
                }

                12, 13, 14 -> {
                    shadowMultiItem.type = ShadowMultiItem.MULTI_TYPE_FOUR
                }

                15 -> {
                    shadowMultiItem.type = ShadowMultiItem.MULTI_TYPE_TWO
                }

                16, 17 -> {
                    shadowMultiItem.type = ShadowMultiItem.MULTI_TYPE_ONE
                }

                18, 19, 20 -> {
                    shadowMultiItem.type = ShadowMultiItem.MULTI_TYPE_TWO
                }
            }
            dataList.add(shadowMultiItem)
        }
    }
}
