package com.tunyin.ui.adapter.index

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.index.SearchHistoryEntity
import kotlinx.android.synthetic.main.item_search_history.view.*

/**
 * create by wangrongchao
 * on 2019/10/31
 *
 **/
class SearchHistoryAdapter : BaseAdapter<SearchHistoryEntity.ListBean>() {
    var listener: DeleteSearchListener? = null
    override fun layoutId(): Int {
        return R.layout.item_search_history
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<SearchHistoryEntity.ListBean>(itemView) {

        override fun bindData(itemBean: SearchHistoryEntity.ListBean) {
            itemView.tv_content.text = itemBean.title
            itemView.iv_delete.setOnClickListener { listener?.onDeleteSearchClick(position) }
        }
    }

    interface DeleteSearchListener {
        fun onDeleteSearchClick(position:Int)
    }
}