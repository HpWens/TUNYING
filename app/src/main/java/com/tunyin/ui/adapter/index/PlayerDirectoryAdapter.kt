package com.tunyin.ui.adapter.index

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.index.PlayerDirectoryEntity
import kotlinx.android.synthetic.main.item_player_directory.view.*

class PlayerDirectoryAdapter constructor(currentPos: String) : BaseAdapter<PlayerDirectoryEntity.ListBean>() {

    private var mCurrentPos: String = "-1"

    init {
        mCurrentPos = currentPos
    }

    open fun setCurrentPos(currentPos: String) {
        mCurrentPos = currentPos

    }

    override fun layoutId(): Int {
        return R.layout.item_player_directory
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<PlayerDirectoryEntity.ListBean>(itemView) {

        override fun bindData(itemBean: PlayerDirectoryEntity.ListBean) {

            itemView.tv_serial_num.text = String.format("第%s集", position + 1)
            itemView.tv_duration.text = itemBean.durationDict
            itemView.tv_title.text = itemBean.title
            if ((mCurrentPos.toInt() - 1).toString() == position.toString()) {
                itemView.tv_serial_num.setTextColor(itemView.context.resources.getColor(R.color.color_F85252))
                itemView.tv_title.setTextColor(itemView.context.resources.getColor(R.color.color_F85252))
            } else {
                itemView.tv_serial_num.setTextColor(itemView.context.resources.getColor(R.color.black))
                itemView.tv_title.setTextColor(itemView.context.resources.getColor(R.color.black))
            }


        }
    }
}