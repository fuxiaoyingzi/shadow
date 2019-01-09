package com.gongchangtemai.myhome.widgets.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.example.administrator.shadowapplication.widget.DensityUtil
import java.util.*
import android.util.DisplayMetrics
import android.view.WindowManager
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import com.example.administrator.shadowapplication.base.MyApp


/**
 * Author : shadow
 * Desc : 图片九宫格显示
 * Date :2019/1/5/005
 */
class NineGridlayout : ViewGroup {
    /**
     * 图片之间的间隔 图片宽度 85*3 左右间距6*2 上下间距5
     */
    private var leftGap = DensityUtil.dp2px(6f)
    private var topGap = DensityUtil.dp2px(5f)
    private var columns: Int = 0//
    private var rows: Int = 0//
    private var listData: ArrayList<String> = ArrayList()
    private  var listener:ImageClickListener? = null
    lateinit var mcontext:Context;

    interface ImageClickListener{
        fun onImageClick(imgPoi: Int)
    }

    constructor(context: Context) : super(context) {
        this.mcontext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mcontext = context
    }
     var totalWidth:Int = 0
    init {
        totalWidth = MyApp.getInstance().scannWidth - DensityUtil.dp2px(110f)
    }



     override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }

    private fun layoutChildrenView() {
        if (listData.isNotEmpty()){
            val childrenCount = listData.size
            //单个图片的宽度
           // val singleWidth = DensityUtil.dp2px(85f)
            val singleWidth = (totalWidth - leftGap * 2) / 3

            //根据子view数量确定布局高度
            val params = layoutParams
            params.height = singleWidth * rows + topGap * (rows - 1)
            layoutParams = params

            for (i in 0 until childrenCount) {
                val childrenView = getChildAt(i) as NineGridImageView
                childrenView.setImageUrl((listData[i]))
                val position =  findPosition(i)
                val left = (singleWidth + leftGap) * position[1]
                val top = (singleWidth + topGap) * position[0]
                val right = left + singleWidth
                val bottom = top + singleWidth
                childrenView.layout(left, top, right, bottom)
            }
        }
    }

    private fun findPosition(childNum: Int): IntArray {
        val position = IntArray(2)
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (i * columns + j == childNum) {
                    position[0] = i//行
                    position[1] = j//列
                    break
                }
            }
        }
        return position
    }

    /**
     * 设置ImageUrl
     */
    fun setImagesData(lists: List<String>?,listener:ImageClickListener) {
        if (lists == null || lists.isEmpty()) {
            return
        }
        this.listener = listener
        removeAllViews()
        //初始化布局
        generateChildrenLayout(lists.size)
        var i = 0
        while (i < lists.size) {
            val iv = generateImageView(i)
            addView(iv, generateDefaultLayoutParams())
            i++
        }

        listData.clear()
        listData.addAll(lists)
        layoutChildrenView()
    }

    /**
     * 根据图片个数确定行列数量
     * 对应关系如下
     * num	row	column
     * 1	   1	1
     * 2	   1	2
     * 3	   1	3
     * 4	   2	2
     * 5	   2	3
     * 6	   2	3
     * 7	   3	3
     * 8	   3	3
     * 9	   3	3
     *
     * @param length
     */
    private fun generateChildrenLayout(length: Int) {
        if (length <= 3) {
            rows = 1
            columns = length
        } else if (length <= 6) {
            rows = 2
            columns = 3
            if (length == 4) {
                columns = 2
            }
        } else {
            rows = 3
            columns = 3
        }
    }

    /**
     * 生成imageView
     */
    private fun generateImageView(position:Int): NineGridImageView {
        val iv = NineGridImageView(context)
        iv.scaleType = ImageView.ScaleType.CENTER_CROP
        iv.setOnClickListener {
            if (listener != null){
                listener!!.onImageClick(position)
            } }
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"))
        return iv
    }




}