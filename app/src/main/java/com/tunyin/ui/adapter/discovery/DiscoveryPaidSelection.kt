package com.tunyin.ui.adapter.discovery

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.tunyin.R
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.utils.ImageUtil
import com.tunyin.widget.section.ViewHolder

class DiscoveryPaidSelection(list: List<DiscoveryEntity.PaidSelectionBean.ListBeanXXXX>?) :
        StateDiscovery<DiscoveryEntity.PaidSelectionBean.ListBeanXXXX>(R.layout.layout_item_section_head,
                R.layout.item_pay_stuff, list) {

    override fun convert(holder: ViewHolder, zhuanlan: DiscoveryEntity.PaidSelectionBean.ListBeanXXXX, position: Int) {
        with(holder) {
            zhuanlan.let { it ->
                //                if (position != 0) getView<ConstraintLayout>(R.id.item).setBackgroundResource(R.drawable.ic_bottom_lines)

                setText(R.id.tv_title, it.title)
                setText(R.id.tv_sub_title, it.content)
                setText(R.id.tv_diamond_num, it.member_price)
//                setText(R.id.special_content, it.introduction)
                ImageUtil.load(it.image).on(getView(R.id.iv_bg))
                var id=it.id

                itemView.setOnClickListener {
                    itemView.context.startActivity(PlayerActivity.newInstance(itemView.context,id))
                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "付费精选")?.setVisible(R.id.headMore, true)
        holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
            if (refreshPaidListener!=null){
                refreshPaidListener.refreshPaidData()
            }
//            ToastUtils.showToast("我是换一批")
        }
    }

    fun setRefreshPaidListener(listener: OnRefreshPaidListener) {
        this.refreshPaidListener = listener

    }

    private lateinit var refreshPaidListener: OnRefreshPaidListener


    interface OnRefreshPaidListener {
        fun refreshPaidData()
    }
}