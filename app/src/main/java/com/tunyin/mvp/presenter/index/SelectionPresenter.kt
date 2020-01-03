package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.index.SelectionContract
import com.tunyin.utils.rxSchedulerHelper
import com.weike.education.mvp.model.app.SelectionBean
import javax.inject.Inject

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/19
 * desc: 精选界面-SelectionPresenter
 *
 */
class SelectionPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<SelectionContract.View>(), SelectionContract.Presenter<SelectionContract.View> {

    override fun getSelection() {
        val subscriber = mRetrofitHelper.getSelection()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<SelectionBean>(mView) {
                    override fun onSuccess(mData: SelectionBean) {
                        mView?.showSelection(mData)
                    }
                })
        addSubscribe(subscriber)
    }
}