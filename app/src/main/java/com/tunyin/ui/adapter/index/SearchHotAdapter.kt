package com.tunyin.ui.adapter.index

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.index.SearchHotEntity
import kotlinx.android.synthetic.main.item_search_hot.view.*

/**
 * create by wangrongchao
 * on 2019/10/31
 *
 **/
class SearchHotAdapter : BaseAdapter<SearchHotEntity.ListBean>() {
    override fun layoutId(): Int {
        return R.layout.item_search_hot
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<SearchHotEntity.ListBean>(itemView) {

        override fun bindData(itemBean: SearchHotEntity.ListBean) {
            itemView.tv_title.text = itemBean.title


        }
    }
}