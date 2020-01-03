package com.tunyin.ui.adapter.index

import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.adapter.discovery.StateBroadcast
import com.tunyin.widget.section.ViewHolder

class GuessLikeSelection(list: List<IndexEntity.GuessLikeBean>?) : StateBroadcast<IndexEntity.GuessLikeBean>(R.layout.layout_item_section_head,
        R.layout.item_index_guess_like, list) {

    override fun convert(holder: ViewHolder, zhuanlan: IndexEntity.GuessLikeBean, position: Int) {
        with(holder) {
            zhuanlan.let {
                 var guessLikeItemAdapter = GuessLikeItemAdapter()
                guessLikeItemAdapter.dataList=zhuanlan.list
                getView<RecyclerView>(R.id.recycler).layoutManager= GridLayoutManager(mContext, 3)
                getView<RecyclerView>(R.id.recycler).adapter=guessLikeItemAdapter


//                itemView.setOnClickListener {
//                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
//                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "猜你喜欢")?.setVisible(R.id.headMore, true)
        holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
            if (refreshGuessLikeListener!=null){
                refreshGuessLikeListener.refreshGuessLikeData()
            }
//            ToastUtils.showToast("我是换一批")
        }
    }

    fun setRefreshGuessLikeListener(listener: OnRefreshGuessLikeListener) {
        this.refreshGuessLikeListener = listener

    }

    private lateinit var refreshGuessLikeListener: OnRefreshGuessLikeListener


    interface OnRefreshGuessLikeListener {
        fun refreshGuessLikeData()
    }
}