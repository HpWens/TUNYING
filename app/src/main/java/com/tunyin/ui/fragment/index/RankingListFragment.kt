package com.tunyin.ui.fragment.index

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.BaseFragment
import com.tunyin.R
import com.tunyin.base.BaseRefreshFragment
import com.tunyin.mvp.contract.index.RankListContract
import com.tunyin.mvp.model.index.RankListEntity
import com.tunyin.mvp.presenter.index.RankListPresenter
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.ui.adapter.index.RankListAdapter

/**
 * create by wangrongchao
 * on 2019/11/7
 *
 **/
class RankingListFragment : BaseRefreshFragment<RankListPresenter, RankListEntity>(), RankListContract.View {

    private var mAdapter: RankListAdapter? = null
    private var mType: String = ""
    val mRankListList = ArrayList<RankListEntity.ListBean>()

    override fun initPresenter() = mPresenter.attachView(this)

    override fun initInject() = fragmentComponent.inject(this)
//    override fun lazyLoadData() =
//            mPresenter.getRankList("0", "10", "1")


    override fun initRecyclerView() {
//        mAdapter = RankListAdapter()
//        mRecycler?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        mAdapter?.dataList = mRankListList
//        mRecycler?.adapter = mAdapter
    }

    override fun showRankListData(rankListEntity: RankListEntity) {
        mAdapter = RankListAdapter()
        mRecycler?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRankListList.addAll(rankListEntity.list)
        mAdapter?.dataList = mRankListList
        mRecycler?.adapter = mAdapter
        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener { v, position ->
            mContext?.startActivity(PlayerActivity.newInstance(mContext!!, mAdapter!!.dataList[position].id))
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_rank_list
    }

    override fun initDatas() {
        mType = arguments!!.getString("type")
        mPresenter.getRankList("0", "10", mType)

    }

    companion object {

        fun newInstance(type: String): RankingListFragment {
            val fragment = RankingListFragment()
            val args = Bundle()
            args.putString("type", type)
            fragment.arguments = args
            return fragment
        }
    }
}