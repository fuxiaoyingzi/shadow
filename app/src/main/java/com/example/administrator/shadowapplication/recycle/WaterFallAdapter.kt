package com.example.administrator.shadowapplication.recycle

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.layout_staggered_item.view.*
import java.util.*

/**
 * Author : shadow
 * Desc : 瀑布流
 * Date :2018/6/15/015
 */

class WaterFallAdapter(private val mContext: Context) : RecyclerView.Adapter<WaterFallAdapter.StaggeredHolder>() {
    private val imageUrl: MutableList<String>?

    private var mHeights: MutableList<Int>? = null

    init {
        imageUrl = ArrayList()
    }

    fun getRandomHeight(mList: List<String>) {
        imageUrl!!.addAll(mList)
        mHeights = ArrayList()
        for (i in mList.indices) {
            //随机的获取一个范围为200-600直接的高度
            mHeights!!.add((200 + Math.random() * 400).toInt())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggeredHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_staggered_item, parent, false)
        return StaggeredHolder(view)
    }

    override fun onBindViewHolder(holder: StaggeredHolder, position: Int) {
        //为我们的item设置个随机的高度
        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = mHeights!![position]
        holder.itemView.layoutParams = layoutParams
        Glide.with(mContext)
                .load("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
                .into(holder.itemView.ivStaggeredImage)

    }

    override fun getItemCount(): Int {
        return imageUrl?.size ?: 0
    }

    inner class StaggeredHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
