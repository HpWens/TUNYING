package com.tunyin.ui.adapter.mine

import android.graphics.Color
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
class CollectAdapter constructor(listener: OnCollectListener) : BaseAdapter<CollectEntity.ListBean>() {

    var mListener: OnCollectListener = listener

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

            if (itemBean.isHelperCollect) {
                itemView.tv_cancel_collect.text = "取消收藏"
                itemView.tv_cancel_collect.setTextColor(Color.parseColor("#666666"))
                itemView.tv_cancel_collect.setBackgroundResource(R.drawable.shape_bg_gray_oval2)
            } else {
                itemView.tv_cancel_collect.text = "收藏"
                itemView.tv_cancel_collect.setTextColor(Color.parseColor("#FFFFFF"))
                itemView.tv_cancel_collect.setBackgroundResource(R.drawable.shape_bg_gray_oval)
            }
            itemView.tv_cancel_collect.setOnClickListener {
                if (itemBean.isHelperCollect) {
                    mListener.onCancelCollect(adapterPosition, itemBean.id)
                } else {
                    mListener.onAddCollect(adapterPosition, itemBean.id)
                }
            }
        }
    }

    interface OnCollectListener {
        fun onCancelCollect(pos: Int, songId: String)
        fun onAddCollect(pos: Int, songId: String)
    }
}