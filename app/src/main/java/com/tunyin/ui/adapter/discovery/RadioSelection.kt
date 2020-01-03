package com.tunyin.ui.adapter.discovery

import android.widget.TextView
import com.tunyin.R
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.utils.ImageUtil
import com.tunyin.widget.section.ViewHolder

class RadioSelection(list: List<DiscoveryEntity.RadioBean.ListBeanXXXXX>?) :
        StateDiscovery<DiscoveryEntity.RadioBean.ListBeanXXXXX>(R.layout.layout_item_section_head_view,
                R.layout.item_radio, list) {

    override fun convert(holder: ViewHolder, zhuanlan: DiscoveryEntity.RadioBean.ListBeanXXXXX, position: Int) {
        with(holder) {
            zhuanlan.let {
//                if (position != 0) getView<ConstraintLayout>(R.id.item).setBackgroundResource(R.drawable.ic_bottom_lines)

                setText(R.id.tv_title, it.title)
                setText(R.id.tv_sub_title, it.content)
                ImageUtil.load(it.image).on(getView(R.id.image))

                var id=it.id

                itemView.setOnClickListener {
                    itemView.context.startActivity(PlayerActivity.newInstance(itemView.context,id))
                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
                }

            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "电台")?.setVisible(R.id.headMore, true)

        holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
            if (refreshRadioListener!=null){
                refreshRadioListener.refreshRadioData()
            }
//            ToastUtils.showToast("我是换一批")
        }
    }

    fun setRefreshRadioListener(listener: OnRefreshRadioListener) {
        this.refreshRadioListener = listener

    }

    private lateinit var refreshRadioListener: OnRefreshRadioListener


    interface OnRefreshRadioListener {
        fun refreshRadioData()
    }
}