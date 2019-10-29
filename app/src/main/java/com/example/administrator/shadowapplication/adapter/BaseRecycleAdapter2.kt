package com.example.administrator.shadowapplication.adapter

import android.util.Log
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.shadowapplication.R

/**
 * @author 付影影
 * @desc
 * @date 2019/10/23
 */
class BaseRecycleAdapter2
/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 * @param data A new list is created out of this one to avoid mutable list
 */
(data: List<ShadowMultiItem>) : BaseMultiItemQuickAdapter<ShadowMultiItem, BaseViewHolder>(data) {

    init {
        addItemType(ShadowMultiItem.MULTI_TYPE_ONE, R.layout.item_base_recycle_adapter1)
        addItemType(ShadowMultiItem.MULTI_TYPE_TWO, R.layout.item_base_recycle_adapter2)
        addItemType(ShadowMultiItem.MULTI_TYPE_THREE, R.layout.item_base_recycle_adapter3)
        addItemType(ShadowMultiItem.MULTI_TYPE_FOUR, R.layout.item_base_recycle_adapter4)
    }

    override fun convert(helper: BaseViewHolder, item: ShadowMultiItem) {
        when (item.itemType) {
            ShadowMultiItem.MULTI_TYPE_ONE -> {
                Log.d("hh","MULTI_TYPE_ONE")
                bindData(helper,item)
            }
            ShadowMultiItem.MULTI_TYPE_TWO -> {
                Log.d("hh","MULTI_TYPE_TWO")
                bindData(helper,item)
            }
            ShadowMultiItem.MULTI_TYPE_THREE -> {
                Log.d("hh","MULTI_TYPE_THREE")
                bindData(helper,item)
            }
            ShadowMultiItem.MULTI_TYPE_FOUR -> {
                Log.d("hh","MULTI_TYPE_FOUR")
                bindData(helper,item)
            }
        }

    }

    private fun bindData(helper: BaseViewHolder, item: ShadowMultiItem) {
        helper.setText(R.id.name, item.shadow.name)
                .setText(R.id.age, item.shadow.age)

    }
}
