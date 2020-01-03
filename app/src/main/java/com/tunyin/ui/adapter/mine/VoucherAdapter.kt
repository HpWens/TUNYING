package com.tunyin.ui.adapter.mine

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.mine.VoucherEntity
import kotlinx.android.synthetic.main.item_my_voucher.view.*

/**
 * create by wangrongchao
 * on 2019/11/2
 *
 **/
class VoucherAdapter : BaseAdapter<VoucherEntity.ListBean>() {
    override fun layoutId(): Int {
        return R.layout.item_my_voucher
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<VoucherEntity.ListBean>(itemView) {

        override fun bindData(itemBean: VoucherEntity.ListBean) {
            itemView.tv_price.text = itemBean.money
            itemView.tv_price_range.text = String.format("满%s元可用", itemBean.valve)
            itemView.tv_date.text = itemBean.startTime + "-" + itemBean.endTime
            when (itemBean.isUse) {
                "1", "2" -> {
                    itemView.ly_price.setBackgroundResource(R.mipmap.icon_voucher_blue_tip)
                }
                "3" -> {
                    itemView.ly_price.setBackgroundResource(R.mipmap.icon_voucher_gray_tip)
                }

            }
            when (itemBean.type) {
                "new" -> {
                    itemView.iv_state.visibility = View.VISIBLE
                    itemView.iv_state.setImageDrawable(itemView.context.resources.getDrawable(R.mipmap.icon_voucher_type_new))

                }
                "expireSoon" -> {
                    itemView.iv_state.visibility = View.VISIBLE
                    itemView.iv_state.setImageDrawable(itemView.context.resources.getDrawable(R.mipmap.icon_voucher_type_going_off))
                }
                "Invalid" -> {
                    itemView.iv_state.visibility = View.VISIBLE
                    itemView.iv_state.setImageDrawable(itemView.context.resources.getDrawable(R.mipmap.icon_voucher_tyupe_dead))
                }
            }


        }
    }
}