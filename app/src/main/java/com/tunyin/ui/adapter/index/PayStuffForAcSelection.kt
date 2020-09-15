package com.tunyin.ui.adapter.index

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.mvp.model.index.PayStuffEntity
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.ui.adapter.discovery.StateBroadcast
import com.tunyin.widget.section.ViewHolder

class PayStuffForAcSelection(list: List<PayStuffEntity>?) : StateBroadcast<PayStuffEntity>(R.layout.layout_empty,
        R.layout.item_index_guess_like, list) {

    override fun convert(holder: ViewHolder, zhuanlan: PayStuffEntity, position: Int) {
        with(holder) {
            zhuanlan.let {
                var payStuffForAcAdapter = PayStuffForAcAdapter()
                payStuffForAcAdapter.dataList = zhuanlan.list
                getView<RecyclerView>(R.id.recycler).layoutManager = GridLayoutManager(mContext, 2)
                getView<RecyclerView>(R.id.recycler).adapter = payStuffForAcAdapter
                payStuffForAcAdapter.setOnItemClickListener { v, position ->
                    var id = zhuanlan.list[position].id
                    itemView.context.startActivity(PlayerActivity.newInstance(itemView.context, id))
                }

//                 var guessLikeItemAdapter = GuessLikeItemAdapter()
//                guessLikeItemAdapter.dataList=zhuanlan.list
//                getView<RecyclerView>(R.id.recycler).layoutManager= GridLayoutManager(mContext, 3)
//                getView<RecyclerView>(R.id.recycler).adapter=guessLikeItemAdapter


//                itemView.setOnClickListener {
//                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
//                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        // holder?.setText(R.id.headTitle, "")?.setVisible(R.id.headMore, false)
        // holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
        //            if (refreshGuessLikeListener != null) {
//                refreshGuessLikeListener.refreshGuessLikeData()
//            }
//            ToastUtils.showToast("我是换一批")
    }
}
