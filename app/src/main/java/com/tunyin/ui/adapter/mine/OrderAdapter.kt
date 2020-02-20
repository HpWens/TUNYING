package com.tunyin.ui.adapter.mine

import android.view.View
import com.tunyin.LogUtils
import com.tunyin.MyAudioPlayer
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.mine.OrderEntity
import com.tunyin.utils.ImageUtil
import kotlinx.android.synthetic.main.item_order.view.*

/**
 * create by wangrongchao
 * on 2019/11/2
 *
 **/
class OrderAdapter : BaseAdapter<OrderEntity.ListBean>() {

    private var mDeleteClickListener: OnDeleteClickLister? = null
    override fun layoutId(): Int {
        LogUtils.d("wangrognchao----order--", position.toString())
        return R.layout.item_order
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<OrderEntity.ListBean>(itemView) {

        override fun bindData(itemBean: OrderEntity.ListBean) {
            itemView.tv_title.text = itemBean.title
//            itemView.tv_progress.text = itemBean.content
            ImageUtil.load(itemBean.image).on(itemView.image)
            itemView.tv_date.text = itemBean.createDate
            itemBean.isHelperStartEnable = MyAudioPlayer.get().isPlaying && MyAudioPlayer.get().songId.equals(itemBean.songId)
            itemView.tv_progress.text = if (itemBean.isHelperStartEnable) "正在播放" else "未开始"
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