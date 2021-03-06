package com.tunyin.ui.fragment.purchased

import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.MyAudioPlayer
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseRefreshFragment
import com.tunyin.mvp.contract.mine.OrderContract
import com.tunyin.mvp.model.mine.OrderEntity
import com.tunyin.mvp.presenter.mine.OrderPresenter
import com.tunyin.ui.activity.mine.OrderDetailActivity
import com.tunyin.ui.adapter.mine.OrderAdapter
import kotlinx.android.synthetic.main.fragment_pruchased.*

/**
 * 已购
 */
class PurchasedFragment : BaseRefreshFragment<OrderPresenter, OrderEntity>(), OrderContract.View {

    private var mAdapter: OrderAdapter? = null

    override fun initPresenter() = mPresenter.attachView(this)

    override fun initInject() = fragmentComponent.inject(this)

    override fun lazyLoadData() = mPresenter.getOrderList("0", "200")

    override fun showOrderList(orderEntity: OrderEntity) {
        mAdapter = OrderAdapter()
        mAdapter?.dataList = orderEntity.list
        recycler?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler?.adapter = mAdapter
        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener { v, position ->
            mAdapter!!.dataList?.let {
                var title = it.get(position)?.title ?: ""
                var image = it.get(position)?.image ?: ""
                var orderNo = it.get(position)?.orderNo ?: ""
                var createDateFormat = it.get(position)?.createDateFormat ?: ""
                var totalPrice = it.get(position)?.totalPrice ?: ""
                var songId = it.get(position)?.songId ?: ""
                startActivity(OrderDetailActivity.newInstance(mContext, mAdapter!!.dataList[position].id,
                        title,
                        image,
                        orderNo,
                        createDateFormat,
                        totalPrice,
                        songId
                ))
            }
        }
        mAdapter!!.setOnDeleteClickListener(object : OrderAdapter.OnDeleteClickLister {
            override fun onDeleteClick(position: Int) {
                showLoading()
                mPresenter.delOrder(mAdapter!!.dataList[position].id)
                mAdapter!!.dataList.removeAt(position)
                recycler.closeMenu()

            }
        })
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (mAdapter != null && mAdapter!!.dataList.size > 0) {
            if (MyAudioPlayer.get().isPlaying) {
                for (index in mAdapter!!.dataList.indices) {
                    if (mAdapter!!.dataList[index].songId.equals(MyAudioPlayer.get().songId)) {
                        // mAdapter!!.dataList[index].isHelperStartEnable = true
                        mAdapter!!.notifyItemChanged(index)
                        break
                    }
                }
            }
        }
    }

    override fun delOrderSuc(string: String) {
        hideLoading()
        ToastUtils.showToast(string)
        mAdapter!!.notifyDataSetChanged()
    }

    override fun showError(msg: String) {
        hideLoading()
    }

    override fun getLayoutId(): Int = R.layout.fragment_pruchased

    override fun initWidget() {
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getOrderList("0", "200")
    }

    companion object {
        fun newInstance(): PurchasedFragment {
            return PurchasedFragment()
        }
    }
}