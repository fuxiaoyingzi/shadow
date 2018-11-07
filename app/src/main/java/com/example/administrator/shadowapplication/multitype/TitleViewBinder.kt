package com.example.administrator.shadowapplication.multitype

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.layout_title.view.*

import me.drakeet.multitype.ItemViewBinder

/**
 * Author : shadow
 * Desc :
 * Date :2018/6/1/001
 */

class TitleViewBinder : ItemViewBinder<Title, TitleViewBinder.TitleHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): TitleHolder {
        val view = inflater.inflate(R.layout.layout_title, parent, false)
        return TitleHolder(view)
    }

    override fun onBindViewHolder(holder: TitleHolder, item: Title) {
        holder.itemView.title.text = item.title
        holder.itemView.url.text = item.url

    }

    inner class TitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
