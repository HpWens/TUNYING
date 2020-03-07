package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.mine.GradeContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.SelfBean.Companion.instance
import com.tunyin.mvp.model.mine.MyGradeEntity
import com.tunyin.ui.activity.mine.LoginActivity
import com.tunyin.ui.activity.mine.LoginActivity.Companion.newInstance
import com.tunyin.utils.AppUtils
import com.tunyin.utils.rxSchedulerHelper
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class GradePresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) : RxPresenter<GradeContract.View>(), GradeContract.Presenter<GradeContract.View> {

    override fun getGrade() {
        val disposable: Disposable = mRetrofitHelper.getMyGrade()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<MyGradeEntity>>(mView) {

                    override fun onSuccess(mData: BaseEntity<MyGradeEntity>) {
                        mView?.let {
                            it.getGradeCallBack(mData.content)
                        }
                    }
                })
        addSubscribe(disposable)
    }
}