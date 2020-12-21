package com.tunyin.ui.adapter.index

import android.view.View
import com.tunyin.MyPlayService
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.index.PayStuffEntity
import com.tunyin.utils.ImageUtil
import kotlinx.android.synthetic.main.item_pay_stuff_ac.view.*

/**
 * create by wangrongchao
 * on 2019/11/10
 *
 **/
class PayStuffForAcAdapter(typeId: String) : BaseAdapter<PayStuffEntity.ListBean>() {

    var loadMore = true

    var typeId = "1"

    init {
        this.typeId = typeId
    }

    override fun layoutId(): Int {
        return R.layout.item_pay_stuff_ac
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view, typeId)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    inner class ViewHolder(itemView: View, typeId: String) : BaseViewHolder<PayStuffEntity.ListBean>(itemView) {

        override fun bindData(itemBean: PayStuffEntity.ListBean) {
            var isAnchor = typeId.equals("-1")
            ImageUtil.load(if (isAnchor) itemBean.headUrl else itemBean.image).on(itemView.image)
            itemView.tv_title.text = if (isAnchor) itemBean.name else itemBean.title
            itemView.tv_sub_title.text = if (isAnchor) itemBean.summary else itemBean.content
            itemView.tv_diamond.text = if (isAnchor) "粉丝" else (if (MyPlayService.isFree(itemBean.price)) "免费" else "钻石")
            itemView.tv_diamond_num.text = if (isAnchor) itemBean.fans.toString() else MyPlayService.getPrice(itemBean.price)
        }

    }


}