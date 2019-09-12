package com.example.administrator.shadowapplication.nine_gridlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_nine_gridlayout.*
import org.jetbrains.anko.toast
import java.util.ArrayList

class NineGridlayoutActivity : AppCompatActivity() {

    private lateinit var mAdapter: PreviewShareAdapter
    private lateinit var mDataList:MutableList<PreviewShareBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nine_gridlayout)
        initView()
    }


    private fun initView() {
        mDataList = ArrayList()
        mAdapter = PreviewShareAdapter(this)
        mAdapter.setOnClickListener(object :PreviewShareAdapter.OnClickListener{
            override fun onItemClick(position: Int) {
                toast("item 点击 $position")
            }

            override fun onGoodsImgClick(itemPosition: Int, goodsPosition: Int) {
                toast("图片点击-- $itemPosition --- $goodsPosition")
            }
        })
        rlShareGoodsList.layoutManager = LinearLayoutManager(this)
        rlShareGoodsList.adapter =mAdapter

        createTestData()
    }

    /**
     * 測試數據
     */
    private fun createTestData() {
        for (i in 0..8) {
            val itemList = ArrayList<String>()
            //从一到9生成9条朋友圈内容，分别是1~9张图片
            for (j in 0..i) {
                itemList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546330550475&di=0d7a9d21b9de80226f1f20544488c246&imgtype=0&src=http%3A%2F%2Flife.southmoney.com%2Ftuwen%2FUploadFiles_6871%2F201808%2F20180830155154469.jpg")
            }
            val previewShareBean = PreviewShareBean("hello shadow 商品名称$i",itemList)
            mDataList.add(previewShareBean)
        }
        mAdapter.update(mDataList, true)
    }
}
