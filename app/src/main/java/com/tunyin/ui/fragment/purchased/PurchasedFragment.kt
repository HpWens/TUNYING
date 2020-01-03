package com.tunyin.ui.fragment.purchased

import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun lazyLoadData() = mPresenter.getOrderList("0", "10")


    override fun showOrderList(orderEntity: OrderEntity) {
        mAdapter = OrderAdapter()
        mAdapter?.dataList = orderEntity.list
        recycler?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler?.adapter = mAdapter
        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener { v, position ->
            startActivity(OrderDetailActivity.newInstance(mContext, mAdapter!!.dataList[position].id,
                    mAdapter!!.dataList[position].title,
                    mAdapter!!.dataList[position].image,
                    mAdapter!!.dataList[position].orderNo,
                    mAdapter!!.dataList[position].createDateFormat,
                    mAdapter!!.dataList[position].totalPrice,
                    mAdapter!!.dataList[position].songId
            ))
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
        mPresenter.getOrderList("0", "10")
    }

    companion object {
        fun newInstance(): PurchasedFragment {
            return PurchasedFragment()
        }
    }
}