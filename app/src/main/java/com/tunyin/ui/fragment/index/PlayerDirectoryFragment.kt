package com.tunyin.ui.fragment.index

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.R
import com.tunyin.base.BaseRefreshFragment
import com.tunyin.mvp.contract.index.PlayerDirectoryContract
import com.tunyin.mvp.model.index.PlayerDirectoryEntity
import com.tunyin.mvp.presenter.index.PlayerDirectoryPresenter
import com.tunyin.ui.adapter.index.PlayerDirectoryAdapter

/**
 * 播放——目录
 */
class PlayerDirectoryFragment : BaseRefreshFragment<PlayerDirectoryPresenter, PlayerDirectoryEntity>(), PlayerDirectoryContract.View {

    private var mAdapter: PlayerDirectoryAdapter? = null
    private var mMusicId: String? = null

    override fun initPresenter() = mPresenter.attachView(this)

    override fun initInject() = fragmentComponent.inject(this)

//    override fun lazyLoadData() = mPresenter.getDirectory(mMusicId.toString())


    override fun showDirectory(playerDirectoryEntity: PlayerDirectoryEntity) {
//        mAdapter?.dataList = playerDirectoryEntity.list
//        mAdapter?.notifyDataSetChanged()

        hideLoading()
        mAdapter = PlayerDirectoryAdapter(playerDirectoryEntity.currentEpisode)
        mRecycler?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mAdapter?.dataList = playerDirectoryEntity.list
        mRecycler?.adapter = mAdapter
        mAdapter?.setOnItemClickListener { v, position ->
            if (onChangeMusicListener != null) {
                mAdapter!!.setCurrentPos((position + 1).toString())
                mAdapter!!.notifyDataSetChanged()
                onChangeMusicListener.changeMusic(mAdapter!!.dataList[position].id.toString())
            }

        }

    }

    override fun showError(msg: String) {
        hideLoading()
    }

    override fun initDatas() {
        mMusicId = arguments?.getString("mMusicId")
        showLoading()
        mPresenter.getDirectory(mMusicId.toString())
    }

    override fun initRecyclerView() {
//        mAdapter = PlayerDirectoryAdapter()
//        mRecycler?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        mRecycler?.adapter = mAdapter

    }

    override fun getLayoutId(): Int = R.layout.fragment_player_directory

    companion object {

        fun newInstance(mMusicId: String): PlayerDirectoryFragment {
            val fragment = PlayerDirectoryFragment()
            val args = Bundle()
            args.putString("mMusicId", mMusicId)
            fragment.arguments = args
            return fragment
        }
    }

    fun setChangeMusicListener(listener: OnChangeMusicListener) {
        this.onChangeMusicListener = listener

    }

    private lateinit var onChangeMusicListener: OnChangeMusicListener


    interface OnChangeMusicListener {
        fun changeMusic(id: String)
    }


}