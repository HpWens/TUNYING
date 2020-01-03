package com.tunyin.ui.fragment.mine

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.R
import com.tunyin.base.BaseRefreshFragment
import com.tunyin.mvp.contract.mine.VoucherContract
import com.tunyin.mvp.model.mine.VoucherEntity
import com.tunyin.mvp.presenter.mine.VoucherPresenter
import com.tunyin.ui.adapter.mine.VoucherAdapter
import kotlinx.android.synthetic.main.activity_search_result.*

/**
 * create by wangrongchao
 * on 2019/11/10
 *
 **/
class MyVoucherFragment : BaseRefreshFragment<VoucherPresenter, VoucherEntity>(), VoucherContract.View {


    private var mAdapter: VoucherAdapter? = null
    private var type: String? = null

    override fun initPresenter() = mPresenter.attachView(this)

    override fun initInject() = fragmentComponent.inject(this)

    override fun getLayoutId(): Int {
        return R.layout.fragment_voucher
    }

    override fun showVoucherData(voucherEntity: VoucherEntity) {
        mAdapter = VoucherAdapter()
        mAdapter?.dataList = voucherEntity.list
        recycler?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler?.adapter = mAdapter
    }

    override fun initWidget() {
        type = arguments?.getString("type")
        type?.let { mPresenter.getVoucher("0", "10", it) }

    }

    companion object {
        fun newInstance(type: String): MyVoucherFragment {
            val fragment = MyVoucherFragment()
            val args = Bundle()
            args.putString("type", type)
            fragment.arguments = args
            return fragment
        }
    }
}
