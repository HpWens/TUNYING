package com.tunyin.ui.adapter.index

import android.graphics.Rect
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.adapter.discovery.StateBroadcast
import com.tunyin.widget.section.ViewHolder
import com.vondear.rxtool.RxImageTool

class FreeSelection(list: List<IndexEntity.FreeListBean>?) : StateBroadcast<IndexEntity.FreeListBean>(R.layout.layout_item_section_head,
        R.layout.item_broadcat_recycleview, list) {

    override fun convert(holder: ViewHolder, zhuanlan: IndexEntity.FreeListBean, position: Int) {
        with(holder) {
            zhuanlan.let {
                var freeItemAdapter = FreeItemAdapter()
                freeItemAdapter.dataList = zhuanlan.list
                getView<RecyclerView>(R.id.recycler).layoutManager = GridLayoutManager(mContext, 2)
                getView<RecyclerView>(R.id.recycler).adapter = freeItemAdapter
                for (i in 0 until getView<RecyclerView>(R.id.recycler).itemDecorationCount) {
                    getView<RecyclerView>(R.id.recycler).removeItemDecorationAt(i)
                }
                getView<RecyclerView>(R.id.recycler).addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        var pos = parent.getChildAdapterPosition(view)
                        if (pos % 2 == 0) {
                            outRect.left = RxImageTool.dip2px(10f)
                            outRect.right = RxImageTool.dip2px(4f)
                        } else {
                            outRect.right = RxImageTool.dip2px(10f)
                            outRect.left = RxImageTool.dip2px(4f)
                        }
                        if (pos / 2 == 0) {
                            outRect.top = 0
                        } else {
                            outRect.top = RxImageTool.dip2px(20f)
                        }
                    }
                })
                itemView.setOnClickListener {
                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "免费版")?.setVisible(R.id.headMore, true)
        holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
            if (refreshFreeListener != null) {
                refreshFreeListener.refreshFreeData()
            }
//            ToastUtils.showToast("我是换一批")
        }

    }


    fun setRefreshFreeListener(listener: OnRefreshFreeListener) {
        this.refreshFreeListener = listener

    }

    private lateinit var refreshFreeListener: OnRefreshFreeListener


    interface OnRefreshFreeListener {
        fun refreshFreeData()
    }
}