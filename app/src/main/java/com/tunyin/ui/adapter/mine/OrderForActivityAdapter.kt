package com.tunyin.ui.adapter.mine

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.mine.OrderEntity
import com.tunyin.utils.ImageUtil
import kotlinx.android.synthetic.main.item_order_for_ac.view.*
import kotlinx.android.synthetic.main.item_order_for_ac.view.image
import kotlinx.android.synthetic.main.item_order_for_ac.view.tv_date
import kotlinx.android.synthetic.main.item_order_for_ac.view.tv_delete
import kotlinx.android.synthetic.main.item_order_for_ac.view.tv_title

/**
 * create by wangrongchao
 * on 2019/11/2
 *
 **/
class OrderForActivityAdapter : BaseAdapter<OrderEntity.ListBean>() {
    private var mDeleteClickListener: OnDeleteClickLister? = null
    override fun layoutId(): Int {
        return R.layout.item_order_for_ac
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<OrderEntity.ListBean>(itemView) {

        override fun bindData(itemBean: OrderEntity.ListBean) {
            itemView.tv_title.text = itemBean.title
            itemView.tv_order_no.text = String.format("订单编号：%s", itemBean.orderNo)
            itemView.tv_price.text = String.format("%s钻石", itemBean.totalPrice)
            ImageUtil.load(itemBean.image).on(itemView.image)
            itemView.tv_date.text = itemBean.createDate
            itemView.tv_delete.setOnClickListener {
                if (mDeleteClickListener != null) {
                    mDeleteClickListener!!.onDeleteClick(position)
                }

            }

        }
    }

    fun setOnDeleteClickListener(listener: OnDeleteClickLister) {
        this.mDeleteClickListener = listener
    }

    interface OnDeleteClickLister {
        fun onDeleteClick(position: Int)
    }
}