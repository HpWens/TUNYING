package com.tunyin.ui.activity.index

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.tunyin.R
import com.tunyin.ToastUtils

import com.tunyin.base.BaseActivity
import com.tunyin.listener.OnPageSelectedListener
import com.tunyin.mvp.model.SelfBean
import com.tunyin.ui.activity.mine.LoginActivity
import com.tunyin.utils.ImageUtil
import com.tunyin.utils.StatusBarUtil
import com.tunyin.widget.BannerView
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_guide.*
import java.util.ArrayList

/**
 * create by wangrongchao
 * on 2019/11/10
 */
class GuideActivity : BaseActivity() {

    private val bannerView: BannerView? = null
    private val tvSkip: TextView? = null
    private val handler = Handler()
    private val DELAY_TIME = 2000
    private val isSkip: Boolean = false
    override fun getLayoutId(): Int {
        return R.layout.activity_guide
    }

    override fun initWidget() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null)

        SelfBean.instance.isEnter=true
        setBanner()

        bv_banner.setPageSelectedListener(object : OnPageSelectedListener {
            override fun onSelected(position: Int) {
                if (isSkip) return
                // 看数组大小而定
                if (position == 3) {
                    bv_banner.release()
                    handler.postDelayed(runnable, DELAY_TIME.toLong())
                }
                if (position==3){
                    bv_banner.setOnClickListener(object :View.OnClickListener{
                        override fun onClick(p0: View?) {
                            ToastUtils.showToast("woshi ")
                        }

                    })
                }
            }
        })
    }

    /**
     * 轮播玩延时跳转
     */
    private val runnable = Runnable {
        if (isSkip && isFinishing) return@Runnable
        gotoLogin()
    }

    /**
     * 跳转到登录界面
     */
    private fun gotoLogin() {
        startActivity(Intent(this@GuideActivity, LoginActivity::class.java))
        finish()
    }

    private fun setBanner() {
        val images = ArrayList<Int>()
//        images.add(R.drawable.ui_loading)
        images.add(R.mipmap.guide_one)
        images.add(R.mipmap.guide_two)
        images.add(R.mipmap.guide_three)
        images.add(R.mipmap.guide_four)
        val imageLoad = object : ImageLoader() {
            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
//                imageView.scaleType = ImageView.ScaleType.FIT_XY
                ImageUtil.load(path as Int).on(imageView)
            }
        }
        bv_banner.setBannerStyle(BannerConfig.NOT_INDICATOR)
        bv_banner.setImageLoader(imageLoad).setImages(images).start()
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context?): Intent {
            val intent = Intent(context, GuideActivity::class.java)
            return intent
        }
    }
}
