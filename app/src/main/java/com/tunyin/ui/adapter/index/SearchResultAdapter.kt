package com.tunyin.ui.adapter.index

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.utils.ImageUtil
import kotlinx.android.synthetic.main.item_search_result.view.*

/**
 * create by wangrongchao
 * on 2019/11/2
 *
 **/
class SearchResultAdapter : BaseAdapter<SearchEntity.ListBean>() {
    override fun layoutId(): Int {
        return R.layout.item_search_result
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<SearchEntity.ListBean>(itemView) {

        override fun bindData(itemBean: SearchEntity.ListBean) {
            itemView.tv_title.text = itemBean.title
            itemView.tv_sub_title.text = itemBean.content
            itemView.tv_price.text = itemBean.price ?: "0"
            ImageUtil.load(itemBean.image).on(itemView.image)
        }
    }
}