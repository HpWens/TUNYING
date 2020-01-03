package com.tunyin.ui.adapter.mine

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.mine.CollectEntity
import com.tunyin.utils.ImageUtil
import kotlinx.android.synthetic.main.item_collect.view.*

/**
 * create by wangrongchao
 * on 2019/11/2
 *
 **/
class CollectAdapter : BaseAdapter<CollectEntity.ListBean>() {
    override fun layoutId(): Int {
        return R.layout.item_collect
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<CollectEntity.ListBean>(itemView) {

        override fun bindData(itemBean: CollectEntity.ListBean) {
            itemView.tv_title.text = itemBean.title
            itemView.tv_sub_title.text = itemBean.content
            itemView.tv_price.text = itemBean.price
            ImageUtil.load(itemBean.image).on(itemView.image)

        }
    }
}