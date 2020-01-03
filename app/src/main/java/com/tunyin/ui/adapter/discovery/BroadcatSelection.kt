package com.tunyin.ui.adapter.discovery

import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.widget.section.ViewHolder

class BroadcatSelection(list: List<DiscoveryEntity.BroadcastBean>?) : StateBroadcast<DiscoveryEntity.BroadcastBean>(R.layout.layout_item_section_head,
        R.layout.item_broadcat_recycleview, list) {

    override fun convert(holder: ViewHolder, zhuanlan: DiscoveryEntity.BroadcastBean, position: Int) {
        with(holder) {
            zhuanlan.let {
                 var broadcastItemAdapter = BroadcastItemAdapter()
                broadcastItemAdapter.dataList=zhuanlan.list
                getView<RecyclerView>(R.id.recycler).layoutManager= GridLayoutManager(mContext, 3)
                getView<RecyclerView>(R.id.recycler).adapter=broadcastItemAdapter

//                itemView.setOnClickListener {
//                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
//                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "广播剧")?.setVisible(R.id.headMore, true)
        holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
            if (refreshBroadcatListener!=null){
                refreshBroadcatListener.refreshBroadcatData()
            }
//            ToastUtils.showToast("我是换一批")
        }
    }
    fun setRefreshBroadcatListener(listener: OnRefreshBroadcatListener) {
        this.refreshBroadcatListener = listener

    }

    private lateinit var refreshBroadcatListener: OnRefreshBroadcatListener


    interface OnRefreshBroadcatListener {
        fun refreshBroadcatData()
    }
}