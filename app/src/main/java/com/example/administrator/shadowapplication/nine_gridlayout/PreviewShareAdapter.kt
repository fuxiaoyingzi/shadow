package com.example.administrator.shadowapplication.nine_gridlayout

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.shadowapplication.R
import com.gongchangtemai.myhome.widgets.view.NineGridlayout
import kotlinx.android.synthetic.main.list_item_home_preview_share.view.*


/**
 * Author : shadow
 * Desc : 整场分享预览
 * Date :2019/1/5/005
 */
class PreviewShareAdapter(context: Context) : BaseListAdapter<PreviewShareBean, BaseHolder>(context) {

    //点击事件处理
    private var mOnClickListener: OnClickListener? = null

    interface OnClickListener {
        //点击查看品牌详情
        fun onItemClick(position: Int)

        //查看商品
        fun onGoodsImgClick(itemPosition: Int, goodsPosition: Int)

    }


    fun setOnClickListener(mOnClickListener: OnClickListener) {
        this.mOnClickListener = mOnClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val view: View =  LayoutInflater.from(mContext).inflate(R.layout.list_item_home_preview_share, parent, false)
        return BaseHolder(view)
    }


    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        val previewBean = dataSet[position]
        if (previewBean.goodsUrl.size == 1){
            holder.itemView.ivOneImage.visibility = View.VISIBLE
            holder.itemView.ivMoreImage.visibility = View.GONE
        }else{
            holder.itemView.ivOneImage.visibility = View.GONE
            holder.itemView.ivMoreImage.visibility = View.VISIBLE
        }

        //显示九宫格图片
        if (previewBean.goodsUrl.size == 1){
           holder.itemView.ivOneImage.setImageUrl(previewBean.goodsUrl[0])
            holder.itemView.ivOneImage.setOnClickListener {
                if (mOnClickListener != null){
                    mOnClickListener!!.onGoodsImgClick(position,0)
                }
            }
        }else{
            holder.itemView.ivMoreImage.setImagesData(previewBean.goodsUrl,object :NineGridlayout.ImageClickListener{
                override fun onImageClick(imgPoi: Int) {
                    if (mOnClickListener != null){
                        mOnClickListener!!.onGoodsImgClick(position,imgPoi)
                    }
                }
            })
        }

        //item点击事件
        holder.itemView.setOnClickListener {
            if (mOnClickListener != null) {
                mOnClickListener!!.onItemClick(position)
            }
        }
    }
}
