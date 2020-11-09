package com.tunyin.ui.fragment.index

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseRefreshFragment
import com.tunyin.mvp.contract.index.PlayerCommentContract
import com.tunyin.mvp.model.index.AddCommendEntity
import com.tunyin.mvp.model.index.PlayerCommentEntity
import com.tunyin.mvp.presenter.index.PlayerCommentPresenter
import com.tunyin.ui.adapter.index.PlayerCommentAdapter
import kotlinx.android.synthetic.main.fragment_player_comment.*

/**
 * 播放——评论
 */
class PlayerCommentFragment : BaseRefreshFragment<PlayerCommentPresenter, PlayerCommentEntity>(), View.OnClickListener, PlayerCommentContract.View {


    override fun addCommendData(addCommendEntity: AddCommendEntity) {
        hideLoading()
        et_commend.text.clear()
        mPresenter.getComment(mMusicId.toString())
    }


    override fun onClick(p0: View?) {
        when (p0) {
            tv_commend -> {
                if (TextUtils.isEmpty(et_commend.text.toString())) {
                    ToastUtils.showToast("请输入评论内容")
                    return
                }
                showLoading()
                hideKeyboard(p0?.applicationWindowToken)
                mPresenter.addCommend(mMusicId.toString(), et_commend.text.toString())

            }
        }
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     */
    private fun hideKeyboard(token: IBinder?) {
        if (token != null) {
            val im = mContext?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }


    private var mAdapter: PlayerCommentAdapter? = null
    private var mMusicId: String? = null
    override fun initPresenter() = mPresenter.attachView(this)

    override fun initInject() = fragmentComponent.inject(this)

//    override fun lazyLoadData() = mPresenter.getComment(mMusicId.toString())

    override fun addPraiseSuc(str: String) {
        hideLoading()
    }

    override fun cancelPraiseSuc(str: String) {
        hideLoading()
    }

    override fun showComment(playerCommentEntity: PlayerCommentEntity) {
//        mAdapter?.dataList = playerCommentEntity.list
//        mAdapter?.notifyDataSetChanged()

        hideLoading()
        mAdapter = PlayerCommentAdapter()
        mRecycler?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mAdapter?.dataList = playerCommentEntity.list
        mRecycler?.adapter = mAdapter

        mAdapter?.listener = object : PlayerCommentAdapter.PraiseClickListener {
            override fun onPraiseClick(position: Int, id: String, type: String) {
                if (type == "1") {
                    //取消点赞
                    showLoading()
                    mPresenter.cancelPraise(id)
                    mAdapter!!.dataList[position].isPraise = "0"
                    mAdapter!!.dataList[position].praiseNum = (mAdapter!!.dataList[position].praiseNum.toInt() - 1).toString()
                } else {
                    //点赞
                    showLoading()
                    mPresenter.addPraise(id)
                    mAdapter!!.dataList[position].isPraise = "1"
                    mAdapter!!.dataList[position].praiseNum = (mAdapter!!.dataList[position].praiseNum.toInt() + 1).toString()
                }
                mAdapter?.notifyDataSetChanged()
            }

        }
    }

    override fun showError(msg: String) {
        hideLoading()
    }

    override fun initDatas() {
        mMusicId = arguments?.getString("mMusicId")
        // mIsListen = arguments!!.getBoolean("isListen")
        showLoading()
        mPresenter.getComment(mMusicId.toString())

        //comment_layout.visibility = if (mIsListen) View.INVISIBLE else View.VISIBLE
    }

    fun setInputLayoutVisibility(isVisibility: Boolean) {
        comment_layout.visibility = if (!isVisibility) View.VISIBLE else View.GONE
    }

    override fun initRecyclerView() {
//        mAdapter = PlayerCommentAdapter()
//        mRecycler?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        mRecycler?.adapter = mAdapter
//
//        mAdapter?.listener = object : PlayerCommentAdapter.PraiseClickListener {
//            override fun onPraiseClick(position: Int, id: String, type: String) {
//                if (type == "1") {
//                    //取消点赞
//                    mPresenter.cancelPraise(id)
//                    mAdapter!!.dataList[position].isPraise = "0"
//                    mAdapter!!.dataList[position].praiseNum = (mAdapter!!.dataList[position].praiseNum.toInt() - 1).toString()
//                } else {
//                    //点赞
//                    mPresenter.addPraise(id)
//                    mAdapter!!.dataList[position].isPraise = "1"
//                    mAdapter!!.dataList[position].praiseNum = (mAdapter!!.dataList[position].praiseNum.toInt() + 1).toString()
//                }
//                mAdapter?.notifyDataSetChanged()
//            }
//
//        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_player_comment

    override fun initWidget() {
        tv_commend.setOnClickListener(this)
    }

    companion object {

        fun newInstance(mMusicId: String): PlayerCommentFragment {
            val fragment = PlayerCommentFragment()
            val args = Bundle()
            args.putString("mMusicId", mMusicId)
            fragment.arguments = args
            return fragment
        }
    }

}