package com.tunyin.ui.adapter.mine

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.mine.TunyinVipEntity
import kotlinx.android.synthetic.main.item_tunyin_vip_deposit.view.*


class TunYinVipDepositAdapter : BaseAdapter<TunyinVipEntity.ListBean>() {
    private var defItem = -1//默认值
    override fun layoutId(): Int {
        return R.layout.item_tunyin_vip_deposit
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return Holder(view)
    }

    //获取点击的位置
    fun setDefSelect(position: Int) {
        this.defItem = position
        notifyDataSetChanged()
    }


    inner class Holder(itemView: View) : BaseViewHolder<TunyinVipEntity.ListBean>(itemView) {

        override fun bindData(bean: TunyinVipEntity.ListBean) {
            itemView.tv_vip_name.text=bean.name
            if (defItem == position) {
                itemView.ly_contain.setBackgroundResource(R.drawable.shape_bg_frame_blue_oval_3dp)
                itemView.iv_select_tip.visibility = View.VISIBLE

            } else {
                itemView.ly_contain.setBackgroundResource(R.drawable.shape_bg_frame_gray_oval_3dp)
                itemView.iv_select_tip.visibility = View.INVISIBLE
            }
//            itemView.tv_bind_date.text=String.format("绑定时间：%s", bean.bindDate)
//            itemView.tv_card_status.text=bean.statusDict
//            itemView.tv_card_tip.text=String.format("卡%s", adapterPosition+1)
//            when((adapterPosition+1)%3){
//                1->{
//                    itemView.color_line.setBackgroundColor(Color.parseColor("#FFD900"))
//                }
//                2->{
//                    itemView.color_line.setBackgroundColor(Color.parseColor("#F9AE3A"))
//                }
//                0->{
//                    itemView.color_line.setBackgroundColor(Color.parseColor("#3EB6FF"))
//                }
//            }
//            if (!TextUtils.isEmpty(bean.cardNo)) {
//                itemView.tv_card_status.setTextColor(itemView.context.resources.getColor(R.color.color_333333))
//                itemView.ly_bind_data.visibility = View.VISIBLE
//                itemView.tv_bind_date.visibility = View.GONE
//                ImageUtil.load(bean.headUrl).isCircle.placeholder(R.mipmap.default_avatar).on(itemView.image)
//                itemView.tv_name.text=bean.name
//                itemView.tv_activation_date.text=String.format("激活时间：%s", bean.activationDate)
//                itemView.tv_card_num.text=String.format("卡号：%s", bean.cardNo)
//
//            }else{
//                itemView.tv_card_status.setTextColor(R.color.color_red)
//                itemView.tv_card_status.setTextColor(itemView.context.resources.getColor(R.color.color_red))
//                itemView.ly_bind_data.visibility = View.GONE
//                itemView.tv_bind_date.visibility = View.VISIBLE
//
//
//            }
        }
    }
}