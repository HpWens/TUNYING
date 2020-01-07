package com.tunyin.ui.adapter.index

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.index.PayStuffEntity
import com.tunyin.utils.ImageUtil
import kotlinx.android.synthetic.main.item_pay_stuff_ac.view.*

/**
 * create by wangrongchao
 * on 2019/11/10
 *
 **/
class PayStuffForAcAdapter : BaseAdapter<PayStuffEntity.ListBean>() {

    var loadMore = true

    override fun layoutId(): Int {
        return R.layout.item_pay_stuff_ac
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<PayStuffEntity.ListBean>(itemView) {

        override fun bindData(itemBean: PayStuffEntity.ListBean) {
            ImageUtil.load(itemBean.image).on(itemView.image)
            itemView.tv_title.text = itemBean.title
            itemView.tv_sub_title.text = itemBean.content
            itemView.tv_diamond_num.text = itemBean.price
        }

    }
}