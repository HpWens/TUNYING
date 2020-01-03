package com.tunyin.ui.adapter.mine

import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.mine.MyWalletEntity
import com.tunyin.utils.AppUtils
import kotlinx.android.synthetic.main.item_deposit.view.*


class DepositAdapter : BaseAdapter<MyWalletEntity.RechargeListBean>() {
    private var defItem = -1//默认值
    override fun layoutId(): Int {
        return R.layout.item_deposit
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return Holder(view)
    }

    //获取点击的位置
    fun setDefSelect(position: Int) {
        this.defItem = position
        notifyDataSetChanged()
    }


    inner class Holder(itemView: View) : BaseViewHolder<MyWalletEntity.RechargeListBean>(itemView) {

        override fun bindData(bean: MyWalletEntity.RechargeListBean) {
            itemView.tv_price.text = String.format("¥%s", bean.money)
            itemView.tv_diamond.text = String.format("%s钻石", bean.note)
            if (defItem == position) {
                itemView.tv_price.setTextColor(AppUtils.getColor(R.color.white))
                itemView.tv_diamond.setTextColor(AppUtils.getColor(R.color.white))
                itemView.ly_contain.setBackgroundResource(R.drawable.shape_gradient_blue_corners)

            } else {
                itemView.tv_price.setTextColor(AppUtils.getColor(R.color.black))
                itemView.tv_diamond.setTextColor(AppUtils.getColor(R.color.color_67D0F0))
                itemView.ly_contain.setBackgroundResource(R.drawable.shape_bg_frame_gray_oval_3dp)
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