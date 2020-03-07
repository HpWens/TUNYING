package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.mine.MyGradeEntity

class GradeContract {

    interface View : BaseContract.BaseView {
        fun getGradeCallBack(entity: MyGradeEntity)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getGrade()
    }

}