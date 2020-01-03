package com.tunyin.ui.adapter.index

import android.annotation.SuppressLint
import android.view.View
import com.tunyin.R
import com.tunyin.base.BaseAdapter
import com.tunyin.base.BaseViewHolder
import com.tunyin.mvp.model.index.PlayerCommentEntity
import com.tunyin.utils.ImageUtil
import kotlinx.android.synthetic.main.item_player_comment.view.*

class PlayerCommentAdapter : BaseAdapter<PlayerCommentEntity.ListBean>() {

    var listener: PraiseClickListener? = null
    override fun layoutId(): Int {
        return R.layout.item_player_comment
    }

    override fun getViewHolder(view: View): BaseViewHolder<*> {
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<PlayerCommentEntity.ListBean>(itemView) {

        @SuppressLint("NewApi")
        override fun bindData(itemBean: PlayerCommentEntity.ListBean) {
            ImageUtil.load(itemBean.headUrl).isCircle.on(itemView.iv_avatar)
            itemView.tv_content.text = itemBean.content
            itemView.tv_like_num.text = itemBean.praiseNum
            itemView.tv_name.text = itemBean.nickName
            itemView.tv_grade.text = itemBean.userGrade
            itemView.tv_date.text = itemBean.createDate
            if (itemBean.isPraise == "1") {//已经点赞
                itemView.iv_like.setImageDrawable(itemView.context.getDrawable(R.mipmap.ic_zan3))
            }else{
                itemView.iv_like.setImageDrawable(itemView.context.getDrawable(R.mipmap.ic_zan))
            }
            itemView.iv_like.setOnClickListener { listener?.onPraiseClick(position,itemBean.id,itemBean.isPraise)}

        }
    }

    interface PraiseClickListener {
        fun onPraiseClick(position:Int,id: String, type: String)
    }


}