package com.tunyin

import dagger.Component
import javax.inject.Singleton

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/9/12
 * desc: ApiComponent
 */
@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    val retrofitHelper: RetrofitHelper
}
