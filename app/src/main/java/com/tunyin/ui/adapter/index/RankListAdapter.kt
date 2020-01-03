package com.tunyin.ui.adapter.index

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseMutableAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.index.RankListEntity
import com.tunyin.utils.ImageUtil
import kotlinx.android.synthetic.main.item_ranking_list.view.*
import kotlinx.android.synthetic.main.item_ranking_top.view.*

class RankListAdapter : BaseMutableAdapter<RankListEntity.ListBean>() {
    val TOP = 1
    val NORMAL = 2
    override fun layoutId(viewType: Int): Int {

        if (viewType == TOP) {
            return R.layout.item_ranking_top
        } else {
            return R.layout.item_ranking_list
        }
    }

    override fun getViewHolder(view: View, viewType: Int): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TOP
        } else {
            return NORMAL
        }

    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<RankListEntity.ListBean>(itemView) {

        override fun bindData(itemBean: RankListEntity.ListBean) {
            if (position == 0) {
                ImageUtil.load(itemBean.image).on(itemView.image_top)
                itemView.tv_title_top.text = itemBean.title
                itemView.tv_sub_title_top.text = itemBean.content
                itemView.tv_price_top.text = itemBean.price

            } else {
                ImageUtil.load(itemBean.image).on(itemView.image)
                itemView.tv_title.text = itemBean.title
                itemView.tv_sub_title.text = itemBean.content
                itemView.tv_price.text = itemBean.price
                if (position == 1) {
                    itemView.iv_rank_grade.setImageDrawable(itemView.context.resources.getDrawable(R.mipmap.icon_rank_sliver))

                } else if (position == 2) {
                    itemView.iv_rank_grade.setImageDrawable(itemView.context.resources.getDrawable(R.mipmap.icon_rank_broze))
                } else {
                    itemView.iv_rank_grade.setImageDrawable(itemView.context.resources.getDrawable(R.mipmap.icon_rank_normal))
                }
            }


        }
    }
}