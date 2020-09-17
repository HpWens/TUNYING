package com.tunyin.ui.adapter.discovery

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.ui.activity.index.PayStuffActivity
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.widget.section.ViewHolder


class UnmannedSoundSelection(list: List<DiscoveryEntity.UnmannedSoundBean>?) : StateBroadcast<DiscoveryEntity.UnmannedSoundBean>(R.layout.layout_item_section_head, R.layout.item_broadcat_recycleview2, list) {

    private var mRecyclerViewLayoutManager: LinearLayoutManager? = null
    override fun convert(holder: ViewHolder, zhuanlan: DiscoveryEntity.UnmannedSoundBean, position: Int) {
        with(holder) {
            zhuanlan.let {
                mRecyclerViewLayoutManager = LinearLayoutManager(mContext)
                mRecyclerViewLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
                var unmannedSoundItemAdapter = UnmannedSoundItemAdapter()
                unmannedSoundItemAdapter.dataList = zhuanlan.list
                getView<RecyclerView>(R.id.recycler).layoutManager = mRecyclerViewLayoutManager
                getView<RecyclerView>(R.id.recycler).adapter = unmannedSoundItemAdapter

                unmannedSoundItemAdapter.setOnItemClickListener { v, position ->
                    var id = zhuanlan.list[position].id
                    itemView.context.startActivity(PlayerActivity.newInstance(itemView.context, id))

                }
//                itemView.setOnClickListener {
//                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
//                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "无人声")?.setVisible(R.id.headMore, false)?.setVisible(R.id.iv_more, true)
        holder?.itemView?.setOnClickListener {
            it.context.startActivity(PayStuffActivity.newInstance(it.context, "6", "无人音"))
        }
    }
}